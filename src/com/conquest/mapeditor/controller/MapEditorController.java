package com.conquest.mapeditor.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.view.NewMapEditorView;
import com.conquest.utilities.Utility;

/**
 * MapEditorController Class using ActionListener {@link ActionListener} Object
 * of Classes NewMapEditorView {@link NewMapEditorView} and MapHierarchyModel
 * {@link MapHierarchyModel} are created.
 *
 *  @author Nancy Goyal
 */
public class MapEditorController implements ActionListener {

	/** The map editor view. */
	private NewMapEditorView mapEditorView;

	/** The map hierarchy model. */
	private MapHierarchyModel mapHierarchyModel;

	/** The selected node. */
	private String selectedNode;

	/**
	 * MapEditorController Constructor Constructor to give value to mapEditorView.
	 *
	 * @param mapEditorView Object of Class NewMapEditorView
	 *                      {@link NewMapEditorView}
	 */
	public MapEditorController(NewMapEditorView mapEditorView) {
		this.mapEditorView = mapEditorView;
	}

	/**
	 * addModel method Method to add a model to this controller.
	 * 
	 * @param mapHierarchyModel object of MapHierarchyModel
	 *                          {@link MapHierarchyModel}
	 */
	public void addModel(MapHierarchyModel mapHierarchyModel) {
		this.mapHierarchyModel = mapHierarchyModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		String buttonName = event.getActionCommand();
		selectedNode = mapEditorView.getUserSelTreeNode();
		switch (buttonName) {
		case "Add Continent":
			addContinent();
			break;
		case "Add Country":
			addCountry();
			break;
		case "Save":
			saveMapFile();
			break;
		case "Delete Continent":
			deleteContinent();
			break;
		case "Delete Country":
			deleteCountry();
			break;
		case "Rename Continent":
			renameContinent();
			break;
		case "Move to another continent":
			moveCountry();
			break;
		default:
			break;

		}

	}

	/**
	 * addContinent method Void Method to implement response to addContinentBtn,
	 * provide GUI to input new continent's name,.
	 */
	private void addContinent() {
		boolean retry = true;
		while (retry) {
			String inputContinent = JOptionPane.showInputDialog(null, "Enter the continent name: ");
			if (inputContinent != null) {
				if ((inputContinent = inputContinent.trim()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Continent name can't be empty");
				} else {
					mapEditorView.getMapHierarchyModel().addContinent(inputContinent);
					mapEditorView.updateHierarchyTree();

					retry = false;
				}

			} else
				retry = false;
		}
	}

	/**
	 * addCountry method Void Method to implement response to addCountryBtn, provide
	 * GUI to input new country's name.
	 */
	private void addCountry() {

		String lastOperatedContinent = "";

		if (mapHierarchyModel.getContinentsList().size() == 0)
			JOptionPane.showMessageDialog(null, "Country must belong to a continent, create at least one continent.");
		else {
			// Configure the ConfirmDialog
			JTextField countryInput = new JTextField();
			String continents[] = new String[mapHierarchyModel.getContinentsList().size()];
			int loopcount = 0, defaultIndex = 0;
			for (ContinentModel loopContinent : mapHierarchyModel.getContinentsList()) {
				continents[loopcount++] = loopContinent.getContinentName();
				if (loopContinent.getContinentName().equals(lastOperatedContinent))
					defaultIndex = loopcount - 1;
			}
			JComboBox<Object> continentInput = new JComboBox<Object>(continents);
			Object[] message = { "Choose Continent Name:", continentInput, "Enter country Name:", countryInput };
			continentInput.setSelectedIndex(defaultIndex);
			boolean retry = true;
			while (retry) {
				int option = JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					if (countryInput.getText() == null || countryInput.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Country name can't be empty");
					} else if (continentInput.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null,
								"Country must belong to a continent, choose one exiting continent or create a new one.");
					} else {
						String errorMsg;
						lastOperatedContinent = (String) continentInput.getItemAt(continentInput.getSelectedIndex());
						ArrayList<String> countryNameList = new ArrayList<>();
						countryNameList.add(countryInput.getText().trim());
						for (CountryModel countryModel : mapHierarchyModel.getCountryList()) {
							if (!countryModel.getCountryName().trim().equalsIgnoreCase(countryInput.getText().trim())) {
								countryModel.addNeighbour(countryInput.getText().trim());
							}
							countryNameList.add(countryModel.getCountryName());

						}
						errorMsg = mapHierarchyModel.addCountry(countryInput.getText().trim(), lastOperatedContinent,
								countryNameList);
						if (errorMsg == "") {
							mapEditorView.updateHierarchyTree();
							mapEditorView.updatePaintMatrix();
							retry = false;
						} else
							JOptionPane.showMessageDialog(null, errorMsg);
					}
				} else
					retry = false;
			}
		}
	}

	/**
	 * saveMapFile method Void Method to implement response to save button, provide
	 * GUI to input new map's name,.
	 */
	private void saveMapFile() {
		boolean retry = true;
		Utility utility = new Utility();
		boolean saveVal=utility.beforeSaveValidation(mapHierarchyModel);
		while (retry && saveVal) {
			String mapFileName = JOptionPane.showInputDialog(null, "Enter the name of the map: ");
			if (mapFileName != null) {
				if ((mapFileName = mapFileName.trim()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Map file name can't be empty");
				} else {
					utility.saveMapFile(mapHierarchyModel, mapFileName);
					retry = !saveVal;
				}

			} else
				retry = false;
		}
	}

	/**
	 * deleteContinent method Void Method to implement response to
	 * deleteContinentMenu, provide GUI to delete continent in tree.
	 */
	private void deleteContinent() {
		String errorMsg;
		if ((errorMsg = mapHierarchyModel.deleteContinent(selectedNode)) == "") {
			mapEditorView.updateHierarchyTree();
		} else
			JOptionPane.showMessageDialog(null, errorMsg);

	}

	/**
	 * deleteCountry method Void Method to implement response to deleteCountryMenu,
	 * provide GUI to delete country in tree.
	 */
	private void deleteCountry() {
		String errorMsg;
		if ((errorMsg = mapHierarchyModel.deleteCountry(selectedNode)) == "") {
			for(CountryModel countryModel : mapHierarchyModel.getCountryList()) {
				for(String countryName  : countryModel.getListOfNeighbours()) {
					if(countryName.trim().equalsIgnoreCase(selectedNode.trim())) {
						countryModel.getListOfNeighbours().remove(countryName);
						break;
					}
				}
			}
			mapEditorView.updateHierarchyTree();
			mapEditorView.updatePaintMatrix();
		} else
			JOptionPane.showMessageDialog(null, errorMsg);

	}

	/**
	 * renameContinent method Void Method to implement response to
	 * renameContinentMenu, provide GUI to rename continent in tree.
	 */
	private void renameContinent() {
		String errorMsg;
		boolean retry = true;
		while (retry) {
			String inputContinent = JOptionPane.showInputDialog(null, "Enter the new continent name: ");
			if (inputContinent != null) {
				if ((inputContinent = inputContinent.trim()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Continent name can't be empty");
				} else {
					if ((errorMsg = mapHierarchyModel.renameContinent(selectedNode, inputContinent)) == "") {
						mapEditorView.updateHierarchyTree();
						retry = false;
					} else
						JOptionPane.showMessageDialog(null, errorMsg);
				}
			} else
				retry = false;
		}

	}
	/**
	 * moveCountry method Void Method to implement response to moveCountryMenu,
	 * provide GUI to move country to another continent in tree.
	 */
	private void moveCountry() {
		String errorMsg;
		boolean retry = true;
		while (retry) {
			String inputContinent = JOptionPane.showInputDialog(null,
					"Enter the name of the continent to which you want to move this country: ");
			if (inputContinent != null) {
				if ((inputContinent = inputContinent.trim()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Continent name can't be empty");
				} else {
					if ((errorMsg = mapHierarchyModel.moveCountry(selectedNode, inputContinent)) == "") {
						mapEditorView.updateHierarchyTree();
						retry = false;
					} else
						JOptionPane.showMessageDialog(null, errorMsg);
				}
			} else
				retry = false;
		}

	}
}
