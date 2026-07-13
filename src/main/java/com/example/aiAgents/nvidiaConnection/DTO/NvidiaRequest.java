package com.example.aiAgents.nvidiaConnection.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NvidiaRequest {
    private String model;

    private Double temperature;

    @JsonProperty("top_p")
    private Double topP;

    @JsonProperty("max_tokens")
    private Integer maxTokens;

    @JsonProperty("reasoning_effort")
    private String reasoningEffort;

    @JsonProperty("reasoning_budget")
    private Integer reasoningBudget;

    private Integer seed;

    private Boolean stream;

    private List<messages> messages;

}


