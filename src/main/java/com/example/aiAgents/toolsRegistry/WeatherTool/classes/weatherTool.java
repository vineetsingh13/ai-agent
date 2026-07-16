package com.example.aiAgents.toolsRegistry.WeatherTool.classes;

import com.example.aiAgents.toolsRegistry.DTO.ToolsDefinitions;
import com.example.aiAgents.toolsRegistry.interfaces.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class weatherTool implements Tools {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    WeatherServiceImpl weatherService;

    @Override
    public ToolsDefinitions getToolsDefinitions() {
        return ToolsDefinitions.builder()
                .name("weatherTool")
                .description("This tool provides the current weather information for a given location.")
                .build();
    }

    @Override
    public String execute(String input) {

        return String.valueOf(weatherService.getWeather(input));
    }
}
