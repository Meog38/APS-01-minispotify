package com.minispotify.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.minispotify.api.model.Album;
import com.minispotify.api.service.AlbumService;

@RestController
@RequestMapping("/albuns")
public class AlbumController {

    private final AlbumService service;

    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @PostMapping
    public Album criar(@RequestBody Album album) {
        return service.criar(album);
    }

    @GetMapping
    public List<Album> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Album buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Album atualizar(@PathVariable Long id, @RequestBody Album album) {
        return service.atualizar(id, album);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}