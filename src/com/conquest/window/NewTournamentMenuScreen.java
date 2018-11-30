package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.conquest.controller.TournamentController;
import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.model.AggresivePlayer;
import com.conquest.model.BenevolentPlayer;
import com.conquest.model.CheaterPlayer;
import com.conquest.model.GameModel;
import com.conquest.model.PlayerModel;
import com.conquest.model.PlayerType;
import com.conquest.model.RandomPlayer;
import com.conquest.utilities.Utility;

/**
 * The Class NewTournamentMenuScreen.
 */
public class NewTournamentMenuScreen extends JFrame implements ActionListener, Observer {

	/** The combo box number of maps. */
	private JComboBox<String> comboBoxNumberOfMaps;

	/** The combo box select no of player. */
	private JComboBox<String> comboBoxSelectNoOfPlayer;

	/** The combo box player one. */
	private JComboBox<String> comboBoxPlayer1;

	/** The combo box player two . */
	private JComboBox<String> comboBoxPlayer2;

	/** The combo box player three . */
	private JComboBox<String> comboBoxPlayer3;

	/** The combo box player four . */
	private JComboBox<String> comboBoxPlayer4;

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
	private String[] behaviourList = new String[] { "Aggresive", "Benevolent", "Random", "Cheater" };

	/** The no of game list. */
	private String[] noOfGameList = new String[] { "1", "2", "3", "4", "5" };

	/** The no of turns list. */
	private String[] noOfTurnsList = new String[] { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
			"39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" };

	/** The tournament obj. */
	private TournamentController tournamentObj;

	private String[] mapFilePath = new String[5];

	private String[] mapFileName = new String[5];

	private MapHierarchyModel[] mapHierarchyModels = new MapHierarchyModel[5];

	private List<GameModel> gameModels;
	private int noOfMaps = 1;
	private HashMap<String, ArrayList<String>> results = new HashMap<>();
	private static int gameNumber = 0;
	private int noOfGamesToBePlayed = 0;

	/**
	 * Instantiates a new new tournament menu screen.
	 */
	public NewTournamentMenuScreen() {
		gameModels = new ArrayList<>();
		JLabel labelSelectNoOfMap = new JLabel();
		labelSelectNoOfMap.setText("Select Number of Map:");
		labelSelectNoOfMap.setBounds(70, 50, 130, 30);
		add(labelSelectNoOfMap);

		comboBoxNumberOfMaps = new JComboBox<>(mapList);
		comboBoxNumberOfMaps.setBounds(300, 50, 100, 30);

		comboBoxNumberOfMaps.addActionListener(this);
		add(comboBoxNumberOfMaps);

		chooseMapOne = new JButton("Select");
		chooseMapOne.setBounds(70, 100, 70, 30);
		chooseMapOne.addActionListener(this);
		chooseMapOne.setVisible(true);
		add(chooseMapOne);

		chooseMapTwo = new JButton("Select");
		chooseMapTwo.setBounds(180, 100, 70, 30);
		chooseMapTwo.addActionListener(this);
		chooseMapTwo.setVisible(false);
		add(chooseMapTwo);

		chooseMapThree = new JButton("Select");
		chooseMapThree.setBounds(290, 100, 70, 30);
		chooseMapThree.addActionListener(this);
		chooseMapThree.setVisible(false);
		add(chooseMapThree);

		chooseMapFour = new JButton("Select");
		chooseMapFour.setBounds(400, 100, 70, 30);
		chooseMapFour.setVisible(false);
		chooseMapFour.addActionListener(this);
		add(chooseMapFour);

		chooseMapFive = new JButton("Select");
		chooseMapFive.setBounds(510, 100, 70, 30);
		chooseMapFive.setVisible(false);
		chooseMapFive.addActionListener(this);
		add(chooseMapFive);

		JLabel labelSelectNoOfPlayers = new JLabel();
		labelSelectNoOfPlayers.setText("Select Number of Players");
		labelSelectNoOfPlayers.setBounds(70, 150, 140, 30);
		add(labelSelectNoOfPlayers);

		comboBoxSelectNoOfPlayer = new JComboBox<>(playersList);
		comboBoxSelectNoOfPlayer.setBounds(300, 150, 100, 30);
		comboBoxSelectNoOfPlayer.addActionListener(this);
		add(comboBoxSelectNoOfPlayer);

		comboBoxPlayer1 = new JComboBox<>(behaviourList);
		comboBoxPlayer1.setBounds(70, 200, 100, 30);
		add(comboBoxPlayer1);

		comboBoxPlayer2 = new JComboBox<>(behaviourList);
		comboBoxPlayer2.setBounds(180, 200, 100, 30);
		add(comboBoxPlayer2);

		comboBoxPlayer3 = new JComboBox<>(behaviourList);
		comboBoxPlayer3.setBounds(290, 200, 100, 30);
		comboBoxPlayer3.setVisible(false);
		add(comboBoxPlayer3);

		comboBoxPlayer4 = new JComboBox<>(behaviourList);
		comboBoxPlayer4.setBounds(400, 200, 100, 30);
		comboBoxPlayer4.setVisible(false);
		add(comboBoxPlayer4);

		JLabel labelSelectNoOfGames = new JLabel();
		labelSelectNoOfGames.setText("Select Number of Games");
		labelSelectNoOfGames.setBounds(70, 250, 150, 30);
		add(labelSelectNoOfGames);

		comboBoxNumberOfGames = new JComboBox<>(noOfGameList);
		comboBoxNumberOfGames.setBounds(300, 250, 100, 30);
		add(comboBoxNumberOfGames);

		JLabel labelSelectNoOfTurns = new JLabel();
		labelSelectNoOfTurns.setText("Select Number of Turns");
		labelSelectNoOfTurns.setBounds(70, 300, 150, 30);
		add(labelSelectNoOfTurns);

		comboBoxNumberOfTurns = new JComboBox<>(noOfTurnsList);
		comboBoxNumberOfTurns.setBounds(300, 300, 100, 30);
		add(comboBoxNumberOfTurns);

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
			dfsUsingStackContinent(continentModel, continentModel.getCountriesList().get(1));
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
	private void dfsUsingStackContinent(ContinentModel continentModel, CountryModel countryModel) {
		Stack<CountryModel> stack = new Stack<CountryModel>();
		stack.add(countryModel);
		countryModel.setVisited(true);
		while (!stack.isEmpty()) {
			CountryModel element = stack.pop();
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

		if (event.getSource() == comboBoxNumberOfMaps) {
			String mapSelection = (String) comboBoxNumberOfMaps.getSelectedItem();
			noOfMaps = Integer.valueOf(mapSelection);
			showMapOptionAsPerSelection(mapSelection);
		}
		if (event.getSource() == comboBoxSelectNoOfPlayer) {
			System.out.println("Playes : " + (String) comboBoxSelectNoOfPlayer.getSelectedItem());
			showPlayerOptionAsPerNoOfPlayers((String) comboBoxSelectNoOfPlayer.getSelectedItem());
		}
		if ((event.getSource() == chooseMapOne)) {
			mapFilePath[0] = Utility.pickFile();
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = mapFilePath[0].split(pattern);
			mapFileName[0] = splitFilePath[splitFilePath.length - 1];
		}
		if ((event.getSource() == chooseMapTwo)) {
			mapFilePath[1] = Utility.pickFile();
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = mapFilePath[1].split(pattern);
			mapFileName[1] = splitFilePath[splitFilePath.length - 1];
		}
		if ((event.getSource() == chooseMapThree)) {
			mapFilePath[2] = Utility.pickFile();
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = mapFilePath[2].split(pattern);
			mapFileName[2] = splitFilePath[splitFilePath.length - 1];
		}
		if ((event.getSource() == chooseMapFour)) {
			mapFilePath[3] = Utility.pickFile();
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = mapFilePath[3].split(pattern);
			mapFileName[3] = splitFilePath[splitFilePath.length - 1];
		}
		if ((event.getSource() == chooseMapFive)) {
			mapFilePath[4] = Utility.pickFile();
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = mapFilePath[4].split(pattern);
			mapFileName[4] = splitFilePath[splitFilePath.length - 1];
		}
		if ((event.getSource() == startTournament)) {
			for (int i = 0; i < noOfMaps; i++) {
				MapHierarchyModel mapHierarchyModel = checkMapIsValid(mapFileName[i], mapFilePath[i]);
				if (mapHierarchyModel != null)
					mapHierarchyModels[i] = mapHierarchyModel;
				else {
					System.out.println("Unable to create Map Hierarchy Model");
					return;
				}

			}
			int noOfPlayers = Integer.parseInt((String) comboBoxSelectNoOfPlayer.getSelectedItem());
			int noOfGames = Integer.parseInt((String) comboBoxNumberOfGames.getSelectedItem());
			int noOfTurns = Integer.parseInt((String) comboBoxNumberOfTurns.getSelectedItem());
			String[] comboSelectedPlayers = new String[noOfPlayers];
			if (noOfPlayers >= 2) {
				comboSelectedPlayers[0] = (String) comboBoxPlayer1.getSelectedItem();
				comboSelectedPlayers[1] = (String) comboBoxPlayer2.getSelectedItem();
			}
			if (noOfPlayers >= 3) {
				comboSelectedPlayers[2] = (String) comboBoxPlayer3.getSelectedItem();
			}
			if (noOfPlayers == 4) {
				comboSelectedPlayers[3] = (String) comboBoxPlayer4.getSelectedItem();
			}
			noOfGamesToBePlayed = noOfMaps * noOfGames;
			gameNumber = 0;
			for (int i = 0; i < noOfMaps; i++) {
				MapHierarchyModel mapHierarchyModel = mapHierarchyModels[i];
				System.out.println("Map Name for Results : " + mapHierarchyModel.getConquestMapName());
				results.put(mapHierarchyModel.getConquestMapName(), new ArrayList<String>());
			}
			for (int j = 0; j < noOfMaps; j++) {
				MapHierarchyModel mapHierarchyModel = mapHierarchyModels[j];
				for (int i = 0; i < noOfGames; i++) {

					PlayerModel[] playerModels = initializingPlayerModels(noOfPlayers, mapHierarchyModel,
							comboSelectedPlayers);
					System.out.println("Map Name: " + mapHierarchyModel.getConquestMapName());
					GameModel gameModel = new GameModel(mapHierarchyModel, playerModels);
					gameModel.setMaxTurnsAllowed(noOfTurns);
					gameModel.addObserver(NewTournamentMenuScreen.this);
					gameModels.add(gameModel);
				}
			}

			tournamentObj = new TournamentController(noOfMaps, noOfGames, noOfTurns);
			tournamentObj.startTournament(gameModels.get(gameNumber));
		}
	}

	/**
	 * @param selectedItem
	 */
	private void showPlayerOptionAsPerNoOfPlayers(String selectedItem) {
		// TODO Auto-generated method stub
		if (selectedItem.equalsIgnoreCase("2")) {
			comboBoxPlayer1.setVisible(true);
			comboBoxPlayer2.setVisible(true);
			comboBoxPlayer3.setVisible(false);
			comboBoxPlayer4.setVisible(false);
		}
		if (selectedItem.equalsIgnoreCase("3")) {
			comboBoxPlayer1.setVisible(true);
			comboBoxPlayer2.setVisible(true);
			comboBoxPlayer3.setVisible(true);
			comboBoxPlayer4.setVisible(false);
		}
		if (selectedItem.equalsIgnoreCase("4")) {
			comboBoxPlayer1.setVisible(true);
			comboBoxPlayer2.setVisible(true);
			comboBoxPlayer3.setVisible(true);
			comboBoxPlayer4.setVisible(true);
		}
	}

	/**
	 * @param selectedItem
	 */
	private void showMapOptionAsPerSelection(String selectedItem) {
		// TODO Auto-generated method stub
		if (selectedItem.equalsIgnoreCase("1")) {
			chooseMapOne.setVisible(true);
			chooseMapTwo.setVisible(false);
			chooseMapThree.setVisible(false);
			chooseMapFour.setVisible(false);
			chooseMapFive.setVisible(false);
		}
		if (selectedItem.equalsIgnoreCase("2")) {
			chooseMapOne.setVisible(true);
			chooseMapTwo.setVisible(true);
			chooseMapThree.setVisible(false);
			chooseMapFour.setVisible(false);
			chooseMapFive.setVisible(false);
		}
		if (selectedItem.equalsIgnoreCase("3")) {
			chooseMapOne.setVisible(true);
			chooseMapTwo.setVisible(true);
			chooseMapThree.setVisible(true);
			chooseMapFour.setVisible(false);
			chooseMapFive.setVisible(false);
		}
		if (selectedItem.equalsIgnoreCase("4")) {
			chooseMapOne.setVisible(true);
			chooseMapTwo.setVisible(true);
			chooseMapThree.setVisible(true);
			chooseMapFour.setVisible(true);
			chooseMapFive.setVisible(false);
		}
		if (selectedItem.equalsIgnoreCase("5")) {
			chooseMapOne.setVisible(true);
			chooseMapTwo.setVisible(true);
			chooseMapThree.setVisible(true);
			chooseMapFour.setVisible(true);
			chooseMapFive.setVisible(true);
		}
	}

	public PlayerModel[] initializingPlayerModels(int noOfPlayers, MapHierarchyModel mapHierarchyModel,
			String[] comboSelectedPlayers) {
		PlayerModel[] players = new PlayerModel[noOfPlayers];
		ContinentModel[] continents = new ContinentModel[mapHierarchyModel.getContinentsList().size()];
		PlayerType[] playerTypes = Utility.getPlayerTypeFromDropDown(noOfPlayers, comboSelectedPlayers);
		for (int j = 0; j < noOfPlayers; j++) {
			int value = j + 1;
			players[j] = new PlayerModel("Player" + String.valueOf(value), playerTypes[j]);
			if (playerTypes[j] == PlayerType.Aggresive)
				players[j].setStrategy(new AggresivePlayer());
			else if (playerTypes[j] == PlayerType.Benevolent)
				players[j].setStrategy(new BenevolentPlayer());
			else if (playerTypes[j] == PlayerType.Random)
				players[j].setStrategy(new RandomPlayer());
			else if (playerTypes[j] == PlayerType.Cheater)
				players[j].setStrategy(new CheaterPlayer());
			switch (noOfPlayers) {
			case (2):
				players[j].noOfArmyInPlayer(25);
				break;

			case (3):
				players[j].noOfArmyInPlayer(20);
				break;
			case (4):
				players[j].noOfArmyInPlayer(15);
				break;

			}

		}
		/*
		 * randomly placing army of each player in different country by round robin
		 */
		int pickedNumber = 0;
		Random rand = new Random();
		List<CountryModel> countryModelList = new ArrayList<>();
		List<ContinentModel> continentModelList = new ArrayList<>();
		continentModelList.addAll(mapHierarchyModel.getContinentsList());
		countryModelList.addAll(mapHierarchyModel.getCountryList());
		while (!(countryModelList.isEmpty())) {
			for (int count1 = 0; count1 < noOfPlayers; count1++) {
				if (!(countryModelList.isEmpty())) {
					pickedNumber = rand.nextInt(countryModelList.size());
					CountryModel countryModelTest = countryModelList.get(pickedNumber);
					if (countryModelTest != null) {
						players[count1].addCountry(countryModelTest);
						countryModelTest.setOwner(players[count1]);
						countryModelTest.addNoOfArmiesCountry();
						players[count1].reduceArmyInPlayer();
					}
					System.out.println(countryModelList.get(pickedNumber).getCountryName());
					countryModelList.remove(pickedNumber);
				}
			}
		}
		return players;
	}

	private MapHierarchyModel checkMapIsValid(String fileName, String filePath) {
		MapHierarchyModel mapHierarchyModel = null;

		if (fileName == null || fileName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Select Map to continue.", "Error Message", JOptionPane.ERROR_MESSAGE);
			return mapHierarchyModel;
		} else {
			Utility utility = new Utility();
			mapHierarchyModel = utility.parseAndValidateMap(filePath);
			mapHierarchyModel.setConquestMapName(fileName);
			if (!mapHierarchyModel.isValErrorFlag()) {
				boolean isConnected = isMapConnected(mapHierarchyModel);
				System.out.println("Map Is Connected: " + isConnected);
			}
			if (!mapHierarchyModel.isValErrorFlag()) {
				boolean isConnected = isContinentConnected(mapHierarchyModel);
				System.out.println("Continent Is Connected: " + isConnected);
			}
			// Initializing player Models and Redirecting to Game Window.
			if (!mapHierarchyModel.isValErrorFlag()) {
				return mapHierarchyModel;
			} else {
				String valErrorMsg = mapHierarchyModel.getErrorMsg();
				JOptionPane.showMessageDialog(this, valErrorMsg, "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		return mapHierarchyModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable object, Object arg) {
		// TODO Auto-generated method stub
		GameModel gameModel = (GameModel) object;
		System.out.println(
				gameModel.getCurrPlayer().getPlayerName() + ": : " + gameModel.getCurrPlayer().getPlayerType());

		if (gameModel.getIsWon() || gameModel.getMaxTurnsAllowed() == 0) {
			System.out.println("Game WOn");
			ArrayList<String> mapResults = results.get(gameModel.getMapHierarchyModel().getConquestMapName());
			if (gameModel.getIsWon()) {
				System.out.println("Game WOn" + gameModel.getCurrPlayer().getPlayerType().toString());
				mapResults.add(gameModel.getCurrPlayer().getPlayerType().toString());
			} else {
				System.out.println("Game Draw");

				mapResults.add("Draw");
			}
			results.put(gameModel.getMapHierarchyModel().getConquestMapName(), mapResults);
			gameNumber = gameNumber + 1;

			if (gameNumber >= noOfGamesToBePlayed) {
				System.out.println("Results: " + results);
			} else
				tournamentObj.startTournament(gameModels.get(gameNumber));
		} else if (gameModel.getGamePhaseStage() == 1) {

			if (gameModel.getCurrPlayer() != null)
				gameModel.getCurrPlayer().reinforcementPhase(gameModel);
			else {
				System.out.println("CurrentPlayer is Null");
				System.out.println("Game Draw");

			}

		}

	}

	/**
	 * @return the results
	 */
	public HashMap<String, ArrayList<String>> getResults() {
		return results;
	}
}
