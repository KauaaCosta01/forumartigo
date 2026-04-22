package com.kaua.artigosforum.controller;


import com.kaua.artigosforum.business.ArtigoService;
import com.kaua.artigosforum.dto.CreateArtigoDTO;
import com.kaua.artigosforum.infrastructure.entities.Artigos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ArtigoController {

    private final ArtigoService artigoService;
    public ArtigoController(ArtigoService artigoService) {
        this.artigoService = artigoService;
    }

    @PostMapping("/createartigo")
    public ResponseEntity<Artigos> createArtigo(@RequestBody CreateArtigoDTO createArtigoDTO) {
        return ResponseEntity.ok(artigoService.createArtigo(createArtigoDTO));
    }

    @GetMapping("/buscarartigo")
    public ResponseEntity<Artigos>  buscarArtigoPorId(@RequestParam UUID id) {
        return ResponseEntity.ok(artigoService.buscarArtigoPorId(id));
    }

    @PutMapping("/updateartigo")
    public ResponseEntity<Void> updateArtigoPorId(@RequestParam UUID id, @RequestBody CreateArtigoDTO dto) {
        artigoService.updateArtigoPorId(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteartigo")
    public ResponseEntity<Void> deleteArtigoPorId(@RequestParam UUID id) {
        artigoService.deleteArtigoPorId(id);
        return ResponseEntity.ok().build();
    }
}
