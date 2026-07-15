package com.example.aiAgents.toolsRegistry.classes;

import com.example.aiAgents.toolsRegistry.DTO.ToolsDefinitions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolsPromptBuilder {
    public String build(List<ToolsDefinitions> tools) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
                You are an AI Agent.

                You have access to the following tools.

                ============================
                AVAILABLE TOOLS
                ============================

                """);

        for (ToolsDefinitions tool : tools) {

            prompt.append("Name: ")
                    .append(tool.getName())
                    .append("\n");

            prompt.append("Description: ")
                    .append(tool.getDescription())
                    .append("\n\n");
        }

        prompt.append("""
                ============================
                RULES
                ============================

                1. If the user's request requires one of the available tools,
                   respond ONLY with valid JSON.

                2. Do NOT explain your reasoning.

                3. Do NOT wrap the JSON inside markdown.

                4. The JSON must exactly match this schema:

                {
                  "tool": "<tool-name>",
                  "input": "<tool-input>",
                  "response": null
                }
                
                5. If not tools are required just return the response nothing else.

                Example:

                User:
                Calculate 25 * 40

                Response:

                {
                  "tool":"calculator",
                  "input":"25*40",
                  "response":null
                }

                If no tool is required, respond with:

                {
                  "tool":"none",
                  "input":null,
                  "response":"Your response"
                }

                Return ONLY JSON.
                """);

        return prompt.toString();
    }
}
