package com.dio.e_commerce.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDTO {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String imageUrl;
    private LocalDateTime createdAt;
}