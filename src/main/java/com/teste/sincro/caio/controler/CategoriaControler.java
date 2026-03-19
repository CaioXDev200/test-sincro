package com.teste.sincro.caio.controler;

import com.teste.sincro.caio.dto.CategoriaDTO;
import com.teste.sincro.caio.entity.Categoria;
import com.teste.sincro.caio.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/categorias")
@RequiredArgsConstructor
public class CategoriaControler {
    private final CategoriaService categoriaService;
    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.criar(categoriaDTO));
    }
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        List<Categoria> categorias = categoriaService.listarTodas();
        return ResponseEntity.ok(categorias);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoria);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@Valid @PathVariable Integer id, @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoriaAtualizada = categoriaService.atualizar(id, categoriaDTO);
        return ResponseEntity.ok(categoriaAtualizada);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
