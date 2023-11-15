package com.example.Proj1_2019202021;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.io.File;

@SpringBootApplication
@EnableJpaAuditing
public class Proj12019202021Application {

    public static void main(String[] args) {
		SpringApplication.run(Proj12019202021Application.class, args);
    }

    @Bean
    public HiddenHttpMethodFilter httpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
