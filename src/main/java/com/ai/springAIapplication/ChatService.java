package com.ai.springAIapplication;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    //using spring ai chat model
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getChatResponse(String userInput) {

        return chatModel.call(userInput);
    }


    public String getChatResponseOptions(String userInput) {
                ChatResponse response= chatModel.call(
                        new Prompt(
                                userInput,
                                OpenAiChatOptions.builder()
                                        .model("gpt-4o")
                                        .maxTokens(150)  // Use maxTokens for non-reasoning models
                                        .build()
                        ));
                return response.getResult().getOutput().getText();
    }

}
