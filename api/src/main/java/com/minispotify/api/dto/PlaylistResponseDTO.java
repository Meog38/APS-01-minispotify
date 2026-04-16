package com.minispotify.api.dto;

import java.util.List;
import java.time.LocalDateTime;

public record PlaylistResponseDTO(
    Long id,
    String nome,
    boolean publica,
    LocalDateTime dataCriacao,
    String nomeDono,
    List<String> musicas
) {}
