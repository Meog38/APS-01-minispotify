package com.minispotify.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.minispotify.api.model.Artista;

@Service
public class ArtistaService {

    private List<Artista> artistas = new ArrayList<>();
    private Long contadorId = 1L;

    public Artista criar(Artista artista) {
        artista.setId(contadorId++);
        artistas.add(artista);
        return artista;
    }

    public List<Artista> listar() {
        return artistas;
    }

    public Artista buscarPorId(Long id) {
        return artistas.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Artista atualizar(Long id, Artista artistaAtualizado) {

        Artista artista = buscarPorId(id);

        if (artista == null) {
            return null;
        }

        artista.setNome(artistaAtualizado.getNome());
        artista.setGeneroMusical(artistaAtualizado.getGeneroMusical());
        artista.setPaisOrigem(artistaAtualizado.getPaisOrigem());

        return artista;
    }

    public boolean deletar(Long id) {

        Artista artista = buscarPorId(id);

        if (artista == null) {
            return false;
        }

        artistas.remove(artista);
        return true;
    }
}