package com.dio.e_commerce.controller;

import com.dio.e_commerce.model.Cart;
import com.dio.e_commerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @PreAuthorize("hasRole('CLIENTE')")
    @PostMapping("/add/{productId}")
    public ResponseEntity<Cart> addProduct(@PathVariable Long productId, Authentication auth) {
        Cart updatedCart = service.addProductToCart(productId, auth);
        return ResponseEntity.ok(updatedCart);
    }
}
