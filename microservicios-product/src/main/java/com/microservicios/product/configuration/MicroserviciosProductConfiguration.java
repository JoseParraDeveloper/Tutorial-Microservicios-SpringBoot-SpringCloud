package com.microservicios.product.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicroserviciosProductConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
