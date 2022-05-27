package com.microservicios.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicios.product.entities.Category;
import com.microservicios.product.service.CategoryService;

@RestController
@RequestMapping(value = "/api/categories/")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> listaCategorias = categoryService.listAllCategory();
		if (listaCategorias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listaCategorias);
	}

	@GetMapping(value = "{categoryID}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("categoryID") Long categoryId) {
		Category category = categoryService.findByIdCategory(categoryId);
		if (category != null) {
			return ResponseEntity.ok(category);
		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<Category> createcategory(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.createcategory(category));
	}

}
