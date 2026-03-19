package com.teste.sincro.caio.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriaDTO {
    @NotNull
    private String nome;
    @NotNull
    private String descricao;
}
