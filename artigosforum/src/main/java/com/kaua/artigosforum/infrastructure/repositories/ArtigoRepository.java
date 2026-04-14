package com.kaua.artigosforum.infrastructure.repositories;

import com.kaua.artigosforum.infrastructure.entities.Artigos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ArtigoRepository extends JpaRepository<Artigos, UUID> {

    Optional<Artigos> findById(UUID id);

    @Transactional
    void deleteById(UUID id);

    boolean existsByNomeArtigo(String nomeArtigo);
}
