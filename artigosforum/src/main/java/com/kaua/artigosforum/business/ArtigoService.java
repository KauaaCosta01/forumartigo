package com.kaua.artigosforum.business;

import com.kaua.artigosforum.dto.CreateArtigoDTO;
import com.kaua.artigosforum.exception.BusinessException;
import com.kaua.artigosforum.exception.ResourceNotFoundException;
import com.kaua.artigosforum.infrastructure.entities.Artigos;
import com.kaua.artigosforum.infrastructure.entities.Autor;
import com.kaua.artigosforum.infrastructure.repositories.ArtigoRepository;
import com.kaua.artigosforum.infrastructure.repositories.AutorRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ArtigoService {

    private final ArtigoRepository artigoRepository;
    private final AutorRepository autorRepository;

    // Injetamos também o AutorRepository para buscar o autor pelo ID.
    // O Spring injeta os dois automaticamente pelo construtor.
    public ArtigoService(ArtigoRepository artigoRepository, AutorRepository autorRepository) {
        this.artigoRepository = artigoRepository;
        this.autorRepository = autorRepository;
    }

    public Artigos createArtigo(CreateArtigoDTO dto) {

        // Verifica se já existe um artigo com esse nome.
        if (artigoRepository.existsByNomeArtigo(dto.getNomeArtigo())) {
            throw new BusinessException("Já existe um artigo com esse nome");
        }

        // Busca o autor pelo ID informado no DTO.
        // Se não encontrar, lança 404 automaticamente pelo nosso handler.
        Autor autor = autorRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Autor com id " + dto.getAutorId() + " não encontrado"
                ));

        // Monta o artigo com todos os campos, incluindo o autor e a data atual.
        Artigos artigo = Artigos.builder()
                .nomeArtigo(dto.getNomeArtigo())
                .textoArtigo(dto.getTextoArtigo())
                // LocalDateTime.now() = data e hora exata da criação do artigo.
                .createAt(LocalDateTime.now())
                // Vincula o objeto Autor completo ao artigo.
                // O Hibernate vai salvar o autor_id automaticamente no banco.
                .autor(autor)
                .build();

        return artigoRepository.save(artigo);
    }

    public Artigos buscarArtigoPorId(UUID id) {
        return artigoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Artigo com id " + id + " não encontrado"
                ));
    }

    public void updateArtigoPorId(UUID id, CreateArtigoDTO dto) {
        Artigos artigo = artigoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Artigo com id " + id + " não encontrado"
                ));

        artigo.setNomeArtigo(dto.getNomeArtigo());
        artigo.setTextoArtigo(dto.getTextoArtigo());

        artigoRepository.save(artigo);
    }

    public void deleteArtigoPorId(UUID id) {
        // Verifica se existe antes de tentar deletar.
        if (!artigoRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Artigo com id " + id + " não encontrado"
            );
        }
        artigoRepository.deleteById(id);
    }
}