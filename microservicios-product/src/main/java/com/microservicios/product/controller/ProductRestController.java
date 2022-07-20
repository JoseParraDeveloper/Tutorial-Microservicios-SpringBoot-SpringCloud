package com.microservicios.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.product.dto.ProductDto;
import com.microservicios.product.service.IProductService;

@RestController
@RequestMapping(value = "/api/products")
public class ProductRestController {
	@Autowired
	private IProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDto>> listProduct() {
		List<ProductDto> listProduct = productService.listAllProduct();
		if (listProduct.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listProduct);
	}

	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
		return ResponseEntity.ok(productService.createProduct(productDto));

	}

}
