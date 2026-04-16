package com.minispotify.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.minispotify.api.model.Usuario;

import com.minispotify.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
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

        return usuarioRepository.save(usuario);
    }

    public boolean deletar(Long id) {
        Usuario usuario = buscarPorId(id);

        if (usuario == null) {
            return false;
        }

        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
        return true;
    }
}