package com.teste.sincro.caio.service;

import com.teste.sincro.caio.dto.ItemDTO;
import com.teste.sincro.caio.entity.Categoria;
import com.teste.sincro.caio.entity.Item;
import com.teste.sincro.caio.repository.CategoriaRepository;
import com.teste.sincro.caio.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoriaRepository categoriaRepository;

    public Item criar(ItemDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.BAD_REQUEST, "Categoria inexistente"));

        Item item = new Item();
        item.setNome(dto.getNome());
        item.setPreco(dto.getPreco());
        item.setQuantidade(dto.getQuantidade());
        item.setSku(dto.getSku());
        item.setCategoria(categoria);

        return itemRepository.save(item);
    }

    public List<Item> listarTodos() {
        return itemRepository.findAll();
    }


    public List<Item> buscarPorCategoria(Long categoriaId) {
        return itemRepository.findByCategoriaId(categoriaId);
    }


    public Item buscarPorId(Integer id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }


    public Item atualizar(Integer id, ItemDTO dto) {
        Item itemExistente = buscarPorId(id);
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente"));
        itemExistente.setNome(dto.getNome());
        itemExistente.setPreco(dto.getPreco());
        itemExistente.setQuantidade(dto.getQuantidade());
        itemExistente.setSku(dto.getSku());
        itemExistente.setCategoria(categoria);

        return itemRepository.save(itemExistente);
    }


    public void deletar(Integer id) {
        Item itemExistente = buscarPorId(id);
        itemRepository.delete(itemExistente);
    }
}