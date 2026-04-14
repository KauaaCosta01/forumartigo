package com.kaua.artigosforum.business;


import com.kaua.artigosforum.dto.CreateArtigoDTO;
import com.kaua.artigosforum.infrastructure.entities.Artigos;
import com.kaua.artigosforum.infrastructure.repositories.ArtigoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArtigoService {

    private final ArtigoRepository artigoRepository;
    public ArtigoService(ArtigoRepository artigoRepository) {
        this.artigoRepository = artigoRepository;
    }

    public Artigos createArtigo(CreateArtigoDTO dto) {
        if (artigoRepository.existsByNomeArtigo(dto.getNomeArtigo())) {
            throw new IllegalArgumentException("Artigo já cadastrado");
        }

        Artigos artigo = new Artigos().builder()
                .nomeArtigo(dto.getNomeArtigo())
                .textoArtigo(dto.getTextoArtigo())
                .build();

        Artigos saved = artigoRepository.save(artigo);
        return saved;
    }

    public Artigos buscarArtigoPorId(UUID id) {
        if (!artigoRepository.existsById(id)) {
            throw new IllegalArgumentException("Artigo inexistente");
        }
        return artigoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Artigo inexistente")
        );
    }

    public void updateArtigoPorId(UUID id, CreateArtigoDTO dto) {
        Artigos artigo = artigoRepository.existsByNomeArtigo(dto.getNomeArtigo()) ?
                artigoRepository.findById(id).orElseThrow(
                        () -> new RuntimeException("Artigo inexistente")
                ) : null;

        if (!artigoRepository.existsByNomeArtigo(dto.getNomeArtigo())) {
            throw new IllegalArgumentException("Artigo inexistente");
        }

        artigo.setNomeArtigo(dto.getNomeArtigo());
        artigo.setTextoArtigo(dto.getTextoArtigo());

        artigoRepository.save(artigo);

    }


    public void deleteArtigoPorId(UUID id) {
        artigoRepository.deleteById(id);
    }
}
