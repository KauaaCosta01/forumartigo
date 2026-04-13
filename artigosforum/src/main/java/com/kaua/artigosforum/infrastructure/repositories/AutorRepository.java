package com.kaua.artigosforum.infrastructure.repositories;

import com.kaua.artigosforum.infrastructure.entities.Autor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface AutorRepository extends JpaRepository<Autor, UUID> {

    @Transactional
    void deleteById(UUID id);

    Optional<Autor> findById(UUID id);

    boolean existsByEmail(String email);
}
