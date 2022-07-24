package com.microservicios.product.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservicios.product.mapper.IMapper;

@Configuration
public class MicroserviciosProductConfiguration {

	private IMapper modelMapperProduct = () -> {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper;
	};

	private IMapper modelMapperCategory = ModelMapper::new;

	@Bean
	public ModelMapper modelMapperProduct() {
		return modelMapperProduct.getModelMapper();
	}

	@Bean
	public ModelMapper modelMapperCategory() {
		return modelMapperCategory.getModelMapper();
	}

}
