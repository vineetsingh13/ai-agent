package com.example.aiAgents.RestApi.controllers;

import com.example.aiAgents.RestApi.DTO.ChatRequest;
import com.example.aiAgents.RestApi.DTO.ChatResponse;
import com.example.aiAgents.RestApi.services.NvidiaClientConnect;
import com.example.aiAgents.memoryPackage.LLMcontext;
import com.example.aiAgents.nvidiaConnection.DTO.messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    public NvidiaClientConnect nvidiaClientConnect;

    @Autowired
    LLMcontext llMcontext;

    @PostMapping("/sendQuery")
    public ChatResponse getAiChatResponse(@RequestBody ChatRequest request) {
        return new ChatResponse(nvidiaClientConnect.getAiResponse(request));
    }

    @GetMapping("/getConversation")
    public HashMap<String, ArrayList<messages>> getAllConversation(){
        return llMcontext.getAllConversation();
    }

    @GetMapping("/getConversation/{conversationId}")
    public ArrayList<messages> getConversationById(@PathVariable String conversationId) {
        return llMcontext.getConversationById(conversationId);
    }
}
