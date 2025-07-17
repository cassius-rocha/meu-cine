package com.api.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("TMDB_API_TOKEN", dotenv.get("TMDB_API_TOKEN"));

		SpringApplication.run(BackendApplication.class, args);
	}

}
