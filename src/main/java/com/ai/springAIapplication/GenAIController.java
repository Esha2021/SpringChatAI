package com.ai.springAIapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenAIController {

    @Autowired
    ChatService chatService;;

    public GenAIController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/request-ai")
    public String getChatResponse(@RequestParam String userInput){
        return chatService.getChatResponse(userInput);
    }

}
