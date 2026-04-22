package com.kaua.artigosforum.dto;


import com.kaua.artigosforum.infrastructure.entities.Autor;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CreateArtigoDTO {

    @NotBlank(message = "Nome do Artigo é obrigatório")
    private String nomeArtigo;

    @NotBlank(message = "Texto do Artigo é obrigatório")
    private String textoArtigo;

    @NotNull(message = "Autor é obrigatório")
    private UUID autorId;

}
