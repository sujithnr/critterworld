package simulationTests;

import org.junit.Before;
import org.junit.Test;

import console.Console;

public class AttackTests {

	Console console1 = null;
	
	@Before
	 public void setup() {
		 console1 = new Console();
		 console1.loadWorld("AttackWorld.txt");
	 }
	
	/**
	 * testBasicAttack tests attack by having a massive critter attack a tiny critter. 
	 */
	@Test
	public void testBasicAttack() {
		console1.worldInfo();
		console1.advanceTime(1);
		console1.worldInfo();
	}
}
