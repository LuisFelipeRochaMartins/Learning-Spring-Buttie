package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student luis = new Student(
                    "Luís Felipe Rocha Martins",
                    "luisfelipetochamartins@gmail.com",
                    LocalDate.of(2003, Month.MAY, 27)
            );
            Student merilo = new Student(
                    "Merilo algo",
                    "merilo@gmail.com",
                    LocalDate.of(2003, Month.AUGUST, 18)
            );
            repository.saveAll(
                    List.of(luis, merilo)
            );
        };
    }

}
