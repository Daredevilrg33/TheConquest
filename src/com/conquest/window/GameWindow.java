package com.conquest.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.conquest.controller.GameWindowController;
import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.mapeditor.renderer.TableRenderer;
import com.conquest.mapeditor.renderer.TreeRenderer;
import com.conquest.model.GameModel;
import com.conquest.utilities.Constants;

/**
 * The Class GameWindow.
 *
 * @author Rohit Gupta
 */
public class GameWindow extends JFrame implements ActionListener, Observer {

	/** The Constant serialVersionUID. */
	private static final long SERIAL_VERSION_UID = 705136013521611381L;

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

	/** The phaseView scroll pane. */
	private JPanel phaseViewPanel;

	/** The tree view. */
	private TreeRenderer treeView;

	/** The adjacency table. */
	private TableRenderer adjacencyTable;

	/** The label connectivity. */
	private JLabel labelConnectivity;

	/** The label phase. */
	public JLabel labelPhase;

	/** The phase view. */
	private TreeRenderer phaseView;

	/** The phase scroll pane. */
	private JScrollPane phaseScrollPane;

	/** The phase scroll pane. */
	private JPanel progressBarPanel;

	/** The countries column. */
	private String[] countriesColumn;

	/** The vector data. */
	private String[][] vectorData;

	/** The map hierarchy model. */
	private MapHierarchyModel mapHierarchyModel;

	/** The players. */
	private PlayerModel[] players;

	/** The jHandIn button place. */
	private JButton jHandIn;

	/** The label labelCardsWithPlayer button place. */
	private JLabel labelCardsWithPlayer;

	private JProgressBar progressBar;

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

		labelConnectivity = new JLabel("Connectivity Between Countries");
		Dimension size = labelConnectivity.getPreferredSize();
		labelConnectivity.setFont(new Font("dialog", 1, 15));
		labelConnectivity.setBounds(15, 8, size.width + 200, size.height);
		add(labelConnectivity);

		mappingScrollPane = new JScrollPane(adjacencyTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mappingScrollPane.setBounds(15, 55, 800, 350);
		add(mappingScrollPane);

		DefaultMutableTreeNode continentRoot = new DefaultMutableTreeNode("Continent Hierarchy");
		treeView = new TreeRenderer(continentRoot);
		treeScrollPane = new JScrollPane(treeView, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		treeScrollPane.setBounds(mappingScrollPane.getBounds().x + (int) (mappingScrollPane.getBounds().getWidth()), 55,
				300, 350);
		add(treeScrollPane);

		phaseViewPanel = new JPanel();
		phaseViewPanel.setBounds(15, treeScrollPane.getBounds().y + (int) (treeScrollPane.getBounds().getHeight()),
				800 + (int) (treeScrollPane.getBounds().getWidth()), 30);
		add(phaseViewPanel);
		phaseViewPanel.setBackground(Color.lightGray);
		phaseViewPanel.setLayout(new FlowLayout());

		labelPhase = new JLabel("StartUp Phase");
		labelPhase.setFont(new Font("dialog", 1, 15));
		phaseViewPanel.add(labelPhase);

		jPlayerLabel = new JLabel();
		jPlayerLabel.setBounds(50, 620, 100, 30);
		add(jPlayerLabel);

		jComboBoxCountries = new JComboBox<>();
		jComboBoxCountries.setBounds(170, 620, 100, 30);
		add(jComboBoxCountries);

		jButtonPlace = new JButton("Place Initial Armies");
		jButtonPlace.setBounds(310, 620, 150, 30);
		jButtonPlace.addActionListener(this);
		add(jButtonPlace);

		jPlayerArmies = new JLabel();
		jPlayerArmies.setBounds(480, 620, 200, 30);
		add(jPlayerArmies);

		jHandIn = new JButton("HandIn the cards");
		jHandIn.setBounds(690, 620, 200, 30);
		jHandIn.setVisible(false);
		jHandIn.setEnabled(false);
		jHandIn.setVisible(false);
		jHandIn.addActionListener(this);
		add(jHandIn);

		labelCardsWithPlayer = new JLabel();
		labelCardsWithPlayer.setBounds(480, 660, 200, 30);
		add(labelCardsWithPlayer);

		gameWindowController = new GameWindowController(this, Integer.parseInt(noOfPlayers), mapHierarchyModel);
		players = gameWindowController.getPlayers();
		gameWindowController.getGameModel().addObserver(this);
		phaseScrollPane = new JScrollPane(phaseView, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		phaseScrollPane.setBounds(15, phaseViewPanel.getBounds().y + (int) (phaseViewPanel.getBounds().getHeight()),
				700, 150);
		add(phaseScrollPane);

		progressBarPanel = new JPanel();
		progressBarPanel.setBounds(phaseScrollPane.getBounds().x + (int) (phaseScrollPane.getBounds().getWidth()),
				phaseViewPanel.getBounds().y + (int) (phaseViewPanel.getBounds().getHeight()),
				(int) (treeScrollPane.getBounds().getWidth()), 150);
		add(progressBarPanel);
		addProgressBar(gameWindowController.getGameModel());

		addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event. WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				NewGameMenuScreen newGameMenuScreen = new NewGameMenuScreen();
				newGameMenuScreen.setVisible(true);
				dispose();
			}
		});

		if (this.mapHierarchyModel.getContinentsList().size() > 0) {
			updateHierarchyTree();
			updatePaintMatrix();
			updateGameInformation();
		}
		updateUIInfo(players[0]);
		gameWindowController.getGameModel().increaseTurn();
	}

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public PlayerModel[] getPlayers() {
		return players;
	}

	/**
	 * Sets the players.
	 *
	 * @param players the new players
	 */
	public void setPlayers(PlayerModel[] players) {
		this.players = players;
	}

	/**
	 * updateHierarchyTree Method to refresh and update the continent hierarchy tree
	 * on adding new continent or new country.
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
	 * updateHierarchyTree Method to refresh and update the Game Information and
	 * phase view.
	 */

	public void updateGameInformation() {

		DefaultMutableTreeNode playerRoot = new DefaultMutableTreeNode("Game Information");
		for (PlayerModel player : gameWindowController.getPlayers()) {
			List<CountryModel> loopCountriesList = player.getPlayerCountryList();
			DefaultMutableTreeNode playerNode = new DefaultMutableTreeNode(player.getPlayerName());
			for (CountryModel loopCountry : loopCountriesList) {
				playerNode.add(new DefaultMutableTreeNode(
						loopCountry.getCountryName() + " ( " + loopCountry.getNoOfArmiesCountry() + " ) armies"));
			}
			playerRoot.add(playerNode);
		}

		phaseView = new TreeRenderer(playerRoot);
		phaseView.setShowsRootHandles(true);
		phaseScrollPane.getViewport().removeAll();
		phaseView.expandAll(new TreePath(playerRoot), 1);
		phaseScrollPane.getViewport().add(phaseView);

	}

	/**
	 * updatePaintMatrix Method Method to refresh and paint the adjacency table
	 * matrix of connections between the countries.
	 */

	public void updatePaintMatrix() {
		int numberOfCountries = mapHierarchyModel.getCountryList().size();
		DefaultTableModel tableMatrix = new DefaultTableModel(numberOfCountries, numberOfCountries) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		vectorData = new String[numberOfCountries][numberOfCountries + 1];
		countriesColumn = new String[numberOfCountries + 1];

		int columnCounter = 0, rowCounter = 0;
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

		mappingScrollPane.getViewport().removeAll();
		mappingScrollPane.getViewport().add(adjacencyTable);

		for (int i = 0; i < vectorData.length; i++) {
			for (int j = 1; j < vectorData[i].length; j++) {
				String neighbourCountryName = countriesColumn[j], sourceCountryName = vectorData[i][0];
				for (CountryModel countryModel : mapHierarchyModel.getCountryList()) {
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

	/**
	 * updatePhaseView Method Update phase View.
	 *
	 * @param phase the phase
	 */
	public void updatePhaseView(String phase) {
		labelPhase.setText(phase);
		if ("Reinforcement Phase".equalsIgnoreCase(phase)) {
			jButtonPlace.setText("Place Reinforce Armies");
			jButtonPlace.setEnabled(true);
			jHandIn.setVisible(true);
			if (gameWindowController.getGameModel().getCurrPlayer().canHandIn())
				jHandIn.setEnabled(true);

			if (gameWindowController.getGameModel().getGameState() != 1) {
				labelCardsWithPlayer.setText(gameWindowController.getGameModel().getCurrPlayer().cardsString());
				gameWindowController.getGameModel().getCurrPlayer().gamePhase(this);
			}
			updateGameInformation();
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
		case "Place Initial Armies":
			selectedCountry = jComboBoxCountries.getSelectedItem().toString();
			gameWindowController.placingInitialArmies(selectedCountry,
					gameWindowController.getGameModel().getCurrPlayer());
			updateGameInformation();
			gameWindowController.getGameModel().increaseTurn();
			gameWindowController.getGameModel().moveToNextPlayer();
			if (players[players.length - 1].getnoOfArmyInPlayer() == 0) {
				updatePhaseView("Reinforcement Phase");
			}

			break;
		case "Place Reinforce Armies":
			selectedCountry = jComboBoxCountries.getSelectedItem().toString();
			gameWindowController.placeReinforcedArmy(selectedCountry,
					gameWindowController.getGameModel().getCurrPlayer());
			updateGameInformation();
			break;
		case "HandIn the cards":
			gameWindowController.getGameModel().getCurrPlayer().handInCards();
			break;

		default:
			break;
		}
	}

	/**
	 * Adds the progress bar.
	 * 
	 * @param gameModel passing of game model to update progress bar
	 */
	public void addProgressBar(GameModel gameModel) {

		progressBarPanel.removeAll();
		Color color1 = new Color(23, 54, 135);
		Color color2 = new Color(32, 198, 42);
		Color color3 = new Color(88, 43, 97);
		Color color4 = new Color(67, 89, 67);
		Color color5 = new Color(11, 78, 80);

		PlayerModel[] players = gameModel.getPlayers();
		for (int i = 0; i < players.length; i++) {
			progressBar = new JProgressBar();
			setProgressBarValues(players[i]);
			progressBar.setStringPainted(true);
			if (i == 0)
				progressBar.setForeground(color1);
			if (i == 1)
				progressBar.setForeground(color2);
			if (i == 2)
				progressBar.setForeground(color3);
			if (i == 3)
				progressBar.setForeground(color4);
			if (i == 4)
				progressBar.setForeground(color5);

			Border border = BorderFactory.createTitledBorder(players[i].getPlayerName());
			progressBar.setBorder(border);
			progressBarPanel.add(progressBar, BorderLayout.NORTH);
		}
	}

	public void setProgressBarValues(PlayerModel currentPlayer) {
		if (progressBar != null)
			progressBar.setValue(calculatePercentage(currentPlayer));

	}

	/**
	 * Calculate percentage.
	 *
	 * @param player the player
	 * @return the int
	 */
	public int calculatePercentage(PlayerModel player) {
		double x = ((double) player.getPlayerCountryList().size() / mapHierarchyModel.getTotalCountries()) * 100;
		return (int) x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable object, Object arg) {
		// TODO Auto-generated method stub
		if (object instanceof GameModel) {
			GameModel gameModel = (GameModel) object;
			updateGameInformation();
			addProgressBar(gameModel);
			updateUIInfo(gameModel.getCurrPlayer());
		} else if (object instanceof PlayerModel) {
			PlayerModel playerModel = (PlayerModel) object;

//			setProgressBarValues(playerModel);
//			updateGameInformation();
			updateUIInfo(playerModel);

		}

	}

	/**
	 * updateUIInfo method Void Method to update the window screen after any change
	 * has been made.
	 * 
	 * @param currentPlayer model of current player passed
	 */
	public void updateUIInfo(PlayerModel currentPlayer) {
		updatePlayerLabel(currentPlayer.getPlayerName());
		updatePlayerArmies(currentPlayer.getnoOfArmyInPlayer());
		updateComboBoxCountries(currentPlayer.getPlayerCountryList());
		labelCardsWithPlayer.setText(currentPlayer.cardsString());
		invalidate();
		revalidate();
		if (currentPlayer.canHandIn())
			jHandIn.setEnabled(true);
		else
			jHandIn.setEnabled(false);
	}

}
