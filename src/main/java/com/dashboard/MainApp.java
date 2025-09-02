package com.dashboard;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class MainApp {

	public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure()
                .directory("dashboard") // dashboard klasöründe .env arar
                .filename(".env") 
                .load();

        System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
        System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));

		SpringApplication.run(MainApp.class, args);
	}    
}



