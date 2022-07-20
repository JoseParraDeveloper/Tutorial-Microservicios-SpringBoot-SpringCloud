package com.microservicios.product.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProductDto {
	private Long id;
	private String name;
	private String description;
	private Integer stock;
	private BigDecimal price;
	private Boolean enabled;
	private Date createAt;
	private CategoryDto category;

	public ProductDto() {
		super();
	}

	public ProductDto(Long id, String name, String description, Integer stock, BigDecimal price, Boolean enabled,
			Date createAt, CategoryDto category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.price = price;
		this.enabled = enabled;
		this.createAt = createAt;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategoryDto(CategoryDto category) {
		this.category = category;
	}

}
