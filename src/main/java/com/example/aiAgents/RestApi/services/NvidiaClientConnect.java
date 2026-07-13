package com.example.aiAgents.RestApi.services;

import com.example.aiAgents.nvidiaConnection.DTO.ChoicesMessage;
import com.example.aiAgents.nvidiaConnection.DTO.NvidiaRequest;
import com.example.aiAgents.nvidiaConnection.DTO.NvidiaResponse;
import com.example.aiAgents.nvidiaConnection.DTO.messages;
import com.example.aiAgents.nvidiaConnection.connectionClass.NvidiaConnectionImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NvidiaClientConnect {

    @Autowired
    public NvidiaConnectionImpl nvidiaConnectionImpl;

    public String getAiResponse(String clientMessage) {
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
        msg.setContent(clientMessage);
        nvidiaRequest.setMessages(java.util.List.of(msg));

        NvidiaResponse nvidiaResponse = nvidiaConnectionImpl.nvidia(nvidiaRequest);
        ChoicesMessage choicesMessage = nvidiaResponse.getChoices().getFirst().getMessage();
        return choicesMessage.getContent();
    }

}
