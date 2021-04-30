package com.groun24.quarantinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@ComponentScan({"com.groun24.quarantinder.controller"})

public class QuarantinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuarantinderApplication.class, args);
	}

}
