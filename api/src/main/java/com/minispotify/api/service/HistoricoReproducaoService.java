package com.minispotify.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.minispotify.api.dto.TopMusicaDTO;
import com.minispotify.api.model.HistoricoReproducao;
import com.minispotify.api.model.Musica;
import com.minispotify.api.model.Usuario;

@Service
public class HistoricoReproducaoService {

    private List<HistoricoReproducao> historicos = new ArrayList<>();
    private Long contadorId = 1L;

    public HistoricoReproducao registrar(Usuario usuario, Musica musica) {
        HistoricoReproducao historico = new HistoricoReproducao(contadorId++, usuario, musica, LocalDateTime.now());
        historicos.add(historico);
        return historico;
    }

    public List<HistoricoReproducao> listarPorUsuario(Long idUsuario) {
        return historicos.stream()
                .filter(h -> h.getUsuario().getId().equals(idUsuario))
                .sorted((h1, h2) -> h2.getDataHora().compareTo(h1.getDataHora()))
                .collect(Collectors.toList());
    }

    public List<HistoricoReproducao> ultimasReproducoesGeral(int limite) {
        return historicos.stream()
                .sorted((h1, h2) -> h2.getDataHora().compareTo(h1.getDataHora()))
                .limit(limite)
                .collect(Collectors.toList());
    }

    public List<TopMusicaDTO> topMusicasPorUsuario(Long idUsuario, int limite) {
        return historicos.stream()
                .filter(h -> h.getUsuario().getId().equals(idUsuario))
                .collect(Collectors.groupingBy(HistoricoReproducao::getMusica, Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(limite)
                .map(e -> new TopMusicaDTO(
                        e.getKey().getTitulo(),
                        e.getKey().getArtista() != null ? e.getKey().getArtista().getNome() : "Desconhecido",
                        e.getValue()
                ))
                .collect(Collectors.toList());
    }
}
