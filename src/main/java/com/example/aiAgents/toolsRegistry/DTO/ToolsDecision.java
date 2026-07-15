package com.example.aiAgents.toolsRegistry.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolsDecision {

    private String tool;

    private String input;

    private String response;

}
