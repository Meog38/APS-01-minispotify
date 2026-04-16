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
    public com.minispotify.api.dto.UsuarioResponseDTO criar(@RequestBody com.minispotify.api.dto.UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTipoPlano(dto.tipoPlano());
        usuario.setAtivo(true);
        usuario.setDataCriacao(java.time.LocalDateTime.now());
        
        Usuario criado = service.criar(usuario);
        return converterParaDTO(criado);
    }

    @GetMapping
    public List<com.minispotify.api.dto.UsuarioResponseDTO> listar() {
        return service.listar().stream()
                .map(this::converterParaDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public com.minispotify.api.dto.UsuarioResponseDTO buscar(@PathVariable Long id) {
        return converterParaDTO(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public com.minispotify.api.dto.UsuarioResponseDTO atualizar(@PathVariable Long id, @RequestBody com.minispotify.api.dto.UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setTipoPlano(dto.tipoPlano());
        usuario.setAtivo(true);
        
        Usuario atualizado = service.atualizar(id, usuario);
        return converterParaDTO(atualizado);
    }

    private com.minispotify.api.dto.UsuarioResponseDTO converterParaDTO(Usuario u) {
        if (u == null) return null;
        return new com.minispotify.api.dto.UsuarioResponseDTO(u.getId(), u.getNome(), u.getEmail(), u.getTipoPlano(), u.getDataCriacao());
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