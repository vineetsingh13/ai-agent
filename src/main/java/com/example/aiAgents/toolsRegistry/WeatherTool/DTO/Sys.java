package com.example.aiAgents.toolsRegistry.WeatherTool.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sys {

    private Integer type;
    private Integer id;
    private String country;
    private Long sunrise;
    private Long sunset;
}
