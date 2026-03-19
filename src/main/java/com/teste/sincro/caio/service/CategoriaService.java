package com.teste.sincro.caio.service;

import com.teste.sincro.caio.dto.CategoriaDTO;
import com.teste.sincro.caio.entity.Categoria;
import com.teste.sincro.caio.repository.CategoriaRepository;
import com.teste.sincro.caio.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
  private final CategoriaRepository categoriaRepository;
  private final ItemRepository itemRepository;
  public Categoria criar (CategoriaDTO categoriaDTO){
      Categoria categoria = new Categoria();
      categoria.setNome(categoriaDTO.getNome());
      categoria.setDescricao(categoriaDTO.getDescricao());
      return categoriaRepository.save(categoria);
  }
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }
    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }
    public Categoria atualizar(Integer id, CategoriaDTO categoriaDTO) {
        Categoria categoriaExistente = buscarPorId(id);
        categoriaExistente.setNome(categoriaDTO.getNome());
        categoriaExistente.setDescricao(categoriaDTO.getDescricao());

        return categoriaRepository.save(categoriaExistente);
    }
    public void deletar(Integer id) {
        if (!itemRepository.findByCategoriaId(Long.valueOf(id)).isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria com items");
        }
        Categoria categoriaExistente = buscarPorId(id);
        categoriaRepository.delete(categoriaExistente);
    }
}