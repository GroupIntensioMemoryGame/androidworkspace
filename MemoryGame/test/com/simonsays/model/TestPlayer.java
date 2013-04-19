package com.simonsays.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlayer
{
	@Test
	public void test()
	{
		// Test Constructor
		Player pl = new Player("Steve");
		assertTrue(pl != null);
		
		// Test Getter
		assertEquals(pl.getUserName(), "Steve");
	}
}
