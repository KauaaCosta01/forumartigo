package com.kaua.artigosforum.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "artigos")

public class Artigos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String nomeArtigo;

    @Column(nullable = false, length = 400)
    private String textoArtigo;

    @Column(name = "create_at")
    private LocalDateTime createAt;

}
