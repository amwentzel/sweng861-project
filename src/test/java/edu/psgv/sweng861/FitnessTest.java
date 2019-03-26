package edu.psgv.sweng861;

import org.junit.Before;
import org.junit.Test;

public class FitnessTest {

	public Fitness fitness;
	
	@Before
	public void setUp() {
		fitness = new Fitness();
	}
	
	@Test
	public void testMongoSetup() {
		fitness.mongoSetup();
	}
	
}
