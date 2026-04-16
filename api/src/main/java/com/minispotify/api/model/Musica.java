package com.minispotify.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer duracaoSegundos;
    private Integer numeroFaixa;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;
    
    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    private Long totalReproducoes = 0L;

    public Musica() {
    }

    public Musica(Long id, String titulo, Integer duracaoSegundos, Integer numeroFaixa,
                  Album album, Artista artista) {
        this.id = id;
        this.titulo = titulo;
        this.duracaoSegundos = duracaoSegundos;
        this.numeroFaixa = numeroFaixa;
        this.album = album;
        this.artista = artista;
        this.totalReproducoes = 0L;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public Integer getDuracaoSegundos() { return duracaoSegundos; }
    public Integer getNumeroFaixa() { return numeroFaixa; }
    public Album getAlbum() { return album; }
    public Artista getArtista() { return artista; }
    public Long getTotalReproducoes() { return totalReproducoes; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDuracaoSegundos(Integer duracaoSegundos) { this.duracaoSegundos = duracaoSegundos; }
    public void setNumeroFaixa(Integer numeroFaixa) { this.numeroFaixa = numeroFaixa; }
    public void setAlbum(Album album) { this.album = album; }
    public void setArtista(Artista artista) { this.artista = artista; }
    public void setTotalReproducoes(Long totalReproducoes) { this.totalReproducoes = totalReproducoes; }
}