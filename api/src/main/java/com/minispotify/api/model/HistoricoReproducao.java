package com.minispotify.api.model;

import java.time.LocalDateTime;

public class HistoricoReproducao {

    private Long id;
    private Usuario usuario;
    private Musica musica;
    private LocalDateTime dataHora;

    public HistoricoReproducao() {
    }

    public HistoricoReproducao(Long id, Usuario usuario, Musica musica, LocalDateTime dataHora) {
        this.id = id;
        this.usuario = usuario;
        this.musica = musica;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
