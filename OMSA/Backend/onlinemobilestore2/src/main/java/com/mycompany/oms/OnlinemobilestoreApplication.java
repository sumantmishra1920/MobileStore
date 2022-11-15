package com.mycompany.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.mycompany.oms"})
public class OnlinemobilestoreApplication{

	public static void main(String[] args) {
		SpringApplication.run(OnlinemobilestoreApplication.class, args);
	}

}
