package com.example.aiAgents.nvidiaConnection.interfaces;

import com.example.aiAgents.nvidiaConnection.DTO.NvidiaRequest;
import com.example.aiAgents.nvidiaConnection.DTO.NvidiaResponse;

public interface nvidiaConnection {

    public NvidiaResponse nvidia(NvidiaRequest request);
}
