package com.minispotify.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.minispotify.api.dto.TopMusicaDTO;
import com.minispotify.api.model.Musica;
import com.minispotify.api.model.Usuario;

import com.minispotify.api.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    private final UsuarioService usuarioService;
    private final HistoricoReproducaoService historicoService;

    public MusicaService(UsuarioService usuarioService, HistoricoReproducaoService historicoService) {
        this.usuarioService = usuarioService;
        this.historicoService = historicoService;
    }

    public Musica criar(Musica musica) {
        return musicaRepository.save(musica);
    }

    public List<Musica> listar() {
        return musicaRepository.findAll();
    }

    public Musica buscarPorId(Long id) {
        return musicaRepository.findById(id).orElse(null);
    }

    public Musica atualizar(Long id, Musica musicaAtualizada) {

        Musica musica = buscarPorId(id);

        if (musica == null) {
            return null;
        }

        musica.setTitulo(musicaAtualizada.getTitulo());
        musica.setDuracaoSegundos(musicaAtualizada.getDuracaoSegundos());
        musica.setNumeroFaixa(musicaAtualizada.getNumeroFaixa());
        musica.setAlbum(musicaAtualizada.getAlbum());
        musica.setArtista(musicaAtualizada.getArtista());

        return musicaRepository.save(musica);
    }

    public boolean deletar(Long id) {

        Musica musica = buscarPorId(id);

        if (musica == null) {
            return false;
        }

        musicaRepository.delete(musica);
        return true;
    }

    public Musica reproduzir(Long id, Long idUsuario) {

        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        if (usuario == null || !usuario.isAtivo()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário inativo ou não encontrado.");
        }

        Musica musica = buscarPorId(id);

        if (musica == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada.");
        }

        musica.setTotalReproducoes(musica.getTotalReproducoes() + 1);
        historicoService.registrar(usuario, musica);

        return musicaRepository.save(musica);
    }

    public List<TopMusicaDTO> top10Musicas() {
        return musicaRepository.findAll().stream()
                .sorted((m1, m2) -> m2.getTotalReproducoes().compareTo(m1.getTotalReproducoes()))
                .limit(10)
                .map(m -> new TopMusicaDTO(
                        m.getTitulo(),
                        m.getArtista() != null ? m.getArtista().getNome() : "Desconhecido",
                        m.getTotalReproducoes()
                ))
                .collect(Collectors.toList());
    }
}