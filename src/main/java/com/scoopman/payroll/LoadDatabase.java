
package com.scoopman.payroll;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
@Component
@Configuration
@Slf4j
class LoadDatabase {

	@Value("classpath:/resourcetracker.csv")
	private Resource csvResource;
	
	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Employee("Steve", "Coopman", "02/23/1996", "9722134785", "1312 Thistledown Dr, Plano Tx 75093")));
			//log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(csvResource.getInputStream()));
		    String line = null;
		    
		    while ((line = reader.readLine()) != null) {
		      // ... parse the csv line into an object ...
		      String[] empData = line.split(",");

		      log.info("emp data: " + empData[0] + " " + empData[1] + " " + empData[2] + " " + empData[3] + " " + empData[4] + " " + empData[5]);
		      repository.save(new Employee(empData[1], empData[2], empData[3], empData[4], empData[5]));//hack for non required fields.. should add another constructor..
		    }
		    
		    reader.close();
			
		};
	}
}