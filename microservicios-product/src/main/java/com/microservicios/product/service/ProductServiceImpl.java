package com.microservicios.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.product.entities.Category;
import com.microservicios.product.entities.Product;
import com.microservicios.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> listAllProduct() {

		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product createProduct(Product product) {
		product.setCreateAt(new Date());
		product.setEnabled(true);
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product deleteProduct(Long id) {
		Product productDB = this.getProduct(id);
		if (productDB != null) {
			productDB.setEnabled(false);
			return productRepository.save(productDB);
		}
		return null;

	}

	@Override
	public List<Product> findByCategory(Category category) {

		return productRepository.findByCategory(category);
	}

	@Override
	public Product updateStock(Long id, Integer quantity) {
		Product productDB = this.getProduct(id);
		if (productDB != null) {
			Integer updateStock = productDB.getStock() + quantity;
			productDB.setStock(updateStock);
			return productRepository.save(productDB);
		}
		return null;

	}

}
