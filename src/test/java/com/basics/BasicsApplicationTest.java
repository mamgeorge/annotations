package com.basics;

import static com.basics.util.UtilityMain.GREEN;
import static com.basics.util.UtilityMain.RESET;
import static com.basics.util.UtilityMain.PAR;
import static com.basics.util.UtilityMain.LOGGER;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringRunner.class)
// @SpringBootTest
public class BasicsApplicationTest {
	//
    public static void main( String[] args )
	{ System.out.println( GREEN + "BasicsApplicationTest.main" + RESET ); }

	@Test
	public void sample( ) {
		//
		String txtLine = GREEN + "BasicsApplicationTest.sample" + RESET;
		LOGGER.info( PAR + txtLine );
	}
}
