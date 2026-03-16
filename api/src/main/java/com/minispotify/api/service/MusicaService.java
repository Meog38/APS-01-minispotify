package com.minispotify.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.minispotify.api.model.Musica;

@Service
public class MusicaService {

    private List<Musica> musicas = new ArrayList<>();
    private Long contadorId = 1L;

    public Musica criar(Musica musica) {
        musica.setId(contadorId++);
        musicas.add(musica);
        return musica;
    }

    public List<Musica> listar() {
        return musicas;
    }

    public Musica buscarPorId(Long id) {
        return musicas.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
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

        return musica;
    }

    public boolean deletar(Long id) {

        Musica musica = buscarPorId(id);

        if (musica == null) {
            return false;
        }

        musicas.remove(musica);
        return true;
    }

    public Musica reproduzir(Long id) {

        Musica musica = buscarPorId(id);

        if (musica == null) {
            return null;
        }

        musica.setTotalReproducoes(musica.getTotalReproducoes() + 1);

        return musica;
    }
}