package com.example.aiAgents.RestApi.controllers;

import com.example.aiAgents.RestApi.DTO.ChatRequest;
import com.example.aiAgents.RestApi.DTO.ChatResponse;
import com.example.aiAgents.RestApi.services.NvidiaClientConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    public NvidiaClientConnect nvidiaClientConnect;

    @PostMapping("/sendQuery")
    public ChatResponse getAiChatResponse(@RequestBody ChatRequest request) {
        return new ChatResponse(nvidiaClientConnect.getAiResponse(request.query()));
    }
}
