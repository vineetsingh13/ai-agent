package com.example.aiAgents.nvidiaConnection.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Choices {

    private Integer index;
    private ChoicesMessage message;
    private String finish_reason;
    private Object logprobs;

}



