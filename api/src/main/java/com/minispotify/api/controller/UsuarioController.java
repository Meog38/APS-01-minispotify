package com.minispotify.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.minispotify.api.model.Curtida;
import com.minispotify.api.model.HistoricoReproducao;
import com.minispotify.api.model.Usuario;
import com.minispotify.api.service.CurtidaService;
import com.minispotify.api.service.HistoricoReproducaoService;
import com.minispotify.api.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final HistoricoReproducaoService historicoService;
    private final CurtidaService curtidaService;

    public UsuarioController(UsuarioService service, HistoricoReproducaoService historicoService, CurtidaService curtidaService) {
        this.service = service;
        this.historicoService = historicoService;
        this.curtidaService = curtidaService;
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return service.criar(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return service.atualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @GetMapping("/{id}/historico")
    public List<HistoricoReproducao> historico(@PathVariable Long id) {
        return historicoService.listarPorUsuario(id);
    }

    @GetMapping("/{id}/favoritas")
    public List<Curtida> favoritas(@PathVariable Long id) {
        return curtidaService.listarFavoritasPorUsuario(id);
    }
}