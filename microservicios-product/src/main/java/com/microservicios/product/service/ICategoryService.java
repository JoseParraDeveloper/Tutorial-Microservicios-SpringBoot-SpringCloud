package com.microservicios.product.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.microservicios.product.dto.CategoryDto;
import com.microservicios.product.exceptions.CategoryNotFoundException;

public interface ICategoryService {

	public Set<CategoryDto> listAllCategory();

	public Page<CategoryDto> pageCategory(Pageable pageable);

	public CategoryDto createCategory(CategoryDto categoryDto);

	public CategoryDto findByIdCategory(Long idCategory) throws CategoryNotFoundException;

	public void deleteByIdCategory(Long idCategory) throws CategoryNotFoundException;

	public CategoryDto updateCategory(CategoryDto categoryDto) throws CategoryNotFoundException;

}
