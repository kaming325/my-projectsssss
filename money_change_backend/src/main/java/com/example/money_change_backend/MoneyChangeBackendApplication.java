package com.example.money_change_backend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@SpringBootApplication
public class MoneyChangeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyChangeBackendApplication.class, args);
	}


}
