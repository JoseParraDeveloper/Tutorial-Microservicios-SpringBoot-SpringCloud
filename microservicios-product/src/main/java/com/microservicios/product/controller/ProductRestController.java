package com.microservicios.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.product.dto.CategoryDto;
import com.microservicios.product.dto.ProductDto;
import com.microservicios.product.exceptions.BadRequestException;
import com.microservicios.product.exceptions.ProductNotFoundException;
import com.microservicios.product.service.IProductService;

@RestController
@RequestMapping(value = "/api/product")
public class ProductRestController {
	@Autowired
	private IProductService productService;

	@Value("${sizePageProduct}")
	private int sizePageProduct;

	@GetMapping(value = "/all")
	public ResponseEntity<List<ProductDto>> listProduct() {
		List<ProductDto> listProduct = productService.listAllProduct();
		if (listProduct.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listProduct);
	}

	@GetMapping(value = "/page/{page}")
	public ResponseEntity<?> pageProduct(@PathVariable Integer page) {
		Page<ProductDto> pageProducts = productService.pageProduct(PageRequest.of(page - 1, sizePageProduct));
		if (pageProducts.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pageProducts);
	}

	@PostMapping(value = "/create")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
		return ResponseEntity.ok(productService.createProduct(productDto));
	}

	@GetMapping(value = "{productID}")
	public ResponseEntity<?> getProductById(@PathVariable("productID") Long idProduct) {
		try {
			ProductDto productDto = productService.getProductById(idProduct);
			return ResponseEntity.ok(productDto);
		} catch (ProductNotFoundException productNotFoundException) {
			return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{idProduct}")
	public ResponseEntity<?> deleteProduct(@PathVariable("idProduct") Long idProduct) {
		try {
			productService.deleteProductById(idProduct);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ProductNotFoundException productNotFoundException) {
			return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto) {
		try {
			ProductDto updateProductDto = productService.updateProduct(productDto);
			return new ResponseEntity<>(updateProductDto, HttpStatus.OK);
		} catch (ProductNotFoundException productNotFoundException) {
			return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		} catch (BadRequestException badRequestException) {
			return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/allProductByCategory")
	public ResponseEntity<List<ProductDto>> listProductByCategory(@RequestBody CategoryDto categoryDto) {
		List<ProductDto> listProductByCategory = productService.findByCategory(categoryDto);
		if (listProductByCategory.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listProductByCategory);
	}

	@PutMapping("/updateStockProduct/{id}/{quantity}")
	public ResponseEntity<?> updateStockProduct(@PathVariable Map<String, String> pathVarsMap) {
		try {
			ProductDto updateProductDto = productService.updateStock(Long.valueOf(pathVarsMap.get("id")),
					Integer.valueOf(pathVarsMap.get("quantity")));
			return new ResponseEntity<>(updateProductDto, HttpStatus.OK);
		} catch (ProductNotFoundException productNotFoundException) {
			return new ResponseEntity<>(productNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		} catch (BadRequestException badRequestException) {
			return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
