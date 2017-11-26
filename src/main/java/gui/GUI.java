package gui;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setMinHeight(800);
		stage.setMinWidth(1000);

		try {
			URL r = getClass().getResource("gui.fxml");
			if (r == null)
				throw new Exception("No FXML resource found.");
			Scene scene = new Scene(FXMLLoader.load(r));
			stage.setTitle("SUJITHWORLD!");
			stage.setScene(scene);
			stage.sizeToScene();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// Sujith:
// TODO make it so if args != 0 or 4, say bad input
// TODO work on server stuff

// Rishi:
// TODO figure out exactly how hexSelectionMood works and if it's really doing
// what it's supposing to be doing
// TODO make critters solid instead of outlines? try solid out and see how it
// looks.
// TODO implement smell
// TODO do written problems
// TODO create some smarter critter programs for better testing (and as prep for
// critter tournament :))
// TODO make ctrl + arrow keys zooming if wants and has time
// TODO why doesn't critter info box passively update when GUI is in run mode
// instead of step by step mode?
// TODO why doesn't critter info box clear when critter leaves the selected hex?

// Andy:
// TODO merge run and pause buttons?
// TODO fix loading critters so that it won't allow loading in more critters
// than there are available hexes
// TODO why is first new world created not centered?
// TODO fix a5 and a4 grader bugs
// TODO start worldmodel updates and stuff

// Bug: new world,
// reset,
// loadworld,
// spiralcritterworld
// loaded 20 noenergybudcritters
// ConcurrencyModificationException: probably because things inside the map are
// being modified??
// iterating through list of critters and objects
// critter species names?
// do we just remove all the non-server stuff

// TODO critter info box should reset upon world reset