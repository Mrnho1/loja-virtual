package com.dio.e_commerce.mapper;

import com.dio.e_commerce.dto.ProductDTO;
import com.dio.e_commerce.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDTO dto);
    ProductDTO toDTO(Product product);
}