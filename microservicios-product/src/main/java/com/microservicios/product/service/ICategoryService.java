package com.microservicios.product.service;

import java.util.Set;

import com.microservicios.product.dto.CategoryDto;
import com.microservicios.product.exceptions.CategoryNotFoundException;

public interface ICategoryService {

	public Set<CategoryDto> listAllCategory();

	public CategoryDto createCategory(CategoryDto categoryDto);

	public CategoryDto findByIdCategory(Long idCategory) throws CategoryNotFoundException;

	public void deleteByIdCategory(Long idCategory) throws CategoryNotFoundException;

	public CategoryDto updateCategory(CategoryDto categoryDto) throws CategoryNotFoundException;

}
