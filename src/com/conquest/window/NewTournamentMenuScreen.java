package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.utilities.Constants;
import com.conquest.utilities.Utility;

/**
 * The Class NewTournamentMenuScreen.
 */
public class NewTournamentMenuScreen extends JFrame implements ActionListener {

	/** The combo box number of maps. */
	private JComboBox<String> comboBoxNumberOfMaps;

	/** The combo box select no of player. */
	private JComboBox<String> comboBoxSelectNoOfPlayer;

	/** The combo box player one behaviour. */
	private JComboBox<String> comboBoxPlayerOneBehaviour;

	/** The combo box player two behaviour. */
	private JComboBox<String> comboBoxPlayerTwoBehaviour;

	/** The combo box player three behaviour. */
	private JComboBox<String> comboBoxPlayerThreeBehaviour;

	/** The combo box player four behaviour. */
	private JComboBox<String> comboBoxPlayerFourBehaviour;

	/** The combo box number of games. */
	private JComboBox<String> comboBoxNumberOfGames;

	/** The combo box number of turns. */
	private JComboBox<String> comboBoxNumberOfTurns;

	/** The choose map one. */
	private JButton chooseMapOne;

	/** The choose map two. */
	private JButton chooseMapTwo;

	/** The choose map three. */
	private JButton chooseMapThree;

	/** The choose map four. */
	private JButton chooseMapFour;

	/** The choose map five. */
	private JButton chooseMapFive;

	/** The start tournament. */
	private JButton startTournament;

	/** The map list. */
	private String[] mapList = new String[] { "1", "2", "3", "4", "5" };

	/** The players list. */
	private String[] playersList = new String[] { "2", "3", "4" };

	/** The behaviour list. */
	private String[] behaviourList = new String[] { "Agressive", "Benevolent", "Random", "Cheater" };

	/** The no of game list. */
	private String[] noOfGameList = new String[] { "1", "2", "3", "4", "5" };

	/** The no of turns list. */
	private String[] noOfTurnsList = new String[] { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
			"39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" };

	/** The file path one. */
	private String filePathOne;

	/** The file name one. */
	private String fileNameOne;

	/** The file path two. */
	private String filePathTwo;

	/** The file name two. */
	private String fileNameTwo;

	/** The file path three. */
	private String filePathThree;

	/** The file name three. */
	private String fileNameThree;

	/** The file path four. */
	private String filePathFour;

	/** The file name four. */
	private String fileNameFour;

	/** The file path five. */
	private String filePathFive;

	/** The file name five. */
	private String fileNameFive;

	/**
	 * Instantiates a new new tournament menu screen.
	 */
	public NewTournamentMenuScreen() {

		JLabel labelSelectNoOfMap = new JLabel();
		labelSelectNoOfMap.setText("Select Number of Map:");
		labelSelectNoOfMap.setBounds(70, 50, 130, 30);
		add(labelSelectNoOfMap);

		comboBoxNumberOfMaps = new JComboBox<>(mapList);
		comboBoxNumberOfMaps.setBounds(300, 50, 100, 30);
		add(comboBoxNumberOfMaps);
		//comboBoxNumberOfMaps.setSelectedIndex(-1);


		chooseMapOne = new JButton("Select");
		chooseMapOne.setBounds(70, 100, 70, 30);
		chooseMapOne.addActionListener(this);
		add(chooseMapOne);

		chooseMapTwo = new JButton("Select");
		chooseMapTwo.setBounds(180, 100, 70, 30);
		chooseMapTwo.addActionListener(this);
		add(chooseMapTwo);

		chooseMapThree = new JButton("Select");
		chooseMapThree.setBounds(290, 100, 70, 30);
		chooseMapThree.addActionListener(this);
		add(chooseMapThree);

		chooseMapFour = new JButton("Select");
		chooseMapFour.setBounds(400, 100, 70, 30);
		chooseMapFour.addActionListener(this);
		add(chooseMapFour);

		chooseMapFive = new JButton("Select");
		chooseMapFive.setBounds(510, 100, 70, 30);
		chooseMapFive.addActionListener(this);
		add(chooseMapFive);

		JLabel labelSelectNoOfPlayers = new JLabel();
		labelSelectNoOfPlayers.setText("Select Number of Players");
		labelSelectNoOfPlayers.setBounds(70, 150, 140, 30);
		add(labelSelectNoOfPlayers);
		

		comboBoxSelectNoOfPlayer = new JComboBox<>(playersList);
		comboBoxSelectNoOfPlayer.setBounds(300, 150, 100, 30);
		add(comboBoxSelectNoOfPlayer);
		//comboBoxSelectNoOfPlayer.setSelectedIndex(-1);

		comboBoxPlayerOneBehaviour = new JComboBox<>(behaviourList);
		comboBoxPlayerOneBehaviour.setBounds(70, 200, 100, 30);
		add(comboBoxPlayerOneBehaviour);
		//comboBoxPlayerOneBehaviour.setSelectedIndex(-1);

		comboBoxPlayerTwoBehaviour = new JComboBox<>(behaviourList);
		comboBoxPlayerTwoBehaviour.setBounds(180, 200, 100, 30);
		add(comboBoxPlayerTwoBehaviour);
		//comboBoxPlayerTwoBehaviour.setSelectedIndex(-1);

		comboBoxPlayerThreeBehaviour = new JComboBox<>(behaviourList);
		comboBoxPlayerThreeBehaviour.setBounds(290, 200, 100, 30);
		add(comboBoxPlayerThreeBehaviour);
		//comboBoxPlayerThreeBehaviour.setSelectedIndex(-1);

		comboBoxPlayerFourBehaviour = new JComboBox<>(behaviourList);
		comboBoxPlayerFourBehaviour.setBounds(400, 200, 100, 30);
		add(comboBoxPlayerFourBehaviour);
		//comboBoxPlayerFourBehaviour.setSelectedIndex(-1);

		JLabel labelSelectNoOfGames = new JLabel();
		labelSelectNoOfGames.setText("Select Number of Games");
		labelSelectNoOfGames.setBounds(70, 250, 150, 30);
		add(labelSelectNoOfGames);

		comboBoxNumberOfGames = new JComboBox<>(noOfGameList);
		comboBoxNumberOfGames.setBounds(300, 250, 100, 30);
		add(comboBoxNumberOfGames);
		//comboBoxNumberOfGames.setSelectedIndex(-1);

		JLabel labelSelectNoOfTurns = new JLabel();
		labelSelectNoOfTurns.setText("Select Number of Turns");
		labelSelectNoOfTurns.setBounds(70, 300, 150, 30);
		add(labelSelectNoOfTurns);

		comboBoxNumberOfTurns = new JComboBox<>(noOfTurnsList);
		comboBoxNumberOfTurns.setBounds(300, 300, 100, 30);
		add(comboBoxNumberOfTurns);
		//comboBoxNumberOfTurns.setSelectedIndex(-1);


		startTournament = new JButton("Start Tournament");
		startTournament.setBounds(250, 400, 150, 30);
		startTournament.addActionListener(this);
		add(startTournament);

		setTitle("New Tournament Menu");
		setResizable(false);
		setSize(1300, 700);
		setLayout(null);
		setLocationRelativeTo(null);

		
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				MainMenuScreen mainMenuScreen = new MainMenuScreen();
				mainMenuScreen.setVisible(true);
				dispose();
			}
		});
	}

	/**
	 * Checks if is map connected.
	 *
	 * @param mapHierarchyModel the map hierarchy model
	 * @return true, if is map connected
	 */
	public boolean isMapConnected(MapHierarchyModel mapHierarchyModel) {
		boolean isConnected = true;
		dfsUsingStack(mapHierarchyModel, mapHierarchyModel.getCountryList().get(1));
		for (CountryModel countryModel : mapHierarchyModel.getCountryList()) {
			if (countryModel.isVisited())
				countryModel.setVisited(false);
			else {
				isConnected = false;
				break;
			}
		}
		if (!isConnected) {
			mapHierarchyModel.setErrorMsg("Map is not connected !!");
			mapHierarchyModel.setValErrorFlag(true);
		}

		return isConnected;
	}

	/**
	 * Dfs using stack.
	 *
	 * @param mapHierarchyModel the map hierarchy model
	 * @param countryModel      the country model
	 */
	public void dfsUsingStack(MapHierarchyModel mapHierarchyModel, CountryModel countryModel) {
		Stack<CountryModel> stack = new Stack<CountryModel>();
		stack.add(countryModel);
		countryModel.setVisited(true);
		while (!stack.isEmpty()) {
			CountryModel element = stack.pop();
			System.out.println("DFS CountryName: " + element.getCountryName() + " ");
			List<String> neigbourNames = element.getListOfNeighbours();
			List<CountryModel> neighbours = new ArrayList<>();

			for (String neighbourName : neigbourNames) {

				neighbours.add(mapHierarchyModel.searchCountry(neighbourName));
			}
			for (int i = 0; i < neighbours.size(); i++) {
				CountryModel n = neighbours.get(i);
				if (n != null && !n.isVisited()) {
					stack.add(n);
					n.setVisited(true);

				}
			}
		}
	}

	/**
	 * Checks if is continent connected.
	 *
	 * @param mapHierarchyModel the map hierarchy model
	 * @return true, if is continent connected
	 */
	public boolean isContinentConnected(MapHierarchyModel mapHierarchyModel) {
		boolean isConnected = true;
		for (ContinentModel continentModel : mapHierarchyModel.getContinentsList()) {
			List<CountryModel> countryList = continentModel.getCountriesList();
			dfsUsingStackContinent(continentModel, mapHierarchyModel.getCountryList().get(1));
			for (CountryModel countryModel : continentModel.getCountriesList()) {
				if (countryModel.isVisited())
					countryModel.setVisited(false);
				else {
					isConnected = false;
					mapHierarchyModel
							.setErrorMsg("Continent: " + continentModel.getContinentName() + " is not connected !!");
					mapHierarchyModel.setValErrorFlag(true);
					return isConnected;

				}
			}
		}

		return isConnected;
	}

	/**
	 * Dfs using stack continent.
	 *
	 * @param continentModel the continent model
	 * @param countryModel   the country model
	 */
	public void dfsUsingStackContinent(ContinentModel continentModel, CountryModel countryModel) {
		Stack<CountryModel> stack = new Stack<CountryModel>();
		stack.add(countryModel);
		countryModel.setVisited(true);
		while (!stack.isEmpty()) {
			CountryModel element = stack.pop();
			System.out.println("DFS CountryName: " + element.getCountryName() + " ");
			List<String> neigbourNames = element.getListOfNeighbours();
			List<CountryModel> neighbours = new ArrayList<>();

			for (String neighbourName : neigbourNames) {

				neighbours.add(continentModel.searchCountry(neighbourName));
			}
			for (int i = 0; i < neighbours.size(); i++) {
				CountryModel n = neighbours.get(i);
				if (n != null && !n.isVisited()) {
					stack.add(n);
					n.setVisited(true);

				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {

		if ((event.getSource() == chooseMapOne)) {
			filePathOne = Utility.pickFile();
			System.out.println("File Path: " + filePathOne);
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = filePathOne.split(pattern);
			fileNameOne = splitFilePath[splitFilePath.length - 1];
			System.out.println("File Name: " + fileNameOne);
		}
		if ((event.getSource() == chooseMapTwo)) {
			filePathTwo = Utility.pickFile();
			System.out.println("File Path: " + filePathTwo);
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = filePathTwo.split(pattern);
			fileNameTwo = splitFilePath[splitFilePath.length - 1];
			System.out.println("File Name: " + fileNameTwo);

		}
		if ((event.getSource() == chooseMapThree)) {
			filePathThree = Utility.pickFile();
			System.out.println("File Path: " + filePathThree);
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = filePathThree.split(pattern);
			fileNameThree = splitFilePath[splitFilePath.length - 1];
			System.out.println("File Name: " + fileNameThree);

		}
		if ((event.getSource() == chooseMapFour)) {
			filePathFour = Utility.pickFile();
			System.out.println("File Path: " + filePathFour);
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = filePathFour.split(pattern);
			fileNameFour = splitFilePath[splitFilePath.length - 1];
			System.out.println("File Name: " + fileNameFour);

		}
		if ((event.getSource() == chooseMapFive)) {
			filePathFive = Utility.pickFile();
			System.out.println("File Path: " + filePathFive);
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = filePathFive.split(pattern);
			fileNameFive = splitFilePath[splitFilePath.length - 1];
			System.out.println("File Name: " + fileNameFive);

		}
		if ((event.getSource() == startTournament)) {
//				if (fileNameOne == null || filePathOne == null || fileNameOne.isEmpty() || filePathOne.isEmpty()) {
//					JOptionPane.showMessageDialog(this, "Select Map to continue.", "Error Message",
//							JOptionPane.ERROR_MESSAGE);
//				} else {
//
//					Utility utility = new Utility();
//					MapHierarchyModel mapModel = utility.parseAndValidateMap(filePathOne);
//					mapModel.setConquestMapName(fileNameOne);
//					if (!mapModel.isValErrorFlag()) {
//						boolean isConnected = isMapConnected(mapModel);
//						System.out.println("Map Is Connected: " + isConnected);
//
//					}
//				}
//				if (fileNameTwo == null || filePathTwo == null || fileNameTwo.isEmpty() || filePathTwo.isEmpty()) {
//					JOptionPane.showMessageDialog(this, "Select Map to continue.", "Error Message",
//							JOptionPane.ERROR_MESSAGE);
//				} else {
//
//					Utility utility = new Utility();
//					MapHierarchyModel mapModel = utility.parseAndValidateMap(filePathTwo);
//					mapModel.setConquestMapName(fileNameTwo);
//					if (!mapModel.isValErrorFlag()) {
//						boolean isConnected = isMapConnected(mapModel);
//						System.out.println("Map Is Connected: " + isConnected);
//
//					}
//				}
//				if (fileNameThree == null || filePathThree == null || fileNameThree.isEmpty() || filePathThree.isEmpty()) {
//					JOptionPane.showMessageDialog(this, "Select Map to continue.", "Error Message",
//							JOptionPane.ERROR_MESSAGE);
//				} else {
//
//					Utility utility = new Utility();
//					MapHierarchyModel mapModel = utility.parseAndValidateMap(filePathThree);
//					mapModel.setConquestMapName(fileNameThree);
//					if (!mapModel.isValErrorFlag()) {
//						boolean isConnected = isMapConnected(mapModel);
//						System.out.println("Map Is Connected: " + isConnected);
//
//					}
//				}
//				if (fileNameFour == null || filePathFour == null || fileNameFour.isEmpty() || filePathFour.isEmpty()) {
//					JOptionPane.showMessageDialog(this, "Select Map to continue.", "Error Message",
//							JOptionPane.ERROR_MESSAGE);
//				} else {
//
//					Utility utility = new Utility();
//					MapHierarchyModel mapModel = utility.parseAndValidateMap(filePathFour);
//					mapModel.setConquestMapName(fileNameFour);
//					if (!mapModel.isValErrorFlag()) {
//						boolean isConnected = isMapConnected(mapModel);
//						System.out.println("Map Is Connected: " + isConnected);
//
//					}
//				}
//				if (fileNameFive == null || filePathFive == null || fileNameFive.isEmpty() || filePathFive.isEmpty()) {
//					JOptionPane.showMessageDialog(this, "Select Map to continue.", "Error Message",
//							JOptionPane.ERROR_MESSAGE);
//				} else {
//
//					Utility utility = new Utility();
//					MapHierarchyModel mapModel = utility.parseAndValidateMap(filePathFive);
//					mapModel.setConquestMapName(fileNameFive);
//					if (!mapModel.isValErrorFlag()) {
//						boolean isConnected = isMapConnected(mapModel);
//						System.out.println("Map Is Connected: " + isConnected);
//
//					}
//				}
//
//		
		}
	}
}
