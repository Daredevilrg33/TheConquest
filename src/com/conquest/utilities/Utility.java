package com.conquest.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;

/**
 * The Class Utility.
 *
 * @author Rohit Gupta
 */
public class Utility {

	/**
	 * pickFile Method
	 * This method will provide the functionality to pick a map file.
	 * @return : This will return the path of the map.
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

	/**
	 * parseAndValidateMap Method
	 * Parses the and validate map.
	 * @param filePath the file path
	 * @return the map hierarchy model
	 */
	public MapHierarchyModel parseAndValidateMap(String filePath) {
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
					if(!isContinent && continentModels.size()==0)
					{
						String valErrorMessage ="Map is invalid as there are no continents defined";
						mapModel.setValErrorFlag(true);
						mapModel.setErrorMsg(valErrorMessage);
						break;
					}
					
					continue;
				}
				if (isContinent) {
					String[] continentValues = currentLine.split("=");
					if (continentValues.length > 0) {
						ContinentModel continentModel = new ContinentModel(continentValues[0]);
						if (continentValues.length > 1)
							continentModel.setControlValue(Integer.valueOf(continentValues[1]));
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

			mapModel.setContinentsList(continentModels);
			mapModel.setCountryList(countryModels);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapModel;
	}

	/**
	 * convertMapDataToString Method
	 * Convert map data to string.
	 * @param mapHierarchyModel the map hierarchy model
	 * @return the string
	 */
	private String convertMapDataToString(MapHierarchyModel mapHierarchyModel) {
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
				for (CountryModel countryModel : continentModel.getCountriesList()) {
					String countryData = countryModel.getCountryName() + ",0,0," + continentModel.getContinentName();
					for (String countryName : countryModel.getListOfNeighbours()) {
						countryData = countryData.concat("," + countryName);

					}
					countryData = countryData.concat(System.lineSeparator());
					data = data.concat(countryData);
				}
			}
		}

		return data;
	}

	/**
	 * saveMapFile Method
	 * Save map file.
	 * @param mapHierarchyModel the map hierarchy model
	 * @param fileName the file name
	 * @return true, if successful
	 */
	public boolean saveMapFile(MapHierarchyModel mapHierarchyModel, String fileName) {
		boolean isFileSaved = false;
		String data = convertMapDataToString(mapHierarchyModel);

		if (!(data == null || data.isEmpty() || data.trim().equalsIgnoreCase(""))) {
			PrintWriter out = null;
			try {
				out = new PrintWriter(fileName + ".map");
				out.println(data);

				isFileSaved = true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isFileSaved = false;
			} finally {
				out.close();
			}
		}
		return isFileSaved;
	}

}
