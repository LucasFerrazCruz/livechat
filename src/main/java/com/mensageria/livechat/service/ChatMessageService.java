package com.mensageria.livechat.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mensageria.livechat.domain.ChatOutput;

@Service
public class ChatMessageService {

    private final List<ChatOutput> messages = new ArrayList<>();

    public void saveMessage (ChatOutput message) {
        messages.add(message);
    }

    public List<ChatOutput> getAllMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void clearMessages() {
        messages.clear();
    }
}
