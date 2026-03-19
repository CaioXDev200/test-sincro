package com.teste.sincro.caio.controler;

import com.teste.sincro.caio.dto.ItemDTO;
import com.teste.sincro.caio.entity.Item;
import com.teste.sincro.caio.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> criar(@RequestBody ItemDTO itemDTO) {
        if (itemDTO.getCategoriaId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Item novoItem = itemService.criar(itemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoItem);
    }

    @GetMapping
    public ResponseEntity<List<Item>> listar(
            @RequestParam(value = "categoriaId", required = false) Long categoriaId) {

        if (categoriaId != null) {
            List<Item> items = itemService.buscarPorCategoria(categoriaId);
            if (items.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(items);
        }
        return ResponseEntity.ok(itemService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(itemService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> atualizar(@PathVariable Integer id, @Valid @RequestBody ItemDTO itemDTO) {
        return ResponseEntity.ok(itemService.atualizar(id, itemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        itemService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}