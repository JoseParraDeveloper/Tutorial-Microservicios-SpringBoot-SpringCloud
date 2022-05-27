package com.microservicios.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.product.entities.Product;
import com.microservicios.product.service.ProductService;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> listProduct() {
		List<Product> listProduct = productService.listAllProduct();
		if (listProduct.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listProduct);
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return ResponseEntity.ok(productService.createProduct(product));

	}

}
