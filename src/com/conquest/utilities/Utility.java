package com.conquest.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.model.PlayerType;

/**
 * The Class Utility.
 *
 * @author Rohit Gupta
 */
public class Utility {

	/**
	 * pickFile Method This method will provide the functionality to pick a map
	 * file.
	 * 
	 * @return : This will return the path of the map.
	 */
	public static String pickFile() {
		String filePath = "";
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Select Map");
		jfc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", "map");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			filePath = jfc.getSelectedFile().getPath();
			System.out.println("File Path: " + filePath);
			return filePath;
		} else
			return "";
	}

	/**
	 * parseAndValidateMap Method Parses the and validate map.
	 * 
	 * @param filePath the file path
	 * @return the map hierarchy model
	 */
	public MapHierarchyModel parseAndValidateMap(String filePath) {
		MapHierarchyModel mapHierarchyModel = new MapHierarchyModel();
		ArrayList<ContinentModel> continentModels = new ArrayList<>();
		ArrayList<CountryModel> countryModels = new ArrayList<>();
		boolean isContinent = false;
		boolean isCountry = false;
		String[] neighbourCountries = null;
		String targetCountry = null;

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				if (currentLine.isEmpty())
					continue;
				if (currentLine.equalsIgnoreCase("[Continents]")) {
					isContinent = true;
					isCountry = false;
					continue;
				}
				if (currentLine.equalsIgnoreCase("[Territories]")) {
					isCountry = true;
					isContinent = false;
					if (!isContinent && continentModels.size() == 0) {
						String valErrorMessage = "Map is invalid as there are no continents defined";
						mapHierarchyModel.setValErrorFlag(true);
						mapHierarchyModel.setErrorMsg(valErrorMessage);
						break;
					}

					continue;
				}
				if (isContinent) {
					if (currentLine.indexOf('=') > 0) {
						String[] continentValues = currentLine.split("=");
						if (continentValues.length > 0) {
							ContinentModel continentModel = new ContinentModel(continentValues[0]);
							if (continentValues.length > 1)
								continentModel.setControlValue(Integer.valueOf(continentValues[1]));
							continentModels.add(continentModel);
						}
					} else {
						String valErrorMessage = "Map is invalid as there are no territories tag defined";
						mapHierarchyModel.setValErrorFlag(true);
						mapHierarchyModel.setErrorMsg(valErrorMessage);
						break;
					}
					continue;
				}

				if (isCountry) {
					String[] countryValues = currentLine.split(",");
					if (countryValues.length < 5) {
						targetCountry = countryValues[0];
					}
					CountryModel countryModel = new CountryModel();
					for (int i = 0; i < countryValues.length; i++) {
						if (i == 0) {
							countryModel.setCountryName(countryValues[i]);
						} else if (i == 1 || i == 2) {
							// do nothing as we have skipped the coordinates
						} else if (i == 3) {
							ContinentModel continentModel = new ContinentModel(countryValues[i]);
							countryModel.setBelongsTo(continentModel);
						} else {

							countryModel.addNeighbour(countryValues[i]);
						}
					}
					countryModels.add(countryModel);
					if (countryValues.length > 0) {
						for (ContinentModel continentModelValue : continentModels) {
							if (continentModelValue.getContinentName().trim()
									.equalsIgnoreCase(countryModel.getBelongsTo().getContinentName().trim())) {
								continentModelValue.addCountry(countryModel);
							}
						}

					}
				}
				System.out.println(currentLine);
			}

			mapHierarchyModel.setContinentsList(continentModels);
			if (countryModels.size() == 0 && !mapHierarchyModel.isValErrorFlag()) {
				String valErrorMessage = "Map is invalid as there are no countries defined.";
				mapHierarchyModel.setValErrorFlag(true);
				mapHierarchyModel.setErrorMsg(valErrorMessage);
				return mapHierarchyModel;
			} else if (countryModels.size() < 3 && !mapHierarchyModel.isValErrorFlag()) {
				String valErrorMessage = "Map is invalid as there should be minimum three countries defined in the map as there are minimum three players which can play.";
				mapHierarchyModel.setValErrorFlag(true);
				mapHierarchyModel.setErrorMsg(valErrorMessage);
				return mapHierarchyModel;
			} else {
				mapHierarchyModel.setCountryList(countryModels);
				mapHierarchyModel.setTotalCountries(countryModels.size());
			}

			validateIfCountryHasNeighbour(targetCountry, countryModels, mapHierarchyModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapHierarchyModel;
	}

	/**
	 * validating if there is connectivity of country to any other country.
	 *
	 * @param targetCountry     the target country
	 * @param countryModels     the country models
	 * @param mapHierarchyModel the map hierarchy model
	 */
	private void validateIfCountryHasNeighbour(String targetCountry, ArrayList<CountryModel> countryModels,
			MapHierarchyModel mapHierarchyModel) {
		// TODO Auto-generated method stub
		boolean neighbourFlag = false;
		if (targetCountry != null) {
			for (CountryModel loopCountry : countryModels) {
				ArrayList<String> neighbours = loopCountry.getListOfNeighbours();
				for (String country : neighbours) {
					if (country.equalsIgnoreCase(targetCountry)) {
						neighbourFlag = true;
					}
				}
			}
			if (!neighbourFlag) {
				String valErrorMessage = "Map is invalid as there is no connectivity from or to country "
						+ targetCountry;
				mapHierarchyModel.setValErrorFlag(true);
				mapHierarchyModel.setErrorMsg(valErrorMessage);
			}
		}
	}

	/**
	 * convertMapDataToString Method Convert map data to string.
	 * 
	 * @param mapHierarchyModel the map hierarchy model
	 * @return the string
	 */
	public String convertMapDataToString(MapHierarchyModel mapHierarchyModel) {
		String data = "";

		if (mapHierarchyModel.getContinentsList() != null && mapHierarchyModel.getContinentsList().size() > 0) {
			data = data.concat("[Continents]" + System.lineSeparator());
			data = data.concat(System.lineSeparator());
			for (ContinentModel continentModel : mapHierarchyModel.getContinentsList()) {
				String continentData = continentModel.getContinentName() + "="
						+ continentModel.getCountriesList().size();
				data = data.concat(continentData + System.lineSeparator());
			}
			data = data.concat(System.lineSeparator());
			data = data.concat("[Territories]" + System.lineSeparator());
			data = data.concat(System.lineSeparator());
			for (ContinentModel continentModel : mapHierarchyModel.getContinentsList()) {
				data = data.concat(getContinentString(continentModel));
			}
		}

		return data;
	}

	/**
	 * Gets the continent string.
	 *
	 * @param continentModel the continent model
	 * @return the continent string
	 */
	public String getContinentString(ContinentModel continentModel) {
		String continentString = "";
		for (CountryModel countryModel : continentModel.getCountriesList()) {
			String countryData = countryModel.getCountryName() + ",0,0," + continentModel.getContinentName();
			for (String countryName : countryModel.getListOfNeighbours()) {
				if (!countryModel.getCountryName().trim().equalsIgnoreCase(countryName.trim()))
					countryData = countryData.concat("," + countryName);
			}
			countryData = countryData.concat(System.lineSeparator());
			continentString = continentString.concat(countryData);
		}
		return continentString;
	}

	/**
	 * saveMapFile Method Save map file.
	 * 
	 * @param mapHierarchyModel the map hierarchy model
	 * @param fileName          the file name
	 * @return true, if successful
	 */
	public boolean saveMapFile(MapHierarchyModel mapHierarchyModel, String fileName) {
		boolean isFileSaved = false;
		fileName = fileName.equals("") ? mapHierarchyModel.getConquestMapName() : fileName;
		String data = convertMapDataToString(mapHierarchyModel);

		if (!(data == null || data.isEmpty() || data.trim().equalsIgnoreCase(""))) {
			PrintWriter out = null;
			try {
				out = new PrintWriter(fileName + ".map");
				out.println(data);

				isFileSaved = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				isFileSaved = false;
			} finally {
				out.close();
			}
		}
		return isFileSaved;
	}

	/**
	 * beforeSaveValidation Method validate map.
	 *
	 * @param mapHierarchyModel the map hierarchy model
	 * @return true, if successful
	 */
	public boolean beforeSaveValidation(MapHierarchyModel mapHierarchyModel) {
		if (mapHierarchyModel.getCountryList().size() < 3) {
			JOptionPane.showMessageDialog(null,
					"Minimum three countries should be defined in the map as there are minimum three players which can play.");
			return false;
		}
		String targetCountry = null;
		for (ContinentModel continentModel : mapHierarchyModel.getContinentsList()) {
			for (CountryModel countryModel : continentModel.getCountriesList()) {
				if (countryModel.getListOfNeighbours().size() < 2) {
					targetCountry = countryModel.getCountryName();
					break;
				}
			}
		}
		if (targetCountry != null) {
			for (ContinentModel continentModel : mapHierarchyModel.getContinentsList()) {
				for (CountryModel countryModel : continentModel.getCountriesList()) {
					if (countryModel.searchNeighboursCountry(targetCountry) == null) {
						JOptionPane.showMessageDialog(null,
								"Map is invalid as there is no connectivity from or to country " + targetCountry);
						return false;
					}

				}
			}
		}
		return true;
	}

	/**
	 * Roll dice.
	 *
	 * @return the int
	 */
	public static int rollDice() {
		int pickedNumber;
		SecureRandom number = new SecureRandom();
		pickedNumber = number.nextInt(6);
		System.out.println("Roll Dice Value: " + pickedNumber);
//		log.info("Roll Dice starts \n Number:" + pickedNumber);

		return pickedNumber + 1;
	}

	/**
	 * Gets the player type from drop down.
	 *
	 * @param noOfPlayers          the no of players
	 * @param comboSelectedPlayers the combo selected players
	 * @return the player type from drop down
	 */
	public static PlayerType[] getPlayerTypeFromDropDown(int noOfPlayers, String[] comboSelectedPlayers) {
		// TODO Auto-generated method stub
		PlayerType[] playerTypes = new PlayerType[noOfPlayers];
		if (noOfPlayers >= 3) {
			playerTypes[0] = getPlayerType(comboSelectedPlayers[0]);
			playerTypes[1] = getPlayerType(comboSelectedPlayers[1]);
			playerTypes[2] = getPlayerType(comboSelectedPlayers[2]);
		}
		if (noOfPlayers >= 4)
			playerTypes[4] = getPlayerType(comboSelectedPlayers[3]);
		if (noOfPlayers == 5)
			playerTypes[5] = getPlayerType(comboSelectedPlayers[4]);

		return playerTypes;
	}

	/**
	 * Gets the player type.
	 *
	 * @param strPlayer the str player
	 * @return the player type
	 */
	public static PlayerType getPlayerType(String strPlayer) {

		PlayerType playerType = PlayerType.Human;
		if (strPlayer.trim().equalsIgnoreCase("Human"))
			playerType = PlayerType.Human;
		if (strPlayer.trim().equalsIgnoreCase("Aggresive"))
			playerType = PlayerType.Aggresive;
		if (strPlayer.trim().equalsIgnoreCase("Benevolent"))
			playerType = PlayerType.Benevolent;
		if (strPlayer.trim().equalsIgnoreCase("Random"))
			playerType = PlayerType.Random;
		if (strPlayer.trim().equalsIgnoreCase("Cheater"))
			playerType = PlayerType.Cheater;
		return playerType;
	}
	
	public static int defenderDicePopup(int maxNoOfDices) {
		Integer[] options = new Integer[maxNoOfDices];
		for (int i = 1; i <= maxNoOfDices; i++) {
			options[i-1] = i;
		}
		int noOfDices = (Integer) JOptionPane.showInputDialog(null, "Select no of dices to be used.", "Dice Selection.",
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		return noOfDices;
	}

}
