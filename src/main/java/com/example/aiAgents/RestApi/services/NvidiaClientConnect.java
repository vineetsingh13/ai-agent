package com.example.aiAgents.RestApi.services;

import com.example.aiAgents.RestApi.DTO.ChatRequest;
import com.example.aiAgents.memoryPackage.LLMcontext;
import com.example.aiAgents.nvidiaConnection.DTO.ChoicesMessage;
import com.example.aiAgents.nvidiaConnection.DTO.NvidiaRequest;
import com.example.aiAgents.nvidiaConnection.DTO.NvidiaResponse;
import com.example.aiAgents.nvidiaConnection.DTO.messages;
import com.example.aiAgents.nvidiaConnection.connectionClass.NvidiaConnectionImpl;
import com.example.aiAgents.toolsRegistry.DTO.ToolsDecision;
import com.example.aiAgents.toolsRegistry.classes.ToolsPromptBuilder;
import com.example.aiAgents.toolsRegistry.classes.ToolsRegistry;
import com.example.aiAgents.toolsRegistry.interfaces.Tools;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;

@Service
public class NvidiaClientConnect {

    @Autowired
    public NvidiaConnectionImpl nvidiaConnectionImpl;

    @Autowired
    ToolsPromptBuilder toolsPromptBuilder;

    @Autowired
    ToolsRegistry toolsRegistry;

    @Autowired
    LLMcontext  llmContext;

    /*public String getAiResponse(ChatRequest request) {
        llmContext.addUserMessage(request.conversationId(), request.query());

        //remove this add system message line after testing
        llmContext.addSystemMessage(request.conversationId(), toolsPromptBuilder.build(toolsRegistry.getToolDefinitions()));

        ArrayList<messages> convo = new ArrayList<>(llmContext.getConversationById(request.conversationId()));
        convo.addFirst(new messages("system", toolsPromptBuilder.build(toolsRegistry.getToolDefinitions())));
        NvidiaRequest nvidiaRequest = new NvidiaRequest();
        nvidiaRequest.setModel("nvidia/nemotron-3-ultra-550b-a55b");
        nvidiaRequest.setTemperature(1.0);
        nvidiaRequest.setTopP(0.95);
        nvidiaRequest.setMaxTokens(16384);
        nvidiaRequest.setReasoningBudget(16384);
        nvidiaRequest.setReasoningEffort("high");
        nvidiaRequest.setSeed(42);
        nvidiaRequest.setStream(false);

        messages msg = new messages();
        msg.setRole("user");
        msg.setContent(request.query());
        nvidiaRequest.setMessages(convo);

        NvidiaResponse nvidiaResponse = nvidiaConnectionImpl.nvidia(nvidiaRequest);
        ChoicesMessage choicesMessage = nvidiaResponse.getChoices().getFirst().getMessage();
        llmContext.addAIMessage(request.conversationId(), choicesMessage.getContent());

        ObjectMapper mapper = new ObjectMapper();
        ToolsDecision toolsDecision = mapper.readValue(choicesMessage.getContent(),  ToolsDecision.class);
        if (toolsDecision.getTool()!=null){
            Tools requiredTool = toolsRegistry.getTool(toolsDecision.getTool());
            String toolResponse = requiredTool.execute("");
            convo.add(new messages(
                    "tool",
                    "Tool '" + toolsDecision.getTool() + "' returned: " + toolResponse
            ));
            nvidiaRequest.setMessages(convo);
            nvidiaConnectionImpl.nvidia(nvidiaRequest);
        }

        return toolsDecision.getResponse();
    }*/

    public String getAiResponse(ChatRequest request){

        // Store user message
        llmContext.addUserMessage(request.conversationId(), request.query());

        // Build conversation
        ArrayList<messages> convo =
                new ArrayList<>(llmContext.getConversationById(request.conversationId()));

        // Add system prompt (DO NOT store in memory)
        convo.addFirst(new messages(
                "system",
                toolsPromptBuilder.build(toolsRegistry.getToolDefinitions())
        ));
        llmContext.addSystemMessage(request.conversationId(), String.valueOf(new messages(
                "system",
                toolsPromptBuilder.build(toolsRegistry.getToolDefinitions()))));

        NvidiaRequest nvidiaRequest = createRequest(convo);

        // First LLM Call
        NvidiaResponse nvidiaResponse = nvidiaConnectionImpl.nvidia(nvidiaRequest);

        ChoicesMessage choice =
                nvidiaResponse.getChoices().getFirst().getMessage();

        ObjectMapper mapper = new ObjectMapper();

        ToolsDecision decision =
                mapper.readValue(choice.getContent(), ToolsDecision.class);

        // No tool required
        if (decision.getTool() == null || "none".equalsIgnoreCase(decision.getTool())) {

            llmContext.addAIMessage(
                    request.conversationId(),
                    decision.getResponse()
            );

            return decision.getResponse();
        }

        // Execute Tool
        Tools tool = toolsRegistry.getTool(decision.getTool());

        String toolResponse = tool.execute(decision.getInput());
        convo.add(new messages(
                "assistant",
                "Calling tool: " + decision.getTool()
        ));
        llmContext.addSystemMessage(request.conversationId(),
                "Calling tool: " + decision.getTool());
        // Add tool result
        convo.add(new messages(
                "assistant",
                "Tool '" + decision.getTool() + "' returned: " + toolResponse
        ));
        llmContext.addSystemMessage(request.conversationId(),
                "Tool '" + decision.getTool() + "' returned: " + toolResponse);

        // Second LLM Call
        nvidiaRequest.setMessages(convo);

        NvidiaResponse finalResponse =
                nvidiaConnectionImpl.nvidia(nvidiaRequest);

        ChoicesMessage finalChoice =
                finalResponse.getChoices().getFirst().getMessage();

        llmContext.addAIMessage(
                request.conversationId(),
                finalChoice.getContent()
        );

        return finalChoice.getContent();
    }

    private NvidiaRequest createRequest(ArrayList<messages> messages) {

        NvidiaRequest request = new NvidiaRequest();

        request.setModel("nvidia/nemotron-3-ultra-550b-a55b");
        request.setTemperature(1.0);
        request.setTopP(0.95);
        request.setMaxTokens(16384);
        request.setReasoningBudget(16384);
        request.setReasoningEffort("high");
        request.setSeed(42);
        request.setStream(false);
        request.setMessages(messages);

        return request;
    }
}
