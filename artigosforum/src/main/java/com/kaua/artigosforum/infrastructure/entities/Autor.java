package com.kaua.artigosforum.infrastructure.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "autores")
@Builder
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;
}
