package com.kaua.artigosforum.controller;


import com.kaua.artigosforum.business.AutorService;
import com.kaua.artigosforum.dto.CreateAutorDTO;
import com.kaua.artigosforum.infrastructure.entities.Autor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AutorController {

    private final AutorService autorService;
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping("/create")
    public ResponseEntity<Autor> createAutor(@RequestBody CreateAutorDTO dto) {
        return ResponseEntity.ok(autorService.createAutor(dto));
    }

    @GetMapping("/getautor")
    public ResponseEntity<Autor> getAutorById(@RequestParam UUID id) {
        return ResponseEntity.ok(autorService.buscarAutorPorId(id));
    }

    @PutMapping("/updateautor")
    public ResponseEntity<Void> updateAutorByid(@RequestParam UUID id, @RequestBody CreateAutorDTO dto) {
        autorService.updateAutorById(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteautor")
    public ResponseEntity<Void> deleteAutorById(@RequestParam UUID id) {
        autorService.deleteAutorById(id);
        return ResponseEntity.ok().build();
    }

}
