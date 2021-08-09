package com.pheonix.vending;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pheonix.vending.exception.VendingDataBaseException;
import com.pheonix.vending.model.Soda;
import com.pheonix.vending.service.dao.SodaDao;

@SpringBootApplication
public class VendingMachineApplication implements CommandLineRunner{
	
	protected static final Log LOGGER = LogFactory.getLog(VendingMachineApplication.class);

	@Autowired
	SodaDao sodaService;
	
	@Value("${item.id}")
	public int id;
	
	@Value("${item.name}")
	public String name;
	
	@Value("${item.cost}")
	public int cost;
	
	@Value("${item.count}")
	public int count;
	
	public static void main(String[] args) {
		SpringApplication.run(VendingMachineApplication.class, args);
		LOGGER.info("inside spring boot");
	}

	@Override
	public void run(String... args){
		Soda soda = new Soda();
		soda.setId(id);
		soda.setName(name);
		soda.setCost(cost);
		soda.setCount(count);
		try{
		sodaService.createSoda();
		if (sodaService.addSoda(soda) > 0) {
			LOGGER.info("****Soda item added successfully****");
		}
		}catch(VendingDataBaseException e){
			LOGGER.info("catched our custom exception");
		}catch(Exception ex){
			LOGGER.error("internal ");
		}
	}

}
