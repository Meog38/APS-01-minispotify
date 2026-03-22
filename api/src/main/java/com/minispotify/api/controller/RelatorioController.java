package com.minispotify.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minispotify.api.dto.TopMusicaDTO;
import com.minispotify.api.service.MusicaService;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final MusicaService musicaService;

    public RelatorioController(MusicaService musicaService) {
        this.musicaService = musicaService;
    }

    @GetMapping("/top-musicas")
    public List<TopMusicaDTO> getTopMusicas() {
        return musicaService.top10Musicas();
    }
}
