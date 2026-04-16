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
    public com.minispotify.api.dto.PlaylistResponseDTO criar(@RequestBody com.minispotify.api.dto.PlaylistCreateDTO dto, @RequestHeader("X-USER-ID") Long idUsuario) {
        Playlist playlist = new Playlist();
        playlist.setNome(dto.nome());
        playlist.setPublica(dto.publica());
        
        Playlist criada = service.criar(playlist, idUsuario);
        return converterParaDTO(criada);
    }

    @GetMapping
    public List<com.minispotify.api.dto.PlaylistResponseDTO> listar() {
        return service.listar().stream()
                .map(this::converterParaDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public com.minispotify.api.dto.PlaylistResponseDTO buscar(@PathVariable Long id) {
        return converterParaDTO(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public com.minispotify.api.dto.PlaylistResponseDTO atualizar(@PathVariable Long id, @RequestBody com.minispotify.api.dto.PlaylistCreateDTO dto, @RequestHeader("X-USER-ID") Long idUsuario) {
        Playlist playlist = new Playlist();
        playlist.setNome(dto.nome());
        playlist.setPublica(dto.publica());
        
        Playlist atualizada = service.atualizar(id, playlist, idUsuario);
        return converterParaDTO(atualizada);
    }

    private com.minispotify.api.dto.PlaylistResponseDTO converterParaDTO(Playlist p) {
        if (p == null) return null;
        
        List<String> musicas = new java.util.ArrayList<>();
        if (p.getMusicas() != null) {
            musicas = p.getMusicas().stream().map(m -> m.getTitulo()).toList();
        }
        
        String dono = p.getUsuario() != null ? p.getUsuario().getNome() : "Desconhecido";
        
        return new com.minispotify.api.dto.PlaylistResponseDTO(
            p.getId(), p.getNome(), p.isPublica(), p.getDataCriacao(), dono, musicas
        );
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
