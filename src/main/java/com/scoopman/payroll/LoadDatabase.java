
package com.scoopman.payroll;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Date;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
@Component
@Configuration
@Slf4j
class LoadDatabase {

	@Value("classpath:/resourcetracker.csv")
	private Resource csvResource;
	@Value("classpath:/resourcetracker2.csv")
	private Resource csvResource2;
	
	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args -> {
			//log.info("Preloading " + repository.save(new Employee("Steve", "Coopman", "02/23/1996", "9722134785", "1312 Thistledown Dr, Plano Tx 75093")));
			//log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(csvResource.getInputStream()));
		    String line = null;
		    
		    while ((line = reader.readLine()) != null) {
		      // ... parse the csv line into an object ...
		      String[] empData = line.split(",");

		     // log.info("emp data: " + empData[0] + " " + empData[1] + " " + empData[2] + " " + empData[3] + " " + empData[4] + " " + empData[5]);
		      //repository.save(new Employee(empData[1], empData[2], empData[3], empData[4], empData[5]));//hack for non required fields.. should add another constructor..
		    }
		    
		    reader.close();
		    
		    List<List<String>> records = new ArrayList<List<String>>();
		    try (CSVReader csvReader = new CSVReader(new InputStreamReader(csvResource2.getInputStream()));) {
		        String[] values = null;
		        while ((values = csvReader.readNext()) != null) {
		            records.add(Arrays.asList(values));
		        }
		    }

		    ListIterator<List<String>> iterator = records.listIterator();
		    while(iterator.hasNext()) {
		    List<String> empStringList = (List<String>) iterator.next();
		    String[] empData2 = (String[]) empStringList.toArray();
		    Employee temp = new Employee(empData2[1], empData2[2], empData2[3], empData2[4], empData2[5], empData2[6], empData2[7], empData2[8], empData2[9], empData2[10], empData2[11]);
		    
		    log.info("empStringList size: " + empStringList.size());
		    }
		    
		};
	}
}