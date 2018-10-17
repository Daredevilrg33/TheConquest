package com.conquest.mapeditor.view;


import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.conquest.mapeditor.controller.MapEditorController;
import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.renderer.TableRenderer;
import com.conquest.mapeditor.renderer.TreeRenderer;
import com.conquest.utilities.Constants;

public class NewMapEditorView extends JFrame implements MouseListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4139667352960868764L;
	
	/** The tree scroll pane. */
	private JScrollPane treeScrollPane;
	
	/** The mapping scroll pane. */
	private JScrollPane mappingScrollPane;
	
	/** The tree view. */
	private TreeRenderer treeView;
	
	/** The adjacency table. */
	private TableRenderer adjacencyTable;

	/** The label connectivity. */
	private JLabel labelConnectivity;

	/** The add country button. */
	private JButton addCountry;
	
	/** The add continent button. */
	private JButton addContinent;
	
	/** The save button. */
	private JButton jButtonSave;
	private String[] countriesColumn;
	private String[][] vectorData;

	/** The map hierarchy model. */
	private MapHierarchyModel mapHierarchyModel;

	/**
	 * NewMapEditorView Constructor
	 * Instantiates a new new map editor view.
	 * @param mapHierarchyModel the map hierarchy model
	 */
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

		mappingScrollPane = new JScrollPane(adjacencyTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
	 * Gets the map hierarchy model.
	 * @return the mapHierarchyModel
	 */
	public MapHierarchyModel getMapHierarchyModel() {
		return mapHierarchyModel;
	}

	/**
	 * setMapHierarchyModel Method
	 * Sets the map hierarchy model.
	 * @param mapHierarchyModel the mapHierarchyModel to set
	 */
	public void setMapHierarchyModel(MapHierarchyModel mapHierarchyModel) {
		this.mapHierarchyModel = mapHierarchyModel;
	}

	/**
	 * updateHierarchyTree Method
	 * Method to refresh and update the continent hierarchy tree on adding new
	 * continent or new country.
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
	 * updatePaintMatrix Method
	 * Method to refresh and paint the adjacency table matrix of connections between
	 * the countries.
	 */

	public void updatePaintMatrix() {
		
		DefaultTableModel tableMatrix = new DefaultTableModel(mapHierarchyModel.getTotalCountries(), mapHierarchyModel.getTotalCountries()) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		vectorData = new String[mapHierarchyModel.getCountryList().size()][mapHierarchyModel.getCountryList().size() + 1];
//		System.out.println("updatepaintmatrix is active"+vectorData[][0]);
		countriesColumn = new String[mapHierarchyModel.getCountryList().size() + 1];

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
		adjacencyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		adjacencyTable.setRowHeight(20);
		TableColumnModel tableColumnModel = adjacencyTable.getColumnModel();
		for(int i=0;i<countriesColumn.length; i++ )
		{
			tableColumnModel.getColumn(i).setPreferredWidth(50);
		}
		
//		adjacencyTable.setSize(300, 200);
		mappingScrollPane.getViewport().removeAll();
		mappingScrollPane.getViewport().add(adjacencyTable);
		List<CountryModel> countryModels = mapHierarchyModel.getCountryList();

		for (int i = 0; i < vectorData.length; i++) {
			for (int j = 1; j < vectorData[i].length; j++) {
				String neighbourCountryName = countriesColumn[j];
				String sourceCountryName = vectorData[i][0];
				for (CountryModel countryModel : countryModels) {
					if (countryModel.getCountryName().trim().equalsIgnoreCase(sourceCountryName.trim())) {
						for (String countryName : countryModel.getListOfNeighbours()) {
							System.out.println("countryName" + countryName);
							System.out.println("neighbourCountryName" + neighbourCountryName);
							if (countryName.trim().equalsIgnoreCase(neighbourCountryName.trim())) {
								adjacencyTable.setValueAt("1", i, j);

							}else
							{
								adjacencyTable.setValueAt("0", i, j);
							}
						}
					}
				}

			}
		}
		adjacencyTable.addMouseListener(this);

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Mouse Clicked");
		int row = adjacencyTable.rowAtPoint(e.getPoint());
		int col = adjacencyTable.columnAtPoint(e.getPoint());
		String neighbourCountryName = countriesColumn[col];
		String sourceCountryName = vectorData[row][0];
		System.out.println(" Value in the cell clicked :" + adjacencyTable.getValueAt(row, col).toString() + "row--> " + row + "col--> "+ col);
	
		if (adjacencyTable.getValueAt(row, col) == "1") {
			for (CountryModel countryModel : mapHierarchyModel.getCountryList()) {
				System.out.println("Value 1 Source Country: " + sourceCountryName);
				System.out.println("Value 1 Neighbour Country: " + neighbourCountryName);
				if (countryModel.getCountryName().trim().equalsIgnoreCase(sourceCountryName.trim())) {
					for (String countryName : countryModel.getListOfNeighbours()) {
						if (countryName.trim().equalsIgnoreCase(neighbourCountryName)) {
							countryModel.getListOfNeighbours().remove(countryName);
							adjacencyTable.setValueAt("0", row, col);
							System.out.println(countryModel);
							return;
						}
					}
				}
			}

		} else if (adjacencyTable.getValueAt(row, col) == "0") {
			for (CountryModel countryModel : mapHierarchyModel.getCountryList()) {
				System.out.println("Value 2 Source Country: " + sourceCountryName);
				System.out.println("Value 2 Neighbour Country: " + neighbourCountryName);
				if (countryModel.getCountryName().trim().equalsIgnoreCase(sourceCountryName.trim())) {
					for (CountryModel countryModel1: mapHierarchyModel.getCountryList()) {
						if (countryModel1.getCountryName().trim().equalsIgnoreCase(neighbourCountryName)) {
							countryModel.addNeighbour(countryModel1.getCountryName().trim());
							adjacencyTable.setValueAt("1", row, col);
							System.out.println(countryModel);
							return;
						}
					}
				}
			}
		}
		
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
