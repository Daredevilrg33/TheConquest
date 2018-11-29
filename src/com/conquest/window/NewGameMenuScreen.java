package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.model.AggresivePlayer;
import com.conquest.model.BenevolentPlayer;
import com.conquest.model.CheaterPlayer;
import com.conquest.model.GameModel;
import com.conquest.model.HumanPlayer;
import com.conquest.model.PlayerModel;
import com.conquest.model.PlayerType;
import com.conquest.model.RandomPlayer;
import com.conquest.utilities.Constants;
import com.conquest.utilities.Utility;

/**
 * The Class NewGameMenuScreen.
 *
 * @author Rohit Gupta
 */
public class NewGameMenuScreen extends JFrame implements ActionListener {

	/** The button select file. */
	private JButton buttonSelectFile;

	/** The file path. */
	private String filePath;

	/** The file name. */
	private String fileName;

	/** The text field map. */
	private JTextField textFieldMap;

	/** The players list. */
	private String[] playersList = new String[] { "3", "4", "5" };

	/** The button start game. */
	private JButton buttonStartGame;

	/** The combo box select player. */
	private JComboBox<String> comboBoxSelectPlayer;

	/** The no of players. */
	private String noOfPlayers;

	/** The player types. */
	private String[] playerTypes = new String[] { "Human", "Aggresive", "Benevolent", "Random", "Cheater" };

	/** The label player 4. */
	private JLabel labelPlayer5, labelPlayer4;

	/** The combo box player type 5. */
	private JComboBox<String> comboBoxPlayerType1, comboBoxPlayerType2, comboBoxPlayerType3, comboBoxPlayerType4,
			comboBoxPlayerType5;

	/**
	 * NewGameMenuScreen Constructor Instantiates a new new game menu screen.
	 */
	public NewGameMenuScreen() {

		JLabel labelSelectMap = new JLabel();
		labelSelectMap.setText("Select Map");
		labelSelectMap.setBounds(Constants.WIDTH / 2 - 150, 50, 100, 30);
		add(labelSelectMap);

		textFieldMap = new JTextField();
		textFieldMap.setBounds(Constants.WIDTH / 2 - 30, 50, 100, 30);
		add(textFieldMap);

		buttonSelectFile = new JButton();
		buttonSelectFile.setText("Select File");
		buttonSelectFile.setBounds(Constants.WIDTH / 2 + 90, 50, 100, 30);
		buttonSelectFile.addActionListener(this);
		add(buttonSelectFile);

		JLabel labelSelectPlayer = new JLabel();
		labelSelectPlayer.setText("Select Players");
		labelSelectPlayer.setBounds(Constants.WIDTH / 2 - 150, 100, 100, 30);
		add(labelSelectPlayer);

		comboBoxSelectPlayer = new JComboBox<>(playersList);
		comboBoxSelectPlayer.setBounds(Constants.WIDTH / 2 - 30, 100, 100, 30);
		comboBoxSelectPlayer.addActionListener(this);
		add(comboBoxSelectPlayer);

		JLabel labelPlayer1 = new JLabel("Player1 : ");
		labelPlayer1.setBounds(Constants.WIDTH / 2 - 250, 150, 100, 30);
		add(labelPlayer1);

		JLabel labelPlayer2 = new JLabel("Player2 : ");
		labelPlayer2.setBounds(Constants.WIDTH / 2 - 250, 200, 100, 30);
		add(labelPlayer2);

		JLabel labelPlayer3 = new JLabel("Player3 : ");
		labelPlayer3.setBounds(Constants.WIDTH / 2 - 250, 250, 100, 30);
		add(labelPlayer3);

		labelPlayer4 = new JLabel("Player4 : ");
		labelPlayer4.setBounds(Constants.WIDTH / 2 - 250, 300, 100, 30);
		add(labelPlayer4);
		labelPlayer4.setVisible(false);

		labelPlayer5 = new JLabel("Player5 : ");
		labelPlayer5.setBounds(Constants.WIDTH / 2 - 250, 350, 100, 30);
		add(labelPlayer5);
		labelPlayer5.setVisible(false);

		comboBoxPlayerType1 = new JComboBox<>(playerTypes);
		comboBoxPlayerType1.setBounds(Constants.WIDTH / 2 - 120, 150, 100, 30);
		add(comboBoxPlayerType1);

		comboBoxPlayerType2 = new JComboBox<>(playerTypes);
		comboBoxPlayerType2.setBounds(Constants.WIDTH / 2 - 120, 200, 100, 30);
		add(comboBoxPlayerType2);

		comboBoxPlayerType3 = new JComboBox<>(playerTypes);
		comboBoxPlayerType3.setBounds(Constants.WIDTH / 2 - 120, 250, 100, 30);
		add(comboBoxPlayerType3);

		comboBoxPlayerType4 = new JComboBox<>(playerTypes);
		comboBoxPlayerType4.setBounds(Constants.WIDTH / 2 - 120, 300, 100, 30);
		add(comboBoxPlayerType4);
		comboBoxPlayerType4.setVisible(false);

		comboBoxPlayerType5 = new JComboBox<>(playerTypes);
		comboBoxPlayerType5.setBounds(Constants.WIDTH / 2 - 120, 350, 100, 30);
		add(comboBoxPlayerType5);
		comboBoxPlayerType5.setVisible(false);

		buttonStartGame = new JButton("Start Game");
		buttonStartGame.setBounds(Constants.WIDTH / 2 + 150, Constants.HEIGHT - 200, 100, 30);
		buttonStartGame.addActionListener(this);
		add(buttonStartGame);
		setTitle("New Game Menu");
		setResizable(false);
		setSize(Constants.WIDTH, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				MainMenuScreen mainMenuScreen = new MainMenuScreen();
				mainMenuScreen.setVisible(true);
				dispose();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == buttonSelectFile) {
			filePath = Utility.pickFile();
			System.out.println("File Path: " + filePath);
			String pattern = Pattern.quote(System.getProperty("file.separator"));
			String[] splitFilePath = filePath.split(pattern);
			fileName = splitFilePath[splitFilePath.length - 1];
			System.out.println("File Name: " + fileName);
			textFieldMap.setText(fileName);
		} else if (event.getSource() == buttonStartGame) {
			noOfPlayers = (String) comboBoxSelectPlayer.getSelectedItem();
			if (fileName == null || filePath == null || fileName.isEmpty() || filePath.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Select Map to continue.", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			} else {

				Utility utility = new Utility();
				MapHierarchyModel mapHierarchyModel = utility.parseAndValidateMap(filePath);
				checkValidation(mapHierarchyModel, Integer.valueOf(noOfPlayers));
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
					String[] comboSelectedPlayers = new String[Integer.parseInt(noOfPlayers)];
					if (Integer.parseInt(noOfPlayers) >= 3) {
						comboSelectedPlayers[0] = (String) comboBoxPlayerType1.getSelectedItem();
						comboSelectedPlayers[1] = (String) comboBoxPlayerType2.getSelectedItem();
						comboSelectedPlayers[2] = (String) comboBoxPlayerType3.getSelectedItem();
					}
					if (Integer.parseInt(noOfPlayers) >= 4) {
						comboSelectedPlayers[3] = (String) comboBoxPlayerType4.getSelectedItem();
					}
					if (Integer.parseInt(noOfPlayers) == 5) {
						comboSelectedPlayers[4] = (String) comboBoxPlayerType5.getSelectedItem();
					}
					PlayerModel[] playerModels = initializingPlayerModels(Integer.parseInt(noOfPlayers),
							mapHierarchyModel, comboSelectedPlayers);
					redirectingToGameWindow(mapHierarchyModel, playerModels);
				} else {
					String valErrorMsg = mapHierarchyModel.getErrorMsg();
					JOptionPane.showMessageDialog(this, valErrorMsg, "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (event.getSource() == comboBoxSelectPlayer) {
			String item = (String) comboBoxSelectPlayer.getSelectedItem();
			if (item.trim().equalsIgnoreCase("4")) {
				comboBoxPlayerType4.setVisible(true);
				labelPlayer4.setVisible(true);
			} else if (item.trim().equalsIgnoreCase("5")) {
				comboBoxPlayerType4.setVisible(true);
				labelPlayer4.setVisible(true);
				comboBoxPlayerType5.setVisible(true);
				labelPlayer5.setVisible(true);
			} else if (item.trim().equalsIgnoreCase("3")) {
				comboBoxPlayerType4.setVisible(false);
				comboBoxPlayerType5.setVisible(false);
				labelPlayer4.setVisible(false);
				labelPlayer5.setVisible(false);
			}
		}
	}

	/**
	 * initializingPlayerModels method Void method to initialize player models as
	 * per number of players.
	 *
	 * @param noOfPlayers          Input the number of players in game type integer
	 * @param mapHierarchyModel    MapHierarchyModel{@link MapHierarchyModel} object
	 *                             to pass map model
	 * @param comboSelectedPlayers the combo selected players
	 * @return the player model[]
	 */
	public PlayerModel[] initializingPlayerModels(int noOfPlayers, MapHierarchyModel mapHierarchyModel,
			String[] comboSelectedPlayers) {

		PlayerModel[] players = new PlayerModel[noOfPlayers];

		ContinentModel[] continents = new ContinentModel[mapHierarchyModel.getContinentsList().size()];
		PlayerType[] playerTypes = Utility.getPlayerTypeFromDropDown(noOfPlayers, comboSelectedPlayers);
		for (int j = 0; j < noOfPlayers; j++) {
			int value = j + 1;
			players[j] = new PlayerModel("Player" + String.valueOf(value), playerTypes[j]);

			if (playerTypes[j] == PlayerType.Human)
				players[j].setStrategy(new HumanPlayer());
			else if (playerTypes[j] == PlayerType.Aggresive)
				players[j].setStrategy(new AggresivePlayer());
			else if (playerTypes[j] == PlayerType.Benevolent)
				players[j].setStrategy(new BenevolentPlayer());
			else if (playerTypes[j] == PlayerType.Random)
				players[j].setStrategy(new RandomPlayer());
			else if (playerTypes[j] == PlayerType.Cheater)
				players[j].setStrategy(new CheaterPlayer());
			switch (noOfPlayers) {
			case (3):
				players[j].noOfArmyInPlayer(25);
				break;

			case (4):
				players[j].noOfArmyInPlayer(20);
				break;

			case (5):
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

	/**
	 * Redirecting to game window.
	 *
	 * @param mapHierarchyModel the map hierarchy model
	 * @param playerModels      the player models
	 */
	private void redirectingToGameWindow(MapHierarchyModel mapHierarchyModel, PlayerModel[] playerModels) {
		// TODO Auto-generated method stub
		dispose();
		GameModel gameModel = new GameModel(mapHierarchyModel, playerModels);
		GameWindow gameWindow = new GameWindow(gameModel);
		gameWindow.setVisible(true);
	}

	/**
	 * Check validation of Map.
	 *
	 * @param mapHierarchyModel the map hierarchy model
	 * @param noOfPlayers       the no of players
	 * @return the string
	 */
	public String checkValidation(MapHierarchyModel mapHierarchyModel, int noOfPlayers) {
		String mes = "";
		if (mapHierarchyModel.getTotalCountries() < noOfPlayers && !mapHierarchyModel.isValErrorFlag()) {
			mapHierarchyModel.setValErrorFlag(true);
			mapHierarchyModel.setErrorMsg("Number of countries cannot be less than number of players");

		} else {
			mes = "Passed";
		}
		return mes;
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
	 * Depth first search using stack.
	 *
	 * @param mapHierarchyModel the map hierarchy model
	 * @param countryModel      the country model
	 */
	private void dfsUsingStack(MapHierarchyModel mapHierarchyModel, CountryModel countryModel) {
		Stack<CountryModel> stack = new Stack<CountryModel>();
		stack.add(countryModel);
		countryModel.setVisited(true);
		while (!stack.isEmpty()) {
			CountryModel element = stack.pop();
//			System.out.println("DFS CountryName: " + element.getCountryName() + " ");
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
//			System.out.println("DFS CountryName: " + element.getCountryName() + " ");
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
}
