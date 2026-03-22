package com.minispotify.api.dto;

public record TopMusicaDTO(
    String titulo,
    String nomeArtista,
    Long totalReproducoes
) {}
