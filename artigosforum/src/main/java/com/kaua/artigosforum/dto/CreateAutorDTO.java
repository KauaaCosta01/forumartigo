package com.kaua.artigosforum.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAutorDTO {

    @NotBlank(message = "Nome é Obrigatório")
    private String nome;

    @NotBlank(message = "Idade é Obrigatório")
    private String idade;

    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é Obrigatório")
    private String email;
}
