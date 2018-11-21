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
import javax.swing.JTextField;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
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
		add(comboBoxSelectPlayer);

		buttonStartGame = new JButton("Start Game");
		buttonStartGame.setBounds(Constants.WIDTH / 2 - 50, Constants.HEIGHT - 200, 100, 30);
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
				MapHierarchyModel mapModel = utility.parseAndValidateMap(filePath);
				checkValidation(mapModel, Integer.valueOf(noOfPlayers));
				mapModel.setConquestMapName(fileName);
				if (!mapModel.isValErrorFlag()) {
					boolean isConnected = isMapConnected(mapModel);
					System.out.println("Map Is Connected: " + isConnected);

				}
				if (!mapModel.isValErrorFlag()) {
					boolean isConnected = isContinentConnected(mapModel);
					System.out.println("Continent Is Connected: " + isConnected);

				}
				if (!mapModel.isValErrorFlag()) {
					dispose();
					GameWindow gameWindow = new GameWindow(mapModel, noOfPlayers);
					gameWindow.setVisible(true);
				} else {
					String valErrorMsg = mapModel.getErrorMsg();
					JOptionPane.showMessageDialog(this, valErrorMsg, "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
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
}
