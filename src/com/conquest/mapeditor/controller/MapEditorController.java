package com.conquest.mapeditor.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.renderer.TreeRenderer;
import com.conquest.mapeditor.view.MapDashboard;
import com.conquest.mapeditor.view.NewMapEditorView;
import com.conquest.window.SecondScreen;

public class MapEditorController implements ActionListener {
	private MapHierarchyModel newMapHierarchyModel;
	private TreeRenderer treeRoot;
	

	public MapEditorController(TreeRenderer treeView) {
		this.treeRoot=treeView;
	}

	/**
	 * Method to add a model to this controller.
	 * 
	 * @param mapHierarchyModel
	 *            model
	 */
	public void intializeModel(MapHierarchyModel mapHierarchyModel) {
		this.newMapHierarchyModel = mapHierarchyModel;
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
		default:
			System.exit(0);

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
					newMapHierarchyModel.addContinent(inputContinent);
					updateHierarchyTree();
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

	}

	private void updateHierarchyTree() {

		DefaultMutableTreeNode tRoot = new DefaultMutableTreeNode(
				"Map - " + newMapHierarchyModel.getConquestMapName() + " ");
		for (ContinentModel continentObj : newMapHierarchyModel.getContinentsList()) {
			DefaultMutableTreeNode continentNode = new DefaultMutableTreeNode(continentObj.getContinentName());
			tRoot.add(continentNode);
		}
		treeRoot =new TreeRenderer(tRoot);
	}

}
