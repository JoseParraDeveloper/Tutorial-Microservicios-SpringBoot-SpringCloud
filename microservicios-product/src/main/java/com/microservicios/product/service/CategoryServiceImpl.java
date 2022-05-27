package com.microservicios.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicios.product.entities.Category;
import com.microservicios.product.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> listAllCategory() {

		return categoryRepository.findAll();
	}

	@Override
	public Category createcategory(Category category) {

		return categoryRepository.save(category);
	}

	@Override
	public Category findByIdCategory(Long id) {

		return categoryRepository.findById(id).orElse(null);
	}

}
