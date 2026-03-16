package com.minispotify.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.minispotify.api.model.Artista;
import com.minispotify.api.service.ArtistaService;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaService service;

    public ArtistaController(ArtistaService service) {
        this.service = service;
    }

    @PostMapping
    public Artista criar(@RequestBody Artista artista) {
        return service.criar(artista);
    }

    @GetMapping
    public List<Artista> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Artista buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Artista atualizar(@PathVariable Long id, @RequestBody Artista artista) {
        return service.atualizar(id, artista);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}