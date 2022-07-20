package com.microservicios.product.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.product.dto.CategoryDto;
import com.microservicios.product.dto.ProductDto;
import com.microservicios.product.entities.Category;
import com.microservicios.product.entities.Product;
import com.microservicios.product.exceptions.BadRequestException;
import com.microservicios.product.exceptions.ProductNotFoundException;
import com.microservicios.product.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductRepository productRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ProductDto> listAllProduct() {

		List<Product> listProducts = productRepository.findAll();
		return listProducts.stream().map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductById(Long idProduct) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(idProduct);
		return modelMapper.map(
				optionalProduct.orElseThrow(() -> new ProductNotFoundException(this.getMessage(idProduct))),
				ProductDto.class);
	}

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		productDto.setCreateAt(new Date());
		productDto.setEnabled(true);
		Product newProduct = modelMapper.map(productDto, Product.class);
		newProduct = productRepository.save(newProduct);
		return modelMapper.map(newProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto updateProductDto) throws ProductNotFoundException, BadRequestException {
		Long idProduct = updateProductDto.getId();
		if (idProduct != null) {
			Optional<Product> optionalProduct = productRepository.findById(idProduct);
			if (!optionalProduct.isPresent()) {
				throw new ProductNotFoundException(this.getMessage(idProduct));
			}
		} else {
			throw new BadRequestException("id " + idProduct);
		}
		return this.createProduct(updateProductDto);
	}

	@Override
	public void deleteProductById(Long idProduct) {
		Optional<Product> optionalProduct = productRepository.findById(idProduct);
		optionalProduct.ifPresentOrElse(product -> productRepository.deleteById(product.getId()), () -> {
			throw new ProductNotFoundException(this.getMessage(idProduct));
		});

	}

	@Override
	public List<ProductDto> findByCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		List<Product> listProductByCategory = productRepository.findByCategory(category);
		return listProductByCategory.stream().map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());

	}

	@Override
	public ProductDto updateStock(Long id, Integer quantity) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (!optionalProduct.isPresent()) {
			throw new ProductNotFoundException(this.getMessage(id));
		}
		Product product = optionalProduct.get();
		Integer updateStock = product.getStock() + quantity;
		if (updateStock.intValue() < 0) {
			throw new BadRequestException("can not update stock to a negative quantity");
		}
		product.setStock(updateStock);
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		return this.createProduct(productDto);

	}

	private String getMessage(Long id) {
		return "Category by id " + id + " was not found.";
	}

}
