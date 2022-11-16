package com.udemy.expert.exercicios;

import com.udemy.expert.exercicios.domain.entity.Cliente;
import com.udemy.expert.exercicios.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ExerciciosApplication {


	public static void main(String[] args) {

		SpringApplication.run(ExerciciosApplication.class, args);
	}

}
