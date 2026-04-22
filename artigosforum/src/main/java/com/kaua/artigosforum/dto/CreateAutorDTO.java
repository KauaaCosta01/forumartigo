package com.kaua.artigosforum.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAutorDTO {

    @NotBlank(message = "Nome é Obrigatório")
    private String nome;

    @NotNull(message = "Idade é Obrigatório")
    private Integer idade;

    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é Obrigatório")
    private String email;

    @NotBlank(message = "Telefone é obroigatório")
    private String telefone;
}
