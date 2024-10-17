package com.ims;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;





@SpringBootApplication
@EnableJpaAuditing
public class InventoryManagementSystem { 

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(InventoryManagementSystem.class, args);
		
		Logger logger  = LoggerFactory.getLogger(InventoryManagementSystem.class);
		
	    logger.info("InventoryManagementSystem  started ");
	}

}
