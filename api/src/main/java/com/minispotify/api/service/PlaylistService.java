package com.minispotify.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.minispotify.api.model.Musica;
import com.minispotify.api.model.Playlist;
import com.minispotify.api.model.Usuario;

@Service
public class PlaylistService {

    private List<Playlist> playlists = new ArrayList<>();
    private Long contadorId = 1L;

    private final UsuarioService usuarioService;
    private final MusicaService musicaService;

    public PlaylistService(UsuarioService usuarioService, MusicaService musicaService) {
        this.usuarioService = usuarioService;
        this.musicaService = musicaService;
    }

    public Playlist criar(Playlist playlist, Long idUsuario) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        if (usuario == null || !usuario.isAtivo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário inválido ou inativo.");
        }
        playlist.setUsuario(usuario);
        playlist.setId(contadorId++);
        playlists.add(playlist);
        return playlist;
    }

    public List<Playlist> listar() {
        return playlists.stream()
                .filter(Playlist::isAtivo)
                .collect(Collectors.toList());
    }

    public Playlist buscarPorId(Long id) {
        return playlists.stream()
                .filter(p -> p.getId().equals(id) && p.isAtivo())
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist não encontrada."));
    }

    public Playlist atualizar(Long id, Playlist playlistAtualizada, Long idUsuario) {
        Playlist playlist = buscarPorId(id);

        if (!playlist.getUsuario().getId().equals(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas o dono pode alterar a playlist.");
        }

        playlist.setNome(playlistAtualizada.getNome());
        playlist.setPublica(playlistAtualizada.isPublica());

        return playlist;
    }

    public void deletar(Long id, Long idUsuario) {
        Playlist playlist = buscarPorId(id);

        if (!playlist.getUsuario().getId().equals(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas o dono pode deletar a playlist.");
        }

        playlist.setAtivo(false);
    }

    public Playlist adicionarMusica(Long playlistId, Long musicaId, Long idUsuario) {
        Playlist playlist = buscarPorId(playlistId);

        if (!playlist.getUsuario().getId().equals(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas o dono da playlist pode adicionar músicas.");
        }

        Musica musica = musicaService.buscarPorId(musicaId);
        if (musica == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada.");
        }

        boolean musicaJaExiste = playlist.getMusicas().stream()
                .anyMatch(m -> m.getId().equals(musica.getId()));

        if (musicaJaExiste) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A música já está na playlist.");
        }

        playlist.getMusicas().add(musica);
        return playlist;
    }
}
