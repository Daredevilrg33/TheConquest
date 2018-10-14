/**
 * 
 */
package com.conquest.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.window.GameWindow;

/**
 * @author Rohit Gupta
 *
 */
public class GameWindowController  {

	private GameWindow gameWindow;
	private int noOfPlayers = 0;
	private MapHierarchyModel mapModel;
	private int counter = 0;
	private PlayerModel[] players;

	public GameWindowController(GameWindow gameWindow, int noOfPlayers, MapHierarchyModel mapModel) {
		this.gameWindow = gameWindow;
		this.noOfPlayers = noOfPlayers;
		this.mapModel = mapModel;
		initializingPlayerModels(this.noOfPlayers, this.mapModel);
	}

	private void initializingPlayerModels(int noOfPlayers, MapHierarchyModel mapModel) {

		players = new PlayerModel[noOfPlayers];
		int pickedNumber = 0;
		Random rand = new Random();

		for (int j = 0; j < noOfPlayers; j++) {
		
			int value = j+1;
			players[j] = new PlayerModel("Player" + String.valueOf(value));
		}
		

		/*
		 * randomly placing army of each player in different country by round robin
		 */

		while (!(mapModel.getCountryList().isEmpty())) {
			for (int count1 = 0; count1 < noOfPlayers; count1++) {
				if (!(mapModel.getCountryList().isEmpty())) {
					pickedNumber = rand.nextInt(mapModel.getCountryList().size());
					CountryModel countryModelTest = mapModel.getCountryList().get(pickedNumber);
					if (countryModelTest != null)
						players[count1].AddCountry(countryModelTest);
					System.out.println(mapModel.getCountryList().get(pickedNumber));
					mapModel.getCountryList().remove(pickedNumber);
					// h++;
					// System.out.println("size "+mapModel.getCountryModels().size());
					// System.out.println("Picked Number "+pickedNumber);
					// System.out.println(h);

				}
			}
		}

//		player[index].getPlayerCountryList().get(index);

		/*
		 * countries can be accessed by
		 */

		updateUIInfo();
	}

	public void updateUIInfo() {
		 
		gameWindow.updatePlayerLabel(players[counter].getPlayerName());
		gameWindow.updateComboBoxCountries(players[counter].getPlayerCountryList());
		gameWindow.invalidate();
		gameWindow.revalidate();
		counter++;	
		if(counter == noOfPlayers)
			counter=0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	

}
