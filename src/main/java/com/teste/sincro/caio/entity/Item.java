package com.teste.sincro.caio.entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.naming.Name;
import java.math.BigDecimal;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String sku;
    private Integer quantidade;
    private BigDecimal preco;
    @ManyToOne (optional = false)
    @JoinColumn (name = "categoria_id")
    private Categoria categoria;

}
