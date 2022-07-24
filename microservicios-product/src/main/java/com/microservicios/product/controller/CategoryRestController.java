package com.microservicios.product.controller;

import java.util.Set;

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
import com.microservicios.product.exceptions.BadRequestException;
import com.microservicios.product.exceptions.CategoryNotFoundException;
import com.microservicios.product.service.ICategoryService;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryRestController {
	@Autowired
	private ICategoryService categoryService;
	@Value("${sizePageCategory}")
	private int sizePageCategory;

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAllCategories() {
		Set<CategoryDto> listCategories = categoryService.listAllCategory();
		if (listCategories.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There are no categories.");
		}
		return ResponseEntity.ok(listCategories);
	}

	@GetMapping(value = "/page/{page}")
	public ResponseEntity<?> getCategoriesByPage(@PathVariable Integer page) {
		Page<CategoryDto> pageCategories = categoryService.pageCategory(PageRequest.of(page - 1, sizePageCategory));
		if (pageCategories.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There are no categories in the page: " + page);
		}
		return ResponseEntity.ok(pageCategories);
	}

	@GetMapping(value = "/{categoryID}")
	public ResponseEntity<?> getCategoryById(@PathVariable("categoryID") Long categoryId) {
		try {
			CategoryDto category = categoryService.findByIdCategory(categoryId);
			return ResponseEntity.ok(category);
		} catch (CategoryNotFoundException categoryNotFoundException) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(categoryNotFoundException.getMessage());
		}
	}

	@PostMapping(value = "/create")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
		return ResponseEntity.ok(categoryService.createCategory(categoryDto));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Long idCategory) {
		try {
			categoryService.deleteByIdCategory(idCategory);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (CategoryNotFoundException categoryNotFoundException) {
			return new ResponseEntity<>(categoryNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto) {
		try {
			CategoryDto updateCategoryDto = categoryService.updateCategory(categoryDto);
			return new ResponseEntity<>(updateCategoryDto, HttpStatus.OK);
		} catch (CategoryNotFoundException categoryNotFoundException) {
			return new ResponseEntity<>(categoryNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		} catch (BadRequestException badRequestException) {
			return new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
