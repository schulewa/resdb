package com.apschulewitz.resdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
  "com.apschulewitz.resdb.common.model",
  "com.apschulewitz.resdb.config.model",
  "com.apschulewitz.resdb.refdata.model",
  "com.apschulewitz.resdb.security.model"
})
@EnableJpaRepositories
public class ResearchDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResearchDatabaseApplication.class, args);
	}

}

// config, security,
