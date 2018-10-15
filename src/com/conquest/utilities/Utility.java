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
import com.conquest.mapeditor.model.MapHierarchyModel;

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

	public MapHierarchyModel parseMapFile(String filePath) {
		MapHierarchyModel mapModel = new MapHierarchyModel();
		ArrayList<ContinentModel> continentModels = new ArrayList<>();
		ArrayList<CountryModel> countryModels = new ArrayList<>();
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
						//continentModels.add(continentModel);
					}
				}
				
				if (isCountry) {
					String[] countryValues = currentLine.split(",");
					CountryModel countryModel = new CountryModel();
					for (int i = 0; i < countryValues.length; i++) {
						if (i == 0) {
							countryModel.setCountryName(countryValues[i]);
						} else if (i == 1 || i == 2) {
							// do nothing as we have skipped the coordinates
						} else if (i == 3) {
							ContinentModel continentModel = new ContinentModel(countryValues[i]);
							countryModel.setBelongsTo(continentModel);
							continentModel.addCountry(countryModel);
							continentModels.add(continentModel);

						} else {
							countryModel.addNeighbour(new CountryModel(countryValues[i]));
						}

					}
					countryModels.add(countryModel);
					/*if (countryValues.length > 0) {
						for (ContinentModel continentModelValue : continentModels) {
							if (continentModelValue.getContinentName().trim()
									.equalsIgnoreCase(countryModel.getBelongsTo().getContinentName().trim())) {
								continentModelValue.addCountry(countryModel);
							}
						}

					}*/
				}
				System.out.println(currentLine);
			}

			mapModel.setContinentsList(continentModels);
			mapModel.setCountryList(countryModels);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapModel;
	}
	
	public boolean writeMapFile() {
		boolean isFileSaved = false;
		MapHierarchyModel mapHierarchyModel = new MapHierarchyModel("Demo.map",4);
		
		List<CountryModel> countriesModels = new ArrayList<>();
		List<ContinentModel> continentModels = new ArrayList<>();
		ContinentModel continentModelA = new ContinentModel("Continent A ");
		continentModelA.setNoOfArmies(5);
		ContinentModel continentModelB = new ContinentModel("Continent B ");
		continentModelB.setNoOfArmies(2);
		ContinentModel continentModelC = new ContinentModel("Continent C ");
		continentModelC.setNoOfArmies(6);
		ContinentModel continentModelD = new ContinentModel("Continent D ");
		continentModelD.setNoOfArmies(4);
		
		CountryModel countryModelA1 = new CountryModel("countryModelA1");
		CountryModel countryModelA2 = new CountryModel("countryModelA2");
		CountryModel countryModelA3 = new CountryModel("countryModelA3");
		CountryModel countryModelA4 = new CountryModel("countryModelA4");
		CountryModel countryModelB1 = new CountryModel("countryModelB1");
		CountryModel countryModelB2 = new CountryModel("countryModelB2");
		CountryModel countryModelB3 = new CountryModel("countryModelB3");
		CountryModel countryModelB4 = new CountryModel("countryModelB4");
		CountryModel countryModelC1 = new CountryModel("countryModelC1");
		CountryModel countryModelC2 = new CountryModel("countryModelC2");
		CountryModel countryModelC3 = new CountryModel("countryModelC3");
		CountryModel countryModelC4 = new CountryModel("countryModelC4");
		CountryModel countryModelD1 = new CountryModel("countryModelD1");
		CountryModel countryModelD2 = new CountryModel("countryModelD2");
		CountryModel countryModelD3 = new CountryModel("countryModelD3");
		CountryModel countryModelD4 = new CountryModel("countryModelD4");
		
		ArrayList<CountryModel> countryNeighboursA = new ArrayList<>();
		countryNeighboursA.add(countryModelA1); 
		countryNeighboursA.add(countryModelA2); 
		countryNeighboursA.add(countryModelA3); 
		countryNeighboursA.add(countryModelA4); 
		ArrayList<CountryModel> countryNeighboursB = new ArrayList<>();
		countryNeighboursB.add(countryModelB1); 
		countryNeighboursB.add(countryModelB2); 
		countryNeighboursB.add(countryModelB3); 
		countryNeighboursB.add(countryModelB4); 
		ArrayList<CountryModel> countryNeighboursC = new ArrayList<>();
		countryNeighboursC.add(countryModelC1); 
		countryNeighboursC.add(countryModelC2); 
		countryNeighboursC.add(countryModelC3); 
		countryNeighboursC.add(countryModelC4); 
		ArrayList<CountryModel> countryNeighboursD = new ArrayList<>();
		countryNeighboursD.add(countryModelD1); 
		countryNeighboursD.add(countryModelD2); 
		countryNeighboursD.add(countryModelD3); 
		countryNeighboursD.add(countryModelD4); 
		
		
		countriesModels.add(new CountryModel("ABC" ,continentModelA, countryNeighboursA));
		countriesModels.add(new CountryModel("DEF" ,continentModelB, countryNeighboursB));
		countriesModels.add(new CountryModel("GHI" ,continentModelC, countryNeighboursC));
		countriesModels.add(new CountryModel("JKL" ,continentModelD, countryNeighboursD));
//		mapHierarchyModel.addCountry(countryName, continentName)
		return isFileSaved;
	} 
	
}
