package br.com.ribeiroribas.worldcupqatar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorldCupQatarApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorldCupQatarApplication.class, args);
		System.out.println(System.getProperty("java.class.path"));
	}

}
