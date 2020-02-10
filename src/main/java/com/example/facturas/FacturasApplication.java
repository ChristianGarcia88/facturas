package com.example.facturas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FacturasApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacturasApplication.class, args);
	}

}
