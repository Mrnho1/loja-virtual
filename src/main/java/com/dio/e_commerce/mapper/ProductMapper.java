package com.dio.e_commerce.mapper;

import com.dio.e_commerce.dto.ProductDTO;
import com.dio.e_commerce.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDTO dto);

    ProductDTO toDTO(Product product);

    default ProductDTO toDTOWithId(Product product) {
        if (product == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setNome(product.getNome());
        dto.setDescricao(product.getDescricao());
        dto.setPreco(product.getPreco());
        return dto;
    }
}
