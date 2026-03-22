package com.minispotify.api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private Long id;
    private String nome;
    private boolean publica;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private Usuario usuario;
    private List<Musica> musicas = new ArrayList<>();
    private boolean ativo = true;

    public Playlist() {
    }

    public Playlist(Long id, String nome, boolean publica, LocalDateTime dataCriacao, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.publica = publica;
        if (dataCriacao != null) {
            this.dataCriacao = dataCriacao;
        }
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
