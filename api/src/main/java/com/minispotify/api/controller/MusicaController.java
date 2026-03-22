package com.minispotify.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.minispotify.api.model.Musica;
import com.minispotify.api.service.MusicaService;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private final MusicaService service;

    public MusicaController(MusicaService service) {
        this.service = service;
    }

    @PostMapping
    public Musica criar(@RequestBody Musica musica) {
        return service.criar(musica);
    }

    @GetMapping
    public List<Musica> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Musica buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Musica atualizar(@PathVariable Long id, @RequestBody Musica musica) {
        return service.atualizar(id, musica);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PostMapping("/{id}/reproduzir")
    public Musica reproduzir(@PathVariable Long id, @RequestHeader("X-USER-ID") Long idUsuario) {
        return service.reproduzir(id, idUsuario);
    }
}