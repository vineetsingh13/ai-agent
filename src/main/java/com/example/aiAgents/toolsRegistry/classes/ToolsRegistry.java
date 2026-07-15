package com.example.aiAgents.toolsRegistry.classes;

import com.example.aiAgents.toolsRegistry.DTO.ToolsDefinitions;
import com.example.aiAgents.toolsRegistry.interfaces.Tools;
import org.springframework.stereotype.Service;

import javax.tools.Tool;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ToolsRegistry {

    private final List<Tools> tools;
    private final Map<String, Tools> toolMap;

    public ToolsRegistry(List<Tools> tools) {
        this.tools = tools;
        this.toolMap = tools.stream()
                .collect(Collectors.toMap(
                        tool -> tool.getToolsDefinitions().getName(),
                        Function.identity()
                ));
    }

    public List<ToolsDefinitions> getToolDefinitions() {

        return tools.stream()
                .map(Tools::getToolsDefinitions)
                .toList();
    }

    public Tools getTool(String toolName) {
        return toolMap.get(toolName);
    }
}
