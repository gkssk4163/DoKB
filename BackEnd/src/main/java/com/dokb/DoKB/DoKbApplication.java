package com.dokb.DoKB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.dokb.DoKB"})
public class DoKbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoKbApplication.class, args);
	}

}
