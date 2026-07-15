package com.example.aiAgents.toolsRegistry.interfaces;

import com.example.aiAgents.toolsRegistry.DTO.ToolsDefinitions;

public interface Tools {

    ToolsDefinitions getToolsDefinitions();

    String execute(String input);
}
