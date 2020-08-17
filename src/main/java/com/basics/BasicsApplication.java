package com.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import static com.basics.util.UtilityMain.GREEN;
// import static com.basics.util.UtilityMain.RESET;

// C:\workspace\github\spring_annotations\src\main\java\com\basics\BasicsApplication.java
// $ mvn spring-boot:run
// locate to localhost:8080/basics 

/*
	@SpringBootApplication is a convenience annotation that adds all this:
	
	@Configuration				: tags class as a source of bean definitions for application context
	@EnableAutoConfiguration	: adds beans based on classpath settings, other beans, etc.
	@EnableWebMvc				: setting up a DispatcherServlet if spring-webmvc is on classpath
	@ComponentScan				: tells Spring to look for components, configurations, services in package
*/
@SpringBootApplication
public class BasicsApplication {
	//
    public static void main( String[ ] args ) {
		//
		System.out.println( "HELLO from BasicsApplication" ); //  \u001B[31m
        SpringApplication.run( BasicsApplication.class, args );
    }
}