package com.microservicios.product.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.microservicios.product.dto.CategoryDto;
import com.microservicios.product.entities.Category;
import com.microservicios.product.exceptions.BadRequestException;
import com.microservicios.product.exceptions.CategoryNotFoundException;
import com.microservicios.product.repository.ICategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;

	@Autowired
	@Qualifier("modelMapperCategory")
	private ModelMapper modelMapper;

	@Override
	public Set<CategoryDto> listAllCategory() {
		List<Category> listCategory = categoryRepository.findAll();
		return listCategory.stream().map(category -> modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Page<CategoryDto> pageCategory(Pageable pageable) {
		Page<Category> pageCategory = categoryRepository.findAll(pageable);
		List<CategoryDto> listCategoryDto = pageCategory.stream()
				.map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return new PageImpl<>(listCategoryDto);
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category newCategoryEntity = modelMapper.map(categoryDto, Category.class);
		newCategoryEntity = categoryRepository.save(newCategoryEntity);
		return modelMapper.map(newCategoryEntity, CategoryDto.class);
	}

	@Override
	public CategoryDto findByIdCategory(Long idCategory) throws CategoryNotFoundException {
		Optional<Category> optionalCategory = categoryRepository.findById(idCategory);
		return modelMapper.map(
				optionalCategory.orElseThrow(() -> new CategoryNotFoundException(this.getMessage(idCategory))),
				CategoryDto.class);
	}

	@Override
	public void deleteByIdCategory(Long idCategory) throws CategoryNotFoundException {
		Optional<Category> optionalCategory = categoryRepository.findById(idCategory);
		optionalCategory.ifPresentOrElse(category -> categoryRepository.deleteById(category.getId()), () -> {
			throw new CategoryNotFoundException(this.getMessage(idCategory));
		});

	}

	@Override
	public CategoryDto updateCategory(CategoryDto updateCategoryDto) throws CategoryNotFoundException {
		Long idCategory = updateCategoryDto.getId();
		if (idCategory != null) {
			Optional<Category> optionalCategory = categoryRepository.findById(idCategory);
			if (!optionalCategory.isPresent()) {
				throw new CategoryNotFoundException(this.getMessage(idCategory));
			}
		} else {
			throw new BadRequestException("id " + idCategory);
		}
		return this.createCategory(updateCategoryDto);
	}

	private String getMessage(Long id) {
		return "Category by id " + id + " was not found.";
	}

}
