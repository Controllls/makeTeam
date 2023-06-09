package com.p2AE.pdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PdfApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfApplication.class, args);
	}

}
