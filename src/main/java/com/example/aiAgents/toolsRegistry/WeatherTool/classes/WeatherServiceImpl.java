package com.example.aiAgents.toolsRegistry.WeatherTool.classes;

import com.example.aiAgents.toolsRegistry.DTO.ToolsDefinitions;
import com.example.aiAgents.toolsRegistry.WeatherTool.DTO.WeatherResponse;
import com.example.aiAgents.toolsRegistry.WeatherTool.WeatherService;
import com.example.aiAgents.toolsRegistry.interfaces.Tools;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherServiceImpl implements WeatherService {

    WebClient  webClient;
    private final String weatherKey = "weather-API-key";

    public WeatherServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openweathermap.org/data/2.5").build();
    }


    @Override
    public WeatherResponse getWeather(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/weather").queryParam("q",city).queryParam("appid",weatherKey).build())
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block();
    }
}
