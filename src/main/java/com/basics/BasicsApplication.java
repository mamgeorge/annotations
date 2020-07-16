package com.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import static com.basics.util.UtilityMain.GREEN;
// import static com.basics.util.UtilityMain.RESET;

// C:\workspace\github\spring_annotations\src\main\java\com\basics\BasicsApplication.java
// $ mvn spring-boot:run
// locate to localhost:8080/basics 

@SpringBootApplication
public class BasicsApplication {
	//
    public static void main( String[] args ) {
		//
		System.out.println( "HELLO from BasicsApplication" ); //  \u001B[31m
        SpringApplication.run(BasicsApplication.class, args);
    }
}