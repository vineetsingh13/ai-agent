package com.example.aiAgents.nvidiaConnection.connectionClass;

import com.example.aiAgents.nvidiaConnection.DTO.NvidiaRequest;
import com.example.aiAgents.nvidiaConnection.DTO.NvidiaResponse;
import com.example.aiAgents.nvidiaConnection.interfaces.nvidiaConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NvidiaConnectionImpl implements nvidiaConnection {

    String NVIDIA_KEY = "nv";

    public WebClient webClient;

    public NvidiaConnectionImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://integrate.api.nvidia.com/v1").build();
    }

    @Override
    public NvidiaResponse nvidia(NvidiaRequest request) {
        return webClient.post()
                .uri("/chat/completions")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+NVIDIA_KEY)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(NvidiaResponse.class).block();

    }
}
