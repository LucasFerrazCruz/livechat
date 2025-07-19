package com.mensageria.livechat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class ChatInputDTO {
    
    @NotBlank(message = "Nome de usuário é obrigatório.")
    @Getter
    @Setter
    private String user;

    @NotBlank(message = "Não é permitido o envio de mensagens em branco.")
    @Size(max = 500, message = "Não é permitido mensagens com mais de 500 caracteres.")
    @Getter
    @Setter
    private String message;
}
