package simulation;

import ast.Program;
import simulation.Critter.Direction;

public interface SimpleCritter extends WorldObject
{
	/** Returns the ruleset of this critter. */
	public Program getProgram();
	
	public int size();
	
	public int getMemLength();
	
	/** Returns the orientation of this critter. */
	public int getOrientation();
	
	/** 
	 * Returns the value of {@code memory[index]}.
	 * @param index
	 * @return the value of this critter's memory at the specified index, or {@code Integer.MIN_VALUE} if the index lies out-of-bounds
	 */
	public int readMemory(int index);
	
	/**
	 * Sets the memory at index to val. Does nothing if {@code index} is an out-of-bounds or unassignable index
	 * or if {@code val} is not within the restrictions of that array index.
	 * @param val
	 * @param index
	 * @return Whether or not the memory array was actually altered
	 */
	public boolean setMemory(int val, int index);
	
	public String getName();
	
	public String getLastAction();
	
	public void setLastAction(String s);
	
	/** Increments the pass number of this critter (memory index 5) by one, if it is less than 999. */
	public void incrementPass();
	
	/**
	 * Turns this critter in the direction specified.
	 * @param counterclockwise
	 */
	public void turn(boolean clockwise);
	
	public int[] changeInPosition(boolean forward, int dir);
	
	public int getEnergy();
	
	public void updateEnergy(int amount, int maxEnergyPerSize);
	
	public boolean wantsToMate();
	
	public void toggleMatingPheromones(boolean b);
	
	public int complexity(int ruleCost, int abilityCost);
	
	@Override
	
	public int getAppearance();
}