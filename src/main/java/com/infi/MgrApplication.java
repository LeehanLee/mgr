package com.infi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MgrApplication {

	public static void main(String[] args) {
		// EntitiesGenerator gen = new
		// EntitiesGenerator("jdbc:mysql://localhost:3306", "qifei", "root",
		// "123456");
		// gen.Generate();

		SpringApplication.run(MgrApplication.class, args);
	}
}
