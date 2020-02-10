package com.example.facturas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.facturas.utilerias.Fechas;

@Configuration
public class Configuracion {
	
	@Bean
	public Fechas  fechaActual() {
		
		
	return 	new Fechas();
		
	}

}
