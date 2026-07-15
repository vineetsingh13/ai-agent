package com.example.aiAgents.toolsRegistry.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolsDefinitions {
    private String name;

    private String description;
}
