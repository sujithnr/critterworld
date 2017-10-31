package simulationTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import console.Console;

public class MovingTest
{
	Console console1 = null;
	Console console2 = null;
	Console console3 = null;
	@Before
	public void setUp()
	{
		console1 = new Console();
		console1.loadWorld("MovingWorld.txt");
		console2 = new Console();
		console2.loadWorld("MovingWorldFileRock.txt");
		console3 = new Console();
		console3.loadWorld("MovingWorldThree.txt");
	}
	/**
	 * testNormalMove tests to see if moving forward normally works.
	 */
	@Test
	public void testNormalMove()
	{
		System.out.println("testNormalMove");
		console1.worldInfo();
		console1.advanceTime(1);
		console1.worldInfo();
	}
	
	/**
	 * testMoveWithRock tests to see that critter won't move when there is a rock in front of it.
	 */
	@Test
	public void testMoveWithRock() {
		System.out.println("testMoveWithRock");
		console2.worldInfo();
		console2.advanceTime(1);
		console2.worldInfo();
	}
	
	/**
	 * testMovingWithNoEnergy tests to see that a critter without enough energy to move dies.
	 */
	@Test
	public void testMovingWithNoEnergy() {
		System.out.println("testMovingWithNoEnergy");
		console3.worldInfo();
		console3.advanceTime(1);
		console3.worldInfo();
	}

}
