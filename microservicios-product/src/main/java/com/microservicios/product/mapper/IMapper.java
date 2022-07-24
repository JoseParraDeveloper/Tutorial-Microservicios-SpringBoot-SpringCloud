package com.microservicios.product.mapper;

import org.modelmapper.ModelMapper;

@FunctionalInterface
public interface IMapper {
	public ModelMapper getModelMapper();
}
