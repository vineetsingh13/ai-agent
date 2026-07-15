package com.example.aiAgents.toolsRegistry.TimeTool;

import com.example.aiAgents.toolsRegistry.DTO.ToolsDefinitions;
import com.example.aiAgents.toolsRegistry.interfaces.Tools;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class timeTool implements Tools {

    @Override
    public ToolsDefinitions getToolsDefinitions() {
        return ToolsDefinitions.builder()
                .name("timeTool")
                .description("This tool provides the current time in UTC format.")
                .build();
    }

    @Override
    public String execute(String input) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
