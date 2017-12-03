package gui;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import simulation.SimpleCritter;
import simulation.SimpleWorld;
import simulation.World;
import simulation.WorldObject;
import simulation.Hex;

/** 
 * The model that stores world and critter states. It also serves as an abstraction barrier between the world
 * and the modules that interact with the world.
 */
public class WorldModel {
	
	/** An instance of the world. */
	private SimpleWorld world;
	/** The number of critters. */
	private int numCritters;
	/** The number of time steps taken. */
	private int time;
	/** The current world version number. */
	private int versionNumber;
	/** Assigned as a critter ID. Is modified every time a critter is added to ensure uniqueness of IDs. */
	//private int IDAssignment;
	/** Maps unique critter IDs to critters. */
	//private HashMap<Integer, SimpleCritter> critterIDMap;
	/** A running list of all critters that have died across all worlds simulated in this session. */
	private LinkedList<SimpleCritter> cumulativeDeadCritters;
	/** Supplies the locks for the models. */
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	/** A log of all the changes that have occurred to the world since version 0 (which is a blank world). */
	private ArrayList<ArrayList<Hex>> diffLog;

	/** Creates a new blank world model. */
	public WorldModel() {
		rwl.writeLock().lock();
		try {
			numCritters = 0;
			time = 0;
			versionNumber = 0;
			//IDAssignment = 0;
			diffLog = new ArrayList<ArrayList<Hex>>();
			//critterIDMap = new HashMap<Integer, SimpleCritter>();
		} finally {
			rwl.writeLock().unlock();
		}
	}

	/**
	 * Creates a new random world.
	 * @throws UnsupportedOperationException
	 *             if the constants.txt file could not be read
	 */
	public void createNewWorld() throws UnsupportedOperationException {
		rwl.writeLock().lock();
		try {
			// if a world already exists, adds all its dead critters to the cumulative dead critter list
			if (world != null)
				cumulativeDeadCritters.addAll(world.collectCritterCorpses());
			world = new World();
			//System.out.println(world.getAndResetUpdatedHexes());
			//diffLog.add(world.getAndResetUpdatedHexes());
			time = 0;
			versionNumber++;
			numCritters = world.numRemainingCritters();
		} finally {
			rwl.writeLock().unlock();
		}
	}

	/**
	 * Loads in a world based on a description.
	 * 
	 * @param desc
	 * @throws IllegalArgumentException
	 *             if the description is invalid
	 * @throws UnsupportedOperationException
	 *             if the constants.txt file could not be read
	 */
	public void loadWorld(String desc) throws IllegalArgumentException, UnsupportedOperationException {
		rwl.writeLock().lock();
		try {
			// if a world already exists, adds all its dead critters to the cumulative dead critter list
			if (world != null)
				cumulativeDeadCritters.addAll(world.collectCritterCorpses());
			world = new World(desc);
			//System.out.println(world.getAndResetUpdatedHexes());
			//diffLog.add(world.getAndResetUpdatedHexes());
			time = 0;
			versionNumber++;
			numCritters = world.numRemainingCritters();
		} finally {
			rwl.writeLock().unlock();
		}
	}
	
	/**
	 * Loads in a world file.
	 * 
	 * @param worldfile
	 * @throws FileNotFoundException
	 *             if the file could not be found or is somehow invalid
	 * @throws UnsupportedOperationException
	 *             if the constants.txt file could not be read
	 */
	public void loadWorld(File worldfile) throws FileNotFoundException, UnsupportedOperationException {
		rwl.writeLock().lock();
		try {
			// if a world already exists, adds all its dead critters to the cumulative dead critter list
			if (world != null)
				cumulativeDeadCritters.addAll(world.collectCritterCorpses());
			world = new World(worldfile);
			//System.out.println(world.getAndResetUpdatedHexes());
			//diffLog.add(world.getAndResetUpdatedHexes());
			time = 0;
			versionNumber++;
			numCritters = world.numRemainingCritters();
		} finally {
			rwl.writeLock().unlock();
		}
	}

	public boolean isReady() {
		return world != null;
	}

	/** Returns the number of columns in the world. */
	public int getColumns() {
		rwl.readLock().lock();
		try {
			return world.getColumns();
		} finally {
			rwl.readLock().unlock();
		}
	}

	/** Returns the number of rows in the world. */
	public int getRows() {
		rwl.readLock().lock();
		try {
			return world.getRows();
		} finally {
			rwl.readLock().unlock();
		}
	}

	/** Returns the number of living critters in the world. */
	public int getNumCritters()
	{
		return numCritters;
	}
	
	/** Returns the current time step of the world. */
	public int getCurrentTimeStep()
	{
		return time;
	}
	
	/** Returns the current version number. */
	public int getCurrentVersionNumber()
	{
		return versionNumber;
	}
	
	/** Retrieves the running list of dead critters. */
	public SimpleCritter[] getCumulativeDeadCritters()
	{
		SimpleCritter[] result = new SimpleCritter[cumulativeDeadCritters.size()];
		return cumulativeDeadCritters.toArray(result);
	}
	
	public ArrayList<Hex> getUpdatedHexes()
	{
		return world.getAndResetUpdatedHexes();
	}
	
	/**
	 * Returns a number giving information about a hex.
	 * @param c
	 * @param r
	 * @return
	 */
	public int hexInfo(int c, int r) {
		rwl.readLock().lock();
		try {
			return world.analyzeHex(c, r);
		} finally {
			rwl.readLock().unlock();
		}
	}

	/**
	 * 
	 * @param c
	 * @param r
	 * @return
	 */
	public SimpleCritter getCritter(int c, int r) {
		rwl.readLock().lock();
		try {
			return world.analyzeCritter(c, r);
		} finally {
			rwl.readLock().unlock();
		}
	}

	public Set<Map.Entry<SimpleCritter, Hex>> getCritterMap() {
		rwl.readLock().lock();
		try {
			return world.getCritterMap();
		} finally {
			rwl.readLock().unlock();
		}
	}

	public Set<Map.Entry<WorldObject, Hex>> getObjectMap() {
		rwl.readLock().lock();
		try {
			return world.getObjectMap();
		} finally {
			rwl.readLock().unlock();
		}
	}

	/** Advances one time step. */
	public void advanceTime() {
		try {
			rwl.writeLock().lock();
			world.advanceOneTimeStep();
			time++;
			versionNumber++;
			//System.out.println(world.getAndResetUpdatedHexes());
			//diffLog.add(world.getAndResetUpdatedHexes());
			rwl.writeLock().unlock();
			
			rwl.readLock().lock();
			numCritters = world.numRemainingCritters();
		} finally {
			rwl.readLock().unlock();
		}
	}
	
	/**
	 * Provides a map of everything that has changed in the world since the initial version.
	 * @param initialVersionNumber
	 * @return a HashMap mapping changed hexes to the objects at those hexes.
	 */
	public HashMap<Hex, WorldObject> updateSince(int initialVersionNumber)
	{
		//TODO implement locks
		HashMap<Hex, WorldObject> result = new HashMap<Hex, WorldObject>();
		if(initialVersionNumber < 0 || initialVersionNumber >= diffLog.size())
			return null;
		for (int i = initialVersionNumber; i < diffLog.size(); i++)
		{
			for (Hex h : diffLog.get(i))
			{
				int c = h.getColumnIndex();
				int r = h.getRowIndex();
				result.put(h, world.getHexContent(c, r));
			}
		}
		return result;
	}
	
	/**
	 * Loads critters at random locations.
	 * @param f the file specifying the critter to load
	 * @param n the number of critters to load
	 */
	public void loadRandomCritters(File f, int n) {
		try {
			rwl.writeLock().lock();
			world.loadCritters(f, n, -1);
			versionNumber++;
			rwl.writeLock().unlock();
			rwl.readLock().lock();
			numCritters = world.numRemainingCritters();
		} finally {
			rwl.readLock().unlock();
		}
	}

	public void loadCritterAtLocation(File f, int c, int r) {
		try {
			rwl.writeLock().lock();
			world.loadCritterAtLocation(f, c, r);
			versionNumber++;
			rwl.writeLock().unlock();
			rwl.readLock().lock();
			numCritters = world.numRemainingCritters();
		} finally {
			rwl.readLock().unlock();
		}
	}
}