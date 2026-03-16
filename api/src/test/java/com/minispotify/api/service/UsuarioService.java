package com.minispotify.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.minispotify.api.model.Usuario;

@Service
public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();
    private Long contadorId = 1L;

    public Usuario criar(Usuario usuario) {
        usuario.setId(contadorId++);
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> listar() {
        return usuarios;
    }

    public Usuario buscarPorId(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = buscarPorId(id);

        if (usuario == null) {
            return null;
        }

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setTipoPlano(usuarioAtualizado.getTipoPlano());
        usuario.setAtivo(usuarioAtualizado.isAtivo());

        return usuario;
    }

    public boolean deletar(Long id) {
        Usuario usuario = buscarPorId(id);

        if (usuario == null) {
            return false;
        }

        usuario.setAtivo(false);
        return true;
    }
}