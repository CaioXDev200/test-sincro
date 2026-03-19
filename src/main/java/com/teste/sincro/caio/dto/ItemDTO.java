package com.teste.sincro.caio.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Data
public class ItemDTO {
    @NotNull
    private String nome;
    @NotNull
    private String sku;
    @NotNull
    private Integer quantidade;
    @NotNull
    private BigDecimal preco;
    @NotNull
    private Integer categoriaId;
}

