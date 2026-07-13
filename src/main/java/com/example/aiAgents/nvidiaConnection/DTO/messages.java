package com.example.aiAgents.nvidiaConnection.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class messages {
    private String role;

    private String content;
}
