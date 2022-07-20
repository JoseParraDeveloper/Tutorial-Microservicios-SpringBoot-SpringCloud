package com.microservicios.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicios.product.entities.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
