package com.dio.e_commerce.service;

import com.dio.e_commerce.dto.ProductDTO;
import com.dio.e_commerce.mapper.ProductMapper;
import com.dio.e_commerce.model.Product;
import com.dio.e_commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final ProductMapper mapper;

    /**
     * Cadastra um novo produto e retorna o DTO com ID e createdAt preenchidos.
     */
    public ProductDTO create(ProductDTO dto) {
        Product product = mapper.toEntity(dto);
        product = repo.save(product);
        return mapper.toDTO(product);
    }

    /**
     * Retorna todos os produtos ordenados do mais recente para o mais antigo.
     */
    public List<ProductDTO> listAll() {
        return repo.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Deleta um produto pelo ID.
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }

    /**
     * Busca a entidade Produto completa por ID (útil internamente).
     */
    public Product findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    /**
     * Atualiza um produto existente.
     */
    public ProductDTO update(Long id, ProductDTO dto) {
        Product existingProduct = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        existingProduct.setNome(dto.getNome());
        existingProduct.setDescricao(dto.getDescricao());
        existingProduct.setPreco(dto.getPreco());
        existingProduct.setImageUrl(dto.getImageUrl());

        Product updatedProduct = repo.save(existingProduct);
        return mapper.toDTO(updatedProduct);
    }
}
