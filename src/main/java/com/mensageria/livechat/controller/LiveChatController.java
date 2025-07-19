package com.mensageria.livechat.controller;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.util.HtmlUtils;

import com.mensageria.livechat.domain.ChatOutput;
import com.mensageria.livechat.dto.ChatInputDTO;

import jakarta.validation.Valid;

@Controller
@Validated
public class LiveChatController {

    @MessageMapping("/new-message")
    @SendTo("/topics/livechat")
    public ChatOutput newMessage(@Valid ChatInputDTO input) {
        return new ChatOutput(HtmlUtils.htmlEscape(input.getUser() + ": " + input.getMessage()));
    }

    @MessageExceptionHandler
    public String handleValidationException (Exception ex) {
        return "Erro: " + ex.getMessage();
    }
}
