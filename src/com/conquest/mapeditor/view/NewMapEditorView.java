package com.conquest.mapeditor.view;

/**
 * @author Nancy Goyal
 *
 */
import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.conquest.mapeditor.controller.MapEditorController;
import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.renderer.TableRenderer;
import com.conquest.mapeditor.renderer.TreeRenderer;
import com.conquest.utilities.Constants;
import com.conquest.utilities.Utility;

public class NewMapEditorView extends JFrame {

	private static final long serialVersionUID = 4139667352960868764L;
	private JScrollPane treeScrollPane;
	private JScrollPane mappingScrollPane;
	private TreeRenderer treeView;
	private TableRenderer adjacencyTable;

	private JLabel labelConnectivity;

	private JButton addCountry;
	private JButton addContinent;
	private JButton jButtonSave;

	private MapHierarchyModel mapHierarchyModel;

	public NewMapEditorView(MapHierarchyModel mapHierarchyModel) {
		
		this.mapHierarchyModel = mapHierarchyModel;

		setLocationRelativeTo(null);

		setTitle("Map Editor View");
		setSize(Constants.MAP_EDITOR_WIDTH, Constants.MAP_EDITOR_HEIGHT);
		int screenWidth = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		int screenHeight = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		setLocation((screenWidth - 1280) / 2, (screenHeight - 700) / 2 - 30);
		setResizable(false);
		setLayout(null);
		this.enableEvents(AWTEvent.WINDOW_EVENT_MASK); // handle windows events
//		setDefaultCloseOperation(3); // set exit program when close the window

		DefaultMutableTreeNode continentRoot = new DefaultMutableTreeNode("Continent Hierarchy");
		treeView = new TreeRenderer(continentRoot);

		MapEditorController mapEditorController = new MapEditorController(this);
		
		mapEditorController.addModel(mapHierarchyModel);

		labelConnectivity = new JLabel("Connectivity Between Countries");
		Dimension size = labelConnectivity.getPreferredSize();
		labelConnectivity.setFont(new java.awt.Font("dialog", 1, 15));
		labelConnectivity.setBounds(15, 8, size.width + 200, size.height);
		add(labelConnectivity);

		mappingScrollPane = new JScrollPane(adjacencyTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		mappingScrollPane.setBounds(15, 55, 800, 600);
		add(mappingScrollPane);

		treeScrollPane = new JScrollPane(treeView, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		treeScrollPane.setBounds(mappingScrollPane.getBounds().x + (int) (mappingScrollPane.getBounds().getWidth()), 55,
				300, 600);
		add(treeScrollPane);

		addContinent = new JButton("Add Continent");
		addContinent.addActionListener(mapEditorController);
		addContinent.setBounds(treeScrollPane.getBounds().x, 20, size.width - 50, size.height + 10);
		add(addContinent);

		addCountry = new JButton("Add Country");
		addCountry.addActionListener(mapEditorController);
		addCountry.setBounds(addContinent.getBounds().x + (int) addContinent.getBounds().getWidth() + 10, 20,
				size.width - 50, size.height + 10);
		add(addCountry);

		jButtonSave = new JButton("Save");
		jButtonSave.addActionListener(mapEditorController);
		jButtonSave.setBounds(addCountry.getBounds().x + (int) addCountry.getBounds().getWidth() + 10, 20,
				size.width - 100, size.height + 10);
		add(jButtonSave);
		addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event. WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
				MapDashboard mapDashboard = new MapDashboard();
				mapDashboard.setVisible(true);
			}
		});
		if (mapHierarchyModel.getContinentsList().size() > 0) {
			updateHierarchyTree();
			updatePaintMatrix();
		}
	}

	/**
	 * @return the mapHierarchyModel
	 */
	public MapHierarchyModel getMapHierarchyModel() {
		return mapHierarchyModel;
	}

	/**
	 * @param mapHierarchyModel the mapHierarchyModel to set
	 */
	public void setMapHierarchyModel(MapHierarchyModel mapHierarchyModel) {
		this.mapHierarchyModel = mapHierarchyModel;
	}

	/**
	 * Method to refresh and update the continent hierarchy tree on adding new
	 * continent or new country
	 */

	public void updateHierarchyTree() {

		DefaultMutableTreeNode tRoot = new DefaultMutableTreeNode(
				"Map - " + mapHierarchyModel.getConquestMapName() + " ");
		for (ContinentModel continentObj : mapHierarchyModel.getContinentsList()) {
			ArrayList<CountryModel> loopCountriesList = continentObj.getCountriesList();
			DefaultMutableTreeNode continentNode = new DefaultMutableTreeNode(continentObj.getContinentName());
			for (CountryModel loopCountry : loopCountriesList) {
				continentNode.add(new DefaultMutableTreeNode(loopCountry.getCountryName()));
			}
			tRoot.add(continentNode);
		}
		treeView = new TreeRenderer(tRoot);
		treeView.setShowsRootHandles(true);
		treeScrollPane.getViewport().removeAll();
		treeScrollPane.getViewport().add(treeView);
	}

	/**
	 * Method to refresh and paint the adjacency table matrix of connections between
	 * the countries
	 */

	public void updatePaintMatrix() {

		DefaultTableModel tableMatrix = new DefaultTableModel(mapHierarchyModel.getTotalCountries(),
				mapHierarchyModel.getTotalCountries()) {

			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		String[][] vectorData = new String[mapHierarchyModel.getCountryList()
				.size()][mapHierarchyModel.getTotalCountries() + 1];
		String[] countriesColumn = new String[mapHierarchyModel.getCountryList().size() + 1];

		int columnCounter = 0;
		int rowCounter = 0;
		for (ContinentModel loopContinent : mapHierarchyModel.getContinentsList()) {
			ArrayList<CountryModel> loopCountriesList = loopContinent.getCountriesList();
			for (CountryModel loopCountry : loopCountriesList) {
				countriesColumn[0] = "C/C";
				countriesColumn[++columnCounter] = loopCountry.getCountryName();
				vectorData[rowCounter++][0] = loopCountry.getCountryName();
			}
		}

		tableMatrix.setDataVector(vectorData, countriesColumn);
		adjacencyTable = new TableRenderer(tableMatrix);
		adjacencyTable.setSize(300, 200);
		mappingScrollPane.getViewport().removeAll();
		mappingScrollPane.getViewport().add(adjacencyTable);
		List<CountryModel> countryModels = mapHierarchyModel.getCountryList();

		for (int i = 0; i < vectorData.length; i++) {
			for (int j = 1; j < vectorData[i].length; j++) {
				String neighbourCountryName = countriesColumn[j];
				String sourceCountryName = vectorData[i][0];
				for (CountryModel countryModel : countryModels) {
					System.out.println("Source Country: " + sourceCountryName);
					System.out.println("Neighbour Country: " + neighbourCountryName);
					if (countryModel.getCountryName().trim().equalsIgnoreCase(sourceCountryName.trim())) {
						for (CountryModel countryModel1 : countryModel.getListOfNeighbours()) {
							if (countryModel1.getCountryName().trim().equalsIgnoreCase(neighbourCountryName)) {
								adjacencyTable.setValueAt("1", i, j);

							}
						}
					}
				}

			}
		}
		adjacencyTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row = adjacencyTable.rowAtPoint(e.getPoint());
				int col = adjacencyTable.columnAtPoint(e.getPoint());
				String neighbourCountryName = countriesColumn[col];
				String sourceCountryName = vectorData[row][0];
				System.out.println(" Value in the cell clicked :" + adjacencyTable.getValueAt(row, col).toString());

				if (adjacencyTable.getValueAt(row, col) == "1") {
					for (CountryModel countryModel : countryModels) {
						System.out.println("Source Country: " + sourceCountryName);
						System.out.println("Neighbour Country: " + neighbourCountryName);
						if (countryModel.getCountryName().trim().equalsIgnoreCase(sourceCountryName.trim())) {
							for (CountryModel countryModel1 : countryModel.getListOfNeighbours()) {
								if (countryModel1.getCountryName().trim().equalsIgnoreCase(neighbourCountryName)) {
									countryModel.getListOfNeighbours().remove(countryModel1);
									adjacencyTable.setValueAt("0", row, col);
									return;
								}
							}
						}
					}

				} else {
					for (CountryModel countryModel : countryModels) {
						System.out.println("Source Country: " + sourceCountryName);
						System.out.println("Neighbour Country: " + neighbourCountryName);
						if (countryModel.getCountryName().trim().equalsIgnoreCase(sourceCountryName.trim())) {
							for (CountryModel countryModel1 : countryModel.getListOfNeighbours()) {
								if (countryModel1.getCountryName().trim().equalsIgnoreCase(neighbourCountryName)) {
									countryModel.addNeighbour(countryModel1);
									adjacencyTable.setValueAt("1", row, col);
									return;
								}
							}
						}
					}

				}

			}
		});

	}

}
