package com.kaua.artigosforum.business;

import com.kaua.artigosforum.dto.CreateAutorDTO;
import com.kaua.artigosforum.exception.BusinessException;
import com.kaua.artigosforum.exception.ResourceNotFoundException;
import com.kaua.artigosforum.infrastructure.entities.Autor;
import com.kaua.artigosforum.infrastructure.repositories.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor createAutor(CreateAutorDTO dto) {
        if (autorRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }

        Autor autor = Autor.builder()
                .nome(dto.getNome())
                .idade((dto.getIdade()))
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .build();

        Autor saved = autorRepository.save(autor);

        return saved;
    }

    public Autor buscarAutorPorId(UUID id) {
        return autorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Autor inexistente")
        );
    }


    public void updateAutorById(UUID id, CreateAutorDTO dto) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor com o id" + id + "não encontrado"));

        if (!autor.getEmail().equals(dto.getEmail()) && autorRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }

        autor.setNome(dto.getNome());
        autor.setIdade((dto.getIdade()));
        autor.setEmail(dto.getEmail());
        autor.setTelefone(dto.getTelefone());

        autorRepository.save(autor);
    }

    public void deleteAutorById(UUID id) {
        autorRepository.deleteById(id);
    }
}
