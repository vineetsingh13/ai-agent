package com.example.aiAgents.toolsRegistry.WeatherTool.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wind {

    private Double speed;
    private Integer deg;
    private Double gust;
}
