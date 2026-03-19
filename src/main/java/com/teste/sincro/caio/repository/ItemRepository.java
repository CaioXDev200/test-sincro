package com.teste.sincro.caio.repository;

import com.teste.sincro.caio.entity.Categoria;
import com.teste.sincro.caio.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {
    List<Item> findByCategoriaId(Long categoriaId);
}
