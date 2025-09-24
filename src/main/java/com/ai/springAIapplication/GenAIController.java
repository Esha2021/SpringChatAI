package com.ai.springAIapplication;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GenAIController {

    @Autowired
   private final ChatService chatService;


    @Autowired
    ImageService ImageService;

    public GenAIController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/request-ai")
    public String getChatResponse(@RequestParam String userInput){

        return chatService.getChatResponse(userInput);
    }

    @GetMapping("/request-Imageai")
    public void getImageResponse(HttpServletResponse response, @RequestParam String userInput) throws IOException {
        ImageResponse imageResponse=ImageService.getImageResponse(userInput);
       String imageUrl= imageResponse.getResult().getOutput().getUrl();
         response.sendRedirect(imageUrl);
    }

}
