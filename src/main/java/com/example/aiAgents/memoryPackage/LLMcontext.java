package com.example.aiAgents.memoryPackage;

import com.example.aiAgents.nvidiaConnection.DTO.messages;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class LLMcontext {

    HashMap<String, ArrayList<messages>> conversationMap = new HashMap<>();

    public void addUserMessage(String conversationId, String message){
        messages msg = new messages();
        msg.setRole("user");
        msg.setContent(message);

        insertConvo(conversationId, msg);
    }

    public void addAIMessage(String conversationId, String message){
        messages msg = new messages();
        msg.setRole("assistant");
        msg.setContent(message);

        insertConvo(conversationId, msg);
    }

    public void addSystemMessage(String conversationId, String message){
        messages msg = new messages();
        msg.setRole("system");
        msg.setContent(message);

        insertConvo(conversationId, msg);
    }

    public ArrayList<messages> getConversationById(String conversationId){
        return conversationMap.get(conversationId);
    }

    public HashMap<String, ArrayList<messages>> getAllConversation(){
        return conversationMap;
    }

    private void insertConvo(String conversationId, messages msg){
        if(conversationMap.containsKey(conversationId)){
            conversationMap.get(conversationId).add(msg);
        }else{
            ArrayList<messages> list = new ArrayList<>();
            list.add(msg);
            conversationMap.put(conversationId, list);
        }
    }
}
