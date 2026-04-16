package com.minispotify.api.dto;

import com.minispotify.api.enums.TipoPlano;

public record UsuarioCreateDTO(
    String nome,
    String email,
    TipoPlano tipoPlano
) {}
