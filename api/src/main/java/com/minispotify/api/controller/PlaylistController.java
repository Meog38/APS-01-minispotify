package com.minispotify.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.minispotify.api.model.Playlist;
import com.minispotify.api.service.PlaylistService;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService service;

    public PlaylistController(PlaylistService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Playlist criar(@RequestBody Playlist playlist, @RequestHeader("X-USER-ID") Long idUsuario) {
        return service.criar(playlist, idUsuario);
    }

    @GetMapping
    public List<Playlist> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Playlist buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Playlist atualizar(@PathVariable Long id, @RequestBody Playlist playlist, @RequestHeader("X-USER-ID") Long idUsuario) {
        return service.atualizar(id, playlist, idUsuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id, @RequestHeader("X-USER-ID") Long idUsuario) {
        service.deletar(id, idUsuario);
    }

    @PostMapping("/{playlistId}/musicas/{musicaId}")
    public Playlist adicionarMusica(
            @PathVariable Long playlistId,
            @PathVariable Long musicaId,
            @RequestHeader("X-USER-ID") Long idUsuario) {
        return service.adicionarMusica(playlistId, musicaId, idUsuario);
    }
}
