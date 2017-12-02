package distributed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.Gson;

import ast.Program;
import ast.Rule;
import gui.WorldModel;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;
import java.util.Set;

import simulation.FileParser;
import simulation.Hex;
import simulation.SimpleCritter;
import simulation.WorldObject;

/** A class to handle client requests to the server. */
public class ClientRequestHandler {
	/** Stores the most recently retrieved version of the world. */
	private int mostRecentVersion;
	private String url;

	public ClientRequestHandler(String url) {
		this.url = url;
	}

	/**
	 * 
	 * @param sessionId
	 * @return
	 */
	public boolean createNewWorld(int sessionId) {
		Gson gson = new Gson();
		String description = "name Arrakis\r\nsize 50 68\r\n";
		Hex[][] grid = new Hex[50][68];
		boolean[][] isNotValidSpace = new boolean[50][68];

		// randomly fills about 1/40 of the hexes in the world with rocks
		int c = (int) (Math.random() * 50);
		int r = (int) (Math.random() * 68);
		int n = 0;
		while (n < 2150 / 40) {
			c = (int) (Math.random() * 50);
			r = (int) (Math.random() * 68);
			if (isValidHex(c, r, 50, 68) && isNotValidSpace[c][r] == false) {
				isNotValidSpace[c][r] = true;
				description += "rock " + c + " " + r + "\r\n";
				n++;
			}
		}
		LoadWorldInfoJSON loadWorldInfo = new LoadWorldInfoJSON(description);
		URL url = null;
		try {
			url = new URL(this.url + "/world?session_id=" + sessionId);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // send a POST message
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			PrintWriter w = new PrintWriter(connection.getOutputStream());
			w.println(gson.toJson(loadWorldInfo, LoadWorldInfoJSON.class));
			w.flush();
			if (connection.getResponseCode() == 401) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login Error");
				alert.setHeaderText("Access Denied");
				alert.setContentText("The user cannot create a new world because the user is not an admin.");
				return false;
			}
			BufferedReader r1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			System.out.println(r1.readLine());
		} catch (MalformedURLException e) {
			System.out.println("The URL entered was not correct.");
		} catch (IOException e) {
			System.out.println("Could not connect to the server");
		}
		return true;

	}

	/**
	 * 
	 * @param worldfile
	 * @param sessionId
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public boolean loadWorld(File worldfile, int sessionId) throws IllegalArgumentException, IOException {
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new FileReader(worldfile));
		String description = "";
		String currentLine = br.readLine();
		while (currentLine != null) {
			if (currentLine.indexOf("critter") == -1) {
				description += currentLine;
				description += "\r\n";
				currentLine = br.readLine();
			} else {
				currentLine = br.readLine();
			}
		}
		LoadWorldInfoJSON loadWorldInfo = new LoadWorldInfoJSON(description);
		URL url = null;
		try {
			url = new URL(this.url + "/world?session_id=" + sessionId);
			// url = new URL("http://localhost:" + 8080 + "/world?session_id=" + sessionId);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // send a POST message
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			PrintWriter w = new PrintWriter(connection.getOutputStream());
			w.println(gson.toJson(loadWorldInfo, LoadWorldInfoJSON.class));
			w.flush();
			if (connection.getResponseCode() == 401) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login Error");
				alert.setHeaderText("Access Denied");
				alert.setContentText("The user cannot create a new world because the user is not an admin.");
				return false;
			}
			BufferedReader r1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			if (!r1.readLine().equals("Ok")) {
				return false;
			}
		} catch (MalformedURLException e) {
			System.out.println("The URL entered was not correct.");
		} catch (IOException e) {
			System.out.println("Could not connect to the server");
		} finally {
			br.close();
		}
		return true;

	}

	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Returns the number of columns in the world.
	 * 
	 * @return The number of columns, or -1 if the user does not have permission
	 */
	public int getColumns(int sessionId) {
		Gson gson = new Gson();
		URL url = null;
		int returnValue = 0;
		try {
			url = new URL(this.url + "/world?session_id=" + sessionId + "&update_since=" + this.mostRecentVersion);
			System.out.println(url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			if (connection.getResponseCode() == 401) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login Error");
				alert.setHeaderText("Access Denied");
				alert.setContentText("User is not an admin so a New World cannot be created.");
				return -1;
			}
			BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			JSONWorldObject worldState =  gson.fromJson(r, JSONWorldObject.class);
			returnValue = worldState.getColumn();
		} catch (MalformedURLException e) {
			System.out.println("The URL entered was not correct.");
		} catch (IOException e) {
			System.out.println("Could not connect to the server");
		}
		return returnValue;
	}

	public int getRows(int sessionId) {
		Gson gson = new Gson();
		URL url = null;
		int returnValue = 0;
		try {
			url = new URL(this.url + "/world?session_id=" + sessionId + "&update_since=" + this.mostRecentVersion);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			if (connection.getResponseCode() == 401) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login Error");
				alert.setHeaderText("Access Denied");
				alert.setContentText("User is not an admin so a New World cannot be created.");
				return -1;
			}
			BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			JSONWorldObject worldState =  gson.fromJson(r, JSONWorldObject.class);
			returnValue = worldState.getRow();
		} catch (MalformedURLException e) {
			System.out.println("The URL entered was not correct.");
		} catch (IOException e) {
			System.out.println("Could not connect to the server");
		}
		return returnValue;
	}

	public void advanceTime() {
		// TODO Auto-generated method stub

	}

	public void loadRandomCritters(File f, int n, int sessionId) throws FileNotFoundException {
		Gson gson = new Gson();
		BufferedReader reader = new BufferedReader(new FileReader(f));
		SimpleCritter critter = FileParser.parseCritter(reader, 8, 0);
		Program program = critter.getProgram();
		LinkedList<Rule> programList = program.getRulesList();
		String programDescription = "";
		for (Rule rule : programList) {
			programDescription += (rule.toString() + "\r\n");
		}
		int[] mem = critter.getMemoryCopy();
		Integer num = n;
		CritterJSON critterJSON = new CritterJSON(critter.getName(), programDescription, mem, num);
		URL url;
		try {
			url = new URL("http://hexworld.herokuapp.com:80/hexworld/critters?session_id=1914893925");
			// url = new URL("http://localhost:" + 8080 + "/critters?session_id=" +
			// sessionId);
			System.out.println(url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // send a POST message
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			PrintWriter w = new PrintWriter(connection.getOutputStream());
			w.println(gson.toJson(critterJSON, CritterJSON.class));
			w.flush();
			if (connection.getResponseCode() == 401) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login Error");
				alert.setHeaderText("Access Denied");
				alert.setContentText("The user cannot create a new world because the user is not an admin.");
			}
			System.out.println(gson.toJson(critterJSON, CritterJSON.class));
			BufferedReader r1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = r1.readLine();
			while (line != null) {
				System.out.println(line);
				line = r1.readLine();
			}
		} catch (MalformedURLException e) {
			System.out.println("The URL entered was not correct.");
		} catch (IOException e) {
			System.out.println("Could not connect to the server");
		}
	}

	public void loadCritterAtLocation(File f, int c, int r, int sessionId) throws FileNotFoundException {
		Gson gson = new Gson();
		BufferedReader reader = new BufferedReader(new FileReader(f));
		SimpleCritter critter = FileParser.parseCritter(reader, 8, 0);
		Program program = critter.getProgram();
		LinkedList<Rule> programList = program.getRulesList();
		String programDescription = "";
		for (Rule rule : programList) {
			programDescription += (rule.toString() + "\r\n");
		}
		int[] mem = critter.getMemoryCopy();
		PositionJSON[] positions = new PositionJSON[] { new PositionJSON(c, r) };
		CritterJSON critterJSON = new CritterJSON(critter.getName(), programDescription, mem, positions);
		URL url;
		try {
			url = new URL("http://hexworld.herokuapp.com:80/hexworld/critters?session_id=1914893925");
			// url = new URL("http://localhost:" + 8080 + "/critters?session_id=" +
			// sessionId);
			System.out.println(url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true); // send a POST message
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			PrintWriter w = new PrintWriter(connection.getOutputStream());
			w.println(gson.toJson(critterJSON, CritterJSON.class));
			w.flush();
			if (connection.getResponseCode() == 401) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login Error");
				alert.setHeaderText("Access Denied");
				alert.setContentText("The user cannot create a new world because the user is not an admin.");
			}
			System.out.println(gson.toJson(critterJSON, CritterJSON.class));
			BufferedReader r1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = r1.readLine();
			while (line != null) {
				System.out.println(line);
				line = r1.readLine();
			}
		} catch (MalformedURLException e) {
			System.out.println("The URL entered was not correct.");
		} catch (IOException e) {
			System.out.println("Could not connect to the server");
		}

	}

	private boolean isValidHex(int c, int r, int columns, int rows) {
		if (c < 0 || r < 0)
			return false;
		else if (c >= columns || r >= rows)
			return false;
		else if ((2 * r - c) < 0 || (2 * r - c) >= (2 * rows - columns))
			return false;
		return true;
	}
}