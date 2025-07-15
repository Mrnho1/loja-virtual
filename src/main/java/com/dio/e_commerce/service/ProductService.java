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

    public ProductDTO create(ProductDTO dto) {
        Product product = mapper.toEntity(dto);
        product = repo.save(product);
        return mapper.toDTO(product);
    }

    public List<ProductDTO> listAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Product findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public ProductDTO update(Long id, ProductDTO dto) {
        Product existingProduct = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        existingProduct.setNome(dto.getNome());
        existingProduct.setDescricao(dto.getDescricao());
        existingProduct.setPreco(dto.getPreco());

        Product updatedProduct = repo.save(existingProduct);
        return mapper.toDTO(updatedProduct);
    }

}
