package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestScore
{
	@Test
	public void test()
	{		
		// Test Constructor
		Score scr = new Score("Steve", 10, 5, 10512);
		assertTrue(scr != null);
		
		// Test Getters
		assertEquals(scr.getUserName(), "Steve");
		assertEquals(scr.getNumObjects(), 10);
		assertEquals(scr.getSequenceLength(), 5);
		assertEquals(scr.getTime(), 10512);
		
		// Test CompareTo
		fail("Not yet implemented");
	}
}