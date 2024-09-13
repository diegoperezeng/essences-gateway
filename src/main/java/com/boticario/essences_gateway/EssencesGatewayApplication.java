package com.boticario.essences_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EssencesGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EssencesGatewayApplication.class, args);
	}

}
