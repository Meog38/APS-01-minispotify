package com.minispotify.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.minispotify.api.model.Album;

@Service
public class AlbumService {

    private List<Album> albuns = new ArrayList<>();
    private Long contadorId = 1L;

    public Album criar(Album album) {
        album.setId(contadorId++);
        albuns.add(album);
        return album;
    }

    public List<Album> listar() {
        return albuns;
    }

    public Album buscarPorId(Long id) {
        return albuns.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Album atualizar(Long id, Album albumAtualizado) {

        Album album = buscarPorId(id);

        if (album == null) {
            return null;
        }

        album.setTitulo(albumAtualizado.getTitulo());
        album.setDataLancamento(albumAtualizado.getDataLancamento());
        album.setArtista(albumAtualizado.getArtista());

        return album;
    }

    public boolean deletar(Long id) {

        Album album = buscarPorId(id);

        if (album == null) {
            return false;
        }

        albuns.remove(album);
        return true;
    }
}