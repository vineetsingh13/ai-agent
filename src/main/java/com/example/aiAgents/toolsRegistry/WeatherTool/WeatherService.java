package com.example.aiAgents.toolsRegistry.WeatherTool;

import com.example.aiAgents.toolsRegistry.WeatherTool.DTO.WeatherResponse;

public interface WeatherService {

    public WeatherResponse getWeather(String city);
}
