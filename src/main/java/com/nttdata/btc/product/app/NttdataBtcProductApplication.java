package com.nttdata.btc.product.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NttdataBtcProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(NttdataBtcProductApplication.class, args);
	}

}
