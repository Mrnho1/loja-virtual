package com.dio.e_commerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
}