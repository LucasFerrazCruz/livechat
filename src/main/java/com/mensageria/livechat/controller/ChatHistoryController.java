package com.mensageria.livechat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mensageria.livechat.domain.ChatOutput;
import com.mensageria.livechat.service.ChatMessageService;

@RestController
@RequestMapping("/api/chat")
public class ChatHistoryController {

    private final ChatMessageService chatMessageService;

    public ChatHistoryController (ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/history")
    public List<ChatOutput> getChatHistory () {
        return chatMessageService.getAllMessages();
    }

}
