package com.ai.springAIapplication;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
      /* //single image
        ImageResponse imageResponse=ImageService.getImageResponse(userInput);
       String imageUrl= imageResponse.getResult().getOutput().getUrl();
         response.sendRedirect(imageUrl);*/

        //multiple images
        ImageResponse imageResponse = ImageService.getImageResponse(userInput);
        List<String> imageUrls = imageResponse.getResults().stream().map(result -> result.getOutput().getUrl())
                .collect(Collectors.toList());
        for (String url : imageUrls) {
            response.sendRedirect(url);
        }
    }


}
