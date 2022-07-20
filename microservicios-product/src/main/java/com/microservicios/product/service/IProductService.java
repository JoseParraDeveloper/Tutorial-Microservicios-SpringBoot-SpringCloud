package com.microservicios.product.service;

import java.util.List;

import com.microservicios.product.dto.CategoryDto;
import com.microservicios.product.dto.ProductDto;
import com.microservicios.product.exceptions.ProductNotFoundException;

public interface IProductService {

	public List<ProductDto> listAllProduct();

	public ProductDto getProductById(Long idProduct) throws ProductNotFoundException;

	public ProductDto createProduct(ProductDto productDto);

	public ProductDto updateProduct(ProductDto productDto) throws ProductNotFoundException;

	public void deleteProductById(Long idProduct) throws ProductNotFoundException;

	public List<ProductDto> findByCategory(CategoryDto categoryDto);

	public ProductDto updateStock(Long idProduct, Integer quantity) throws ProductNotFoundException;

}
