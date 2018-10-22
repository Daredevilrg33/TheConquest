package com.conquest.window;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.conquest.controller.GameWindowController;
import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.renderer.TableRenderer;
import com.conquest.mapeditor.renderer.TreeRenderer;
import com.conquest.utilities.Constants;

/**
 * The Class GameWindow.
 *
 * @author Rohit Gupta
 */
public class GameWindow extends JFrame implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 705136013521611381L;

	/** The game window controller. */
	private GameWindowController gameWindowController;

	/** The j player label. */
	private JLabel jPlayerLabel;

	/** The j player armies. */
	private JLabel jPlayerArmies;

	/** The j combo box countries. */
	private JComboBox<String> jComboBoxCountries;

	/** The j button place. */
	private JButton jButtonPlace;

	/** The selected country. */
	private String selectedCountry;

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

	private String[] countriesColumn;
	private String[][] vectorData;

	private MapHierarchyModel mapHierarchyModel;

	/**
	 * GameWindow Parameterized Constructor Instantiates a new game window.
	 * 
	 * @param mapHierarchyModel the map hierarchy model
	 * @param noOfPlayers       the no of players
	 */
	public GameWindow(MapHierarchyModel mapHierarchyModel, String noOfPlayers) {


		this.mapHierarchyModel = mapHierarchyModel;

		setTitle("Game Window");
		setResizable(false);
		setSize(Constants.MAP_EDITOR_WIDTH, Constants.MAP_EDITOR_HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		jPlayerLabel = new JLabel();
		jPlayerLabel.setBounds(50, 620, 100, 30);
		add(jPlayerLabel);

		jComboBoxCountries = new JComboBox<>();
		jComboBoxCountries.setBounds(170, 620, 100, 30);
		add(jComboBoxCountries);
		jButtonPlace = new JButton("Place");
		jButtonPlace.setBounds(290, 620, 100, 30);
		jButtonPlace.addActionListener(this);
		add(jButtonPlace);

		jPlayerArmies = new JLabel();
		jPlayerArmies.setBounds(410, 620, 200, 30);
		add(jPlayerArmies);

		DefaultMutableTreeNode continentRoot = new DefaultMutableTreeNode("Continent Hierarchy");
		treeView = new TreeRenderer(continentRoot);
		labelConnectivity = new JLabel("Connectivity Between Countries");
		Dimension size = labelConnectivity.getPreferredSize();
		labelConnectivity.setFont(new Font("dialog", 1, 15));
		labelConnectivity.setBounds(15, 8, size.width + 200, size.height);
		add(labelConnectivity);

		mappingScrollPane = new JScrollPane(adjacencyTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mappingScrollPane.setBounds(15, 55, 800, 550);
		add(mappingScrollPane);

		treeScrollPane = new JScrollPane(treeView, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		treeScrollPane.setBounds(mappingScrollPane.getBounds().x + (int) (mappingScrollPane.getBounds().getWidth()), 55,
				300, 550);
		add(treeScrollPane);
		gameWindowController = new GameWindowController(this, Integer.parseInt(noOfPlayers), this.mapHierarchyModel);

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {

			}

			@Override
			public void windowIconified(WindowEvent arg0) {

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
				MainMenuScreen initialScreen = new MainMenuScreen();
				initialScreen.setVisible(true);
			}

			@Override
			public void windowClosed(WindowEvent arg0) {

			}

			@Override
			public void windowActivated(WindowEvent arg0) {

			}
		});

		if (this.mapHierarchyModel.getContinentsList().size() > 0) {
			updateHierarchyTree();
			updatePaintMatrix();
		}
	}

	/**
	 * updateHierarchyTree Method Method to refresh and update the continent
	 * hierarchy tree on adding new continent or new country.
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
	 * updatePaintMatrix Method Method to refresh and paint the adjacency table
	 * matrix of connections between the countries.
	 */

	public void updatePaintMatrix() {
		int numberOfCountries = mapHierarchyModel.getCountryList().size();
		DefaultTableModel tableMatrix = new DefaultTableModel(numberOfCountries,numberOfCountries) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		vectorData = new String[numberOfCountries][numberOfCountries+ 1];
		countriesColumn = new String[numberOfCountries + 1];

		int columnCounter = 0,rowCounter = 0;
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
		for (int i = 0; i < countriesColumn.length; i++) {
			tableColumnModel.getColumn(i).setPreferredWidth(50);
		}

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
							if (countryName.trim().equalsIgnoreCase(neighbourCountryName.trim())) {
								adjacencyTable.setValueAt("1", i, j);
							} else {
								if (adjacencyTable.getValueAt(i, j) == null) {
									adjacencyTable.setValueAt("0", i, j);
								}
							}
						}
					}
				}

			}
		}

	}

	/**
	 * updatePlayerLabel Method Update player label.
	 * 
	 * @param playerName the player name
	 */
	public void updatePlayerLabel(String playerName) {
		jPlayerLabel.setText(playerName);
	}

	/**
	 * updatePlayerArmies Method Update player armies.
	 * 
	 * @param RemainingArmies the remaining armies
	 */
	public void updatePlayerArmies(int RemainingArmies) {
		jPlayerArmies.setText("Remaining Armies with player: " + RemainingArmies);
	}

	/**
	 * updateComboBoxCountries Method Update combo box countries.
	 * 
	 * @param countryModels the country models
	 */
	public void updateComboBoxCountries(List<CountryModel> countryModels) {
		jComboBoxCountries.removeAllItems();
		for (CountryModel countryModel : countryModels) {
			jComboBoxCountries.addItem(countryModel.getCountryName());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "Place":
			selectedCountry = jComboBoxCountries.getSelectedItem().toString();
			gameWindowController.checking(selectedCountry);
			gameWindowController.updateUIInfo();
			break;
		default:
			break;
		}
	}

}
