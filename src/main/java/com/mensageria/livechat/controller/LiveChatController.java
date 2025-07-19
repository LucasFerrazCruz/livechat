package com.mensageria.livechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.util.HtmlUtils;

import com.mensageria.livechat.domain.ChatOutput;
import com.mensageria.livechat.dto.ChatInputDTO;
import com.mensageria.livechat.dto.ErrorResponseDTO;
import com.mensageria.livechat.service.ChatMessageService;

import jakarta.validation.Valid;

@Controller
@Validated
public class LiveChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/new-message")
    @SendTo("/topics/livechat")
    public ChatOutput newMessage(@Valid ChatInputDTO input) {
        ChatOutput output = new ChatOutput(HtmlUtils.htmlEscape(input.getUser() + ": " + input.getMessage()));
        chatMessageService.saveMessage(output);

        return output;
    }

    @MessageExceptionHandler(MethodArgumentNotValidException.class)
    @SendToUser("/queue/errors")
    public ErrorResponseDTO handleValidationException (MethodArgumentNotValidException ex) {
        
        ErrorResponseDTO error = new ErrorResponseDTO("Erro na validação.");

        ex.getBindingResult().getFieldErrors().forEach(err -> {
            error.addFieldErrors(err.getField(), err.getDefaultMessage());
        });

        return error;
    }

    @MessageExceptionHandler(Exception.class)
    @SendToUser("/queue/errors")
    public ErrorResponseDTO handleGenericException(Exception ex) {
        return new ErrorResponseDTO("Erro interno: " + ex.getMessage());
    }
}
