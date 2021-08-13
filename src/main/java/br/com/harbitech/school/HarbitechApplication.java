package br.com.harbitech.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HarbitechApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarbitechApplication.class, args);
	}
}
