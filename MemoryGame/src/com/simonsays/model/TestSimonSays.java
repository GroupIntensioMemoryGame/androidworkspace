package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestSimonSays
{
	@Test
	public void test()
	{
		// Test the constructor
		SimonSays simon = new SimonSays(70);
		assertTrue(simon != null);
		
		// Test getters
		assertTrue(simon.getComputerSequence() != null);
		
		assertEquals(simon.getNumGameObjects(), 70);
		
		assertEquals(simon.getRoundNumber(), 0);
		
		// Test increase sequence
		simon.increaseSequence();
		assertEquals(simon.getRoundNumber(), 1);
		assertTrue(simon.getComputerSequence().get(0) <= 69 && simon.getComputerSequence().get(0) >= 0);
		System.out.println(simon.getComputerSequence().get(0));
	}

}
