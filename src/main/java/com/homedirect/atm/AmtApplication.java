package com.homedirect.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.homedirect.atm")
public class AmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmtApplication.class, args);
	}
}
