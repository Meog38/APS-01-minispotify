package com.minispotify.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minispotify.api.dto.TopMusicaDTO;
import com.minispotify.api.model.HistoricoReproducao;
import com.minispotify.api.service.HistoricoReproducaoService;
import com.minispotify.api.service.MusicaService;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final MusicaService musicaService;
    private final HistoricoReproducaoService historicoService;

    public RelatorioController(MusicaService musicaService, HistoricoReproducaoService historicoService) {
        this.musicaService = musicaService;
        this.historicoService = historicoService;
    }

    @GetMapping("/top-musicas")
    public List<TopMusicaDTO> getTopMusicas() {
        return musicaService.top10Musicas();
    }

    @GetMapping("/usuarios/{id}/top-musicas")
    public List<TopMusicaDTO> getTopMusicasPorUsuario(
            @PathVariable Long id, 
            @RequestParam(defaultValue = "10") int limit) {
        return historicoService.topMusicasPorUsuario(id, limit);
    }

    @GetMapping("/ultimas-reproducoes")
    public List<HistoricoReproducao> getUltimasReproducoesGeral(
            @RequestParam(defaultValue = "10") int limit) {
        return historicoService.ultimasReproducoesGeral(limit);
    }
}
