package com.conquest.mapeditor.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.view.NewMapEditorView;
import com.conquest.utilities.Utility;

public class MapEditorController implements ActionListener {
	private NewMapEditorView mapEditorView;
	private MapHierarchyModel mapHierarchyModel;

	public MapEditorController( NewMapEditorView mapEditorView) {
	
		this.mapEditorView = mapEditorView;
	}
	
	/**
	 * Method to add a model to this controller.
	 * @param mapHierarchyModel model
	 */
	public void addModel(MapHierarchyModel mapHierarchyModel)
	{
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
		switch (buttonName) {
		case "Add Continent":
			addContinent();
			break;
		case "Add Country":
			addCountry();
			break;
		case "Save":
			String fileName= "ROHIT";
			MapHierarchyModel mapHierarchyModel = mapEditorView.getMapHierarchyModel();
			Utility utility = new Utility();
			utility.saveMapFile(mapHierarchyModel,fileName);
			break;
		default:
			break;

		}

	}

	/**
	 * Method to implement response to addContinentBtn, provide GUI to input new
	 * continent's name,
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
					//mapEditorView.updatePaintMatrix();
					retry = false;
				}

			} else
				retry = false;
		}
	}

	/**
	 * Method to implement response to addCountryBtn, provide GUI to input new
	 * country's name,
	 */
	private void addCountry() {
		
		String lastOperatedContinent = "";

		if (mapHierarchyModel.getContinentsList().size()==0)
			JOptionPane.showMessageDialog(null,"Country must belong to a continent, create at least one continent.");
		else{
			//Configure the ConfirmDialog
			JTextField countryInput = new JTextField();
			String continents[]= new String[mapHierarchyModel.getContinentsList().size()];
			int loopcount = 0, defaultIndex = 0;
			for (ContinentModel loopContinent : mapHierarchyModel.getContinentsList()) {
				continents[loopcount++]=loopContinent.getContinentName();
				if (loopContinent.getContinentName().equals(lastOperatedContinent)) defaultIndex = loopcount-1; 
			}
			JComboBox<Object> continentInput = new JComboBox<Object>(continents);
			Object[] message = {
					"Choose Continent Name:", continentInput,
					"Enter country Name:", countryInput
			};  
			continentInput.setSelectedIndex(defaultIndex);
			boolean retry = true;
			while (retry){
				int option = JOptionPane.showConfirmDialog(null, message, "Input", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					if (countryInput.getText()==null||countryInput.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(null,"Country name can't be empty");
					}
					else if (continentInput.getSelectedIndex()==-1){
						JOptionPane.showMessageDialog(null,"Country must belong to a continent, choose one exiting continent or create a new one.");
					}
					else {
						String errorMsg;
						lastOperatedContinent = (String)continentInput.getItemAt(continentInput.getSelectedIndex());
						errorMsg = mapHierarchyModel.addCountry(countryInput.getText().trim(),lastOperatedContinent,mapHierarchyModel.getCountryList());
						if (errorMsg == ""){
							mapEditorView.updateHierarchyTree();
							mapEditorView.updatePaintMatrix();
							retry = false;
						}
						else JOptionPane.showMessageDialog(null, errorMsg);
					}
				}
				else retry = false;
			}	
		}
	}

	

}
