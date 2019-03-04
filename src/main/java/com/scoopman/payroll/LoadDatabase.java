package com.scoopman.payroll;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Employee("Steve", "Coopman", "02/23/1996", "9722134785", "1312 Thistledown Dr, Plano Tx 75093")));
			//log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
		};
	}
}