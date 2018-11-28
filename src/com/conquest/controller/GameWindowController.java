package com.conquest.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.model.GameModel;
import com.conquest.model.PlayerModel;
import com.conquest.window.GameWindow;
import com.conquest.window.MainMenuScreen;
import com.conquest.window.AttackPhaseWindow;
import com.conquest.window.FortificationWindow;

/**
 * Game Window Controller Class This class initializes Number of players, Player
 * model, Game window object and Map HeirarchyModel.
 *
 * @author Rohit Gupta
 * @version 1.0.0
 */
public class GameWindowController {

	/** The game window. */
	private GameWindow gameWindow;

	/** The counter. */
	private int counter = 0;

	/** The previous counter. */
	private int prevCounter = 0;

	/** The game model. */
	private GameModel gameModel;

	private static final Logger log = Logger.getLogger(GameWindowController.class);

	/**
	 * Game Window Controller Constructor Constructor created to assign value of
	 * objects.
	 *
	 * @param gameWindow  Object of class GameWindow {@link GameWindow}
	 * @param noOfPlayers Number of players in game Range from (3-5)
	 * @param mapModel    Object of class MapHierarchyModel
	 *                    {@link MapHierarchyModel}
	 */
	public GameWindowController(GameWindow gameWindow, GameModel gameModel) {
		this.gameWindow = gameWindow;
		this.gameModel = gameModel;

	}

	/**
	 * Checks if is control value to be added.
	 *
	 * @param mapHierarchyModel the map hierarchy model
	 * @param playerModels      the player models
	 */
	public void isControlValueTobeAdded(MapHierarchyModel mapHierarchyModel, PlayerModel[] playerModels) {
		int z = 0;
		for (int i = 0; i < playerModels.length; i++) {
			PlayerModel playerModel = playerModels[i];
			for (ContinentModel continentModel : mapHierarchyModel.getContinentsList()) {
				z = 0;
				for (CountryModel countryModel : continentModel.getCountriesList()) {
					CountryModel value = playerModel.searchCountry(countryModel.getCountryName());
					if (value == null) {
						break;
					} else {
						z++;
						if (z == continentModel.getCountriesList().size()) {
							playerModel.addControlValueToNoOfArmy(continentModel.getControlValue());
							z = 0;
						}
					}
				}
			}
		}
	}

	/**
	 * Checking method To check the number of armies.
	 *
	 * @param selectedCountryName type String {@link String}
	 * @param currentPlayer       the current player
	 */
	public void placingInitialArmies(String selectedCountryName, PlayerModel currentPlayer) {
		gameModel.setGameStatus("Placing Initial Armies");
		log.info("Placing Initial Armies");
		if (currentPlayer.getnoOfArmyInPlayer() > 0) {
			currentPlayer.searchCountry(selectedCountryName).addNoOfArmiesCountry();
			currentPlayer.reduceArmyInPlayer();
		}

	}

	/**
	 * Checking method To check the number of armies.
	 *
	 * @param selectedCountryName type String {@link String}
	 * @param currentPlayer       the current player
	 */
	public void placeReinforcedArmy(String selectedCountryName, GameModel gameModel) {
		gameModel.setGameStatus("Placing Reinforced Armies");
		PlayerModel currentPlayer = gameModel.getCurrPlayer();
		log.info("Placing Reinforced Armie");
		if (currentPlayer.getnoOfArmyInPlayer() > 0) {
			currentPlayer.searchCountry(selectedCountryName).addNoOfArmiesCountry();
			currentPlayer.reduceArmyInPlayer();
		}
		if (currentPlayer.getnoOfArmyInPlayer() == 0) {
//			Check this code
			String message = gameModel.attackPhase();
			if (message.trim().equalsIgnoreCase("success")) {
				AttackPhaseWindow attackPhaseWindow = new AttackPhaseWindow(gameModel);
				attackPhaseWindow.setVisible(true);
			}
		}

	}

	/**
	 * Method to save current game to disk
	 */
	public void saveGame() {
		ObjectOutputStream output = null;
		try {
			String fileName = null;
			if (gameModel.getMapHierarchyModel() != null)
				fileName = gameModel.getMapHierarchyModel().getConquestMapName() + "-" + gameModel.getGameStatus() + "-"
						+ new Date().getTime() + ".bin";
			else
				fileName = gameModel.getGameStatus() + new Date().getTime() + ".bin";
			output = new ObjectOutputStream(new FileOutputStream("./save/" + fileName));
			output.writeObject(gameModel);
			JOptionPane.showMessageDialog(null, "Game has been saved to file <" + fileName + ">. successfully ");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
