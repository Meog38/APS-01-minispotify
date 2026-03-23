package com.minispotify.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.minispotify.api.model.Curtida;
import com.minispotify.api.model.Musica;
import com.minispotify.api.model.Usuario;

@Service
public class CurtidaService {

    private List<Curtida> curtidas = new ArrayList<>();
    private Long contadorId = 1L;

    private final UsuarioService usuarioService;
    private final MusicaService musicaService;

    public CurtidaService(UsuarioService usuarioService, MusicaService musicaService) {
        this.usuarioService = usuarioService;
        this.musicaService = musicaService;
    }

    public Curtida curtir(Long musicaId, Long idUsuario) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        if (usuario == null || !usuario.isAtivo()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário inativo ou não encontrado.");
        }

        Musica musica = musicaService.buscarPorId(musicaId);
        if (musica == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada.");
        }

        boolean jaCurtiu = curtidas.stream()
                .anyMatch(c -> c.getUsuario().getId().equals(idUsuario) && c.getMusica().getId().equals(musicaId));

        if (jaCurtiu) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já curtiu esta música.");
        }

        Curtida curtida = new Curtida(contadorId++, usuario, musica, LocalDateTime.now());
        curtidas.add(curtida);
        return curtida;
    }

    public List<Curtida> listarFavoritasPorUsuario(Long idUsuario) {
        return curtidas.stream()
                .filter(c -> c.getUsuario().getId().equals(idUsuario))
                .sorted((c1, c2) -> c2.getDataHora().compareTo(c1.getDataHora()))
                .collect(Collectors.toList());
    }
}
