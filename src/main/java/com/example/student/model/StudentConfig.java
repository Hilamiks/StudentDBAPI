package com.example.student.model;

import com.example.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student john = new Student(
					"John Doe",
					"johndoe@gmail.com",
					LocalDate.of(1997,3,16)
			);

			Student marry = new Student(
					"Marry Doe",
					"marrydoe@gmail.com",
					LocalDate.of(1998,5,1)
			);

			repository.saveAll(List.of(john, marry));


		};
	}
}

