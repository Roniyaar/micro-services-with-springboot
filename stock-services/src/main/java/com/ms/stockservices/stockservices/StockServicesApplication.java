package com.ms.stockservices.stockservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class StockServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockServicesApplication.class, args);
	}

}
