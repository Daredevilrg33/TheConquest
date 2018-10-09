/**
 * 
 */
package com.conquest.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapModel;

/**
 * @author Rohit Gupta
 *
 */
public class Utility {

	/**
	 * This method will provide the functionality to pick a map file.
	 * 
	 * @return : This will return the path of the map.
	 * 
	 */
	public static String pickFile() {
		// TODO Auto-generated method stub
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

	public MapModel parseMapFile(String filePath) {
		MapModel mapModel = new MapModel();
		List<ContinentModel> continentModels = new ArrayList<>();
		List<CountryModel> countryModels = new ArrayList<>();
		boolean isContinent = false;
		boolean isCountry = false;

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
					continue;
				}
				if (isContinent) {
					String[] continentValues = currentLine.split("=");
					if (continentValues.length > 0) {
						ContinentModel continentModel = new ContinentModel(continentValues[0]);
						if (continentValues.length > 1)
							continentModel.setNoOfArmies(Integer.valueOf(continentValues[1]));
						continentModels.add(continentModel);
					}
				}
				if (isCountry) {
					String[] countryValues = currentLine.split(",");
					CountryModel countryModel = new CountryModel();
					for (int i = 0; i < countryValues.length; i++) {
						if (i == 0) {
							countryModel.setCountryName(countryValues[i]);
						} else if (i == 1 || i == 2) {

						} else if (i == 3) {
							ContinentModel continentModel = new ContinentModel(countryValues[i]);
							countryModel.setBelongsTo(continentModel);

						} else {
							countryModel.addNeighbour(new CountryModel(countryValues[i]));
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
//				System.out.println(currentLine);
			}

			mapModel.setContinentModels(continentModels);
			mapModel.setCountryModels(countryModels);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapModel;
	}

}
