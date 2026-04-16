package com.minispotify.api.dto;

import com.minispotify.api.enums.TipoPlano;
import java.time.LocalDateTime;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String email,
    TipoPlano tipoPlano,
    LocalDateTime dataCriacao
) {}
