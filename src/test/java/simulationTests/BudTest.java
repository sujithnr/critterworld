package simulationTests;

import org.junit.Before;
import org.junit.Test;

import console.Console;

public class BudTest {
	
	@Test
	public void testOne() {
		Console console = new Console();
		console.loadWorld("SpiralCritterWorld.txt");
		console.worldInfo();
		//console.loadCritters("example-critter2.txt", 1);
		
		for(int i = 0; i < 20; i++)
		{
			console.advanceTime(1);
			console.worldInfo();
		}
		console.worldInfo();
	}
}
