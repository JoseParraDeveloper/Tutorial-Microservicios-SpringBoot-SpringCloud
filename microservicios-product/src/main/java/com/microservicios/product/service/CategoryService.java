package com.microservicios.product.service;

import java.util.List;

import com.microservicios.product.entities.Category;

public interface CategoryService {

	public List<Category> listAllCategory();

	public Category createcategory(Category category);

	public Category findByIdCategory(Long id);

}
