package com.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// C:\workspace\github\spring_annotations\src\main\java\com\basics\BasicsApplication.java
// $ mvn spring-boot:run
// locate to localhost:8080/basics 

@SpringBootApplication
public class BasicsApplication {
	//
    public static void main( String[] args ) {
		//
		System.out.println( "\u001b[32;1m" + "HELLO" + "\u001b[0m" ); //  \u001B[31m
        SpringApplication.run(BasicsApplication.class, args);
    }
}