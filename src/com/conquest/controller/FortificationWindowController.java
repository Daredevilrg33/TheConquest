package com.conquest.controller;

import java.util.ArrayList;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.window.FortificationWindow;

// TODO: Auto-generated Javadoc
/**
 * The Class FortificationWindowController.
 * 
 * @author Tushar
 */
public class FortificationWindowController {

	/** The reinforcement window. */
	private FortificationWindow fortificationWindow;

	/** The source counter. */
	private int sourceCounter = 0;

	/** The destination counter. */
	private int destinationCounter = -1;

	/** The players. */
	private PlayerModel player;

	/** The destination country models. */
	ArrayList<String> destinationCountryModels = new ArrayList<>();

	/**
	 * Instantiates a new fortification window controller.
	 *
	 * @param player              the player
	 * @param fortificationWindow the fortification window
	 */
	public FortificationWindowController(PlayerModel player, FortificationWindow fortificationWindow) {
		this.fortificationWindow = fortificationWindow;
		this.player = player;
		if (fortificationWindow != null) {
			updateSourceUI();
			this.fortificationWindow.updatePlayerLabel(player.getPlayerName());
		}

	}

	/**
	 * Gets the current player.
	 *
	 * @return the current player
	 */
	public PlayerModel getCurrentPlayer() {
		return player;
	}

	/**
	 * Sending Countries.
	 *
	 * @param armies the armies
	 */
	public void sending(int armies) {
		for (int s = 0; s < armies; s++) {
			getCurrentPlayer().getPlayerCountryList().get(destinationCounter).addNoOfArmiesCountry();
			getCurrentPlayer().getPlayerCountryList().get(sourceCounter).removeNoOfArmiesCountry();
		}
		System.out.println("Newnumber of armies in source: "
				+ getCurrentPlayer().getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
		System.out.println("Newnumber of armies in destination: "
				+ getCurrentPlayer().getPlayerCountryList().get(destinationCounter).getNoOfArmiesCountry());
		if (fortificationWindow != null)
			updateUIInfo();
	}

	/**
	 * After fortification.
	 */
	public void afterFortification() {
		fortificationWindow.dispose();
	}

	/**
	 * Update Backend.
	 */
	public void updateBackend() {

		sourceCounter = 0;
		destinationCounter = -1;
		fortificationWindow.updateComboBoxSourceCountries(getCurrentPlayer().getPlayerCountryList());
		fortificationWindow.updateComboBoxDestinationCountries(destinationCountryModels);
		fortificationWindow.updatePlayerLabel(getCurrentPlayer().getPlayerName());
		updateSourceUI();
		fortificationWindow.newArmyLabel();

	}

	/**
	 * Finding Countries in Fortification.
	 *
	 * @param sourceCountry the source country
	 */
	public void finding(String sourceCountry) {

		ArrayList<String> tempDestList = new ArrayList<>();
		for (CountryModel countryModel : getCurrentPlayer().getPlayerCountryList()) {
			if (countryModel.getCountryName().trim().equalsIgnoreCase(sourceCountry)) {
				sourceCounter = getCurrentPlayer().getPlayerCountryList().indexOf(countryModel);
				tempDestList.addAll(countryModel.getListOfNeighbours());
				break;
			}
		}

		for (String destinationCountryName : tempDestList) {
			CountryModel countryModel = getCurrentPlayer().searchCountry(destinationCountryName.trim());
			if (countryModel != null) {
				destinationCountryModels.add(destinationCountryName.trim());
			}
		}

	}

	/**
	 * Checking.
	 *
	 * @return true, if successful
	 */
	public boolean checking() {
		if (getCurrentPlayer().getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry() == 1)
			return true;
		else
			return false;
	}

	/**
	 * Update destination UI.
	 *
	 * @param selectedDestination the selected destination
	 */
	public void updateDestinationUI(String selectedDestination) {
		for (int i = 0; i < getCurrentPlayer().getPlayerCountryList().size(); i++) {
			if (getCurrentPlayer().getPlayerCountryList().get(i).getCountryName()
					.equalsIgnoreCase(selectedDestination)) {
				destinationCounter = i;
				break;
			}
		}
		if (fortificationWindow != null) {
			if (destinationCounter >= 0)
				fortificationWindow.updateDestinationArmyLabel(
						getCurrentPlayer().getPlayerCountryList().get(destinationCounter).getNoOfArmiesCountry());
			else
				fortificationWindow.updateDestinationArmyLabel(0);

		}
	}

	/**
	 * Update source UI.
	 */
	public void updateSourceUI() {
		fortificationWindow.updateComboBoxSourceCountries(getCurrentPlayer().getPlayerCountryList());
	}

	/**
	 * Update UI info.
	 */
	public void updateUIInfo() {

		fortificationWindow
				.updateArmy(getCurrentPlayer().getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
		fortificationWindow.updateSourceArmyLabel(
				getCurrentPlayer().getPlayerCountryList().get(sourceCounter).getNoOfArmiesCountry());
		fortificationWindow.updateComboBoxDestinationCountries(destinationCountryModels);
		destinationCountryModels.clear();

	}

}
