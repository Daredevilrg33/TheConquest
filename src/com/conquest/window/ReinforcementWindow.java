package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.conquest.controller.ReinfrocementWindowController;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.utilities.Constants;

public class ReinforcementWindow extends JFrame implements ActionListener {

	private ReinfrocementWindowController reinforcementWindowController;
	private JComboBox<String> jComboBoxSourceCountries;
	private JComboBox<String> jComboBoxDestinationCountries;
	private JComboBox<String> jComboBoxChosenNoOfArmies;
	private JButton jButtonSend;
	private String selectedSourceCountry;
	private int armies;
	private String selectedDestinationCountry;
	private JLabel jSourceArmyLabel;
	private JLabel jDestinationArmyLabel;
	private JLabel jPlayerLabel;
	private JLabel jSourceCountryLabel;
	private JLabel jDestinationCountryLabel;
	private JLabel jChosenNoOfArmies;
	private PlayerModel[] players;

	public ReinforcementWindow(MapHierarchyModel mapModel, PlayerModel[] players) {
		this.players = players;
		setTitle("Fortification Window");
		setResizable(false);
		setSize(Constants.MAP_EDITOR_WIDTH, Constants.MAP_EDITOR_HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		jPlayerLabel = new JLabel();
		jPlayerLabel.setBounds(50, 50, 100, 30);
		add(jPlayerLabel);

		jComboBoxSourceCountries = new JComboBox<>();
		jComboBoxSourceCountries.setBounds(170, 50, 100, 30);

		add(jComboBoxSourceCountries);

		jComboBoxDestinationCountries = new JComboBox<>();
		jComboBoxDestinationCountries.setBounds(300, 50, 100, 30);
		add(jComboBoxDestinationCountries);

		jButtonSend = new JButton("Send");
		jButtonSend.setBounds(600, 50, 100, 30);
		jButtonSend.addActionListener(this);
		add(jButtonSend);

		jComboBoxChosenNoOfArmies = new JComboBox<>();
		jComboBoxChosenNoOfArmies.setBounds(450, 50, 100, 30);
		add(jComboBoxChosenNoOfArmies);

		jChosenNoOfArmies = new JLabel();
		jChosenNoOfArmies.setBounds(450, 20, 200, 30);
		jChosenNoOfArmies.setText("Select no. of Armies");
		add(jChosenNoOfArmies);

		jSourceCountryLabel = new JLabel();
		jSourceCountryLabel.setBounds(170, 20, 200, 30);
		jSourceCountryLabel.setText("Source Country");
		add(jSourceCountryLabel);

		jDestinationCountryLabel = new JLabel();
		jDestinationCountryLabel.setBounds(300, 20, 200, 30);
		jDestinationCountryLabel.setText("Destination Country");
		add(jDestinationCountryLabel);

		jSourceArmyLabel = new JLabel();
		jSourceArmyLabel.setBounds(217, 78, 200, 30);
		add(jSourceArmyLabel);

		jDestinationArmyLabel = new JLabel();
		jDestinationArmyLabel.setBounds(337, 78, 200, 30);
		add(jDestinationArmyLabel);

		reinforcementWindowController = new ReinfrocementWindowController(players, this, players.length, mapModel);

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
				MainMenuScreen initialScreen = new MainMenuScreen();
				initialScreen.setVisible(true);
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		jComboBoxSourceCountries.addActionListener(this);
		jComboBoxDestinationCountries.addActionListener(this);

	}

	public void updateArmy(int number) {

		jComboBoxChosenNoOfArmies.removeAllItems();
		for (int i = 1; i <= number; i++) {
			jComboBoxChosenNoOfArmies.addItem(Integer.toString(i));
		}

	}

	public void updatePlayerLabel(String playerName) {
//		System.out.println("updatePlayerLabel Value: "+  playerName);
		jPlayerLabel.setText(playerName);
	}

	public void updateSourceArmyLabel(int sourceArmy) {
//		System.out.println("updatePlayerLabel Value: "+  playerName);
		jSourceArmyLabel.setText(Integer.toString(sourceArmy));
//		jDestinationArmyLabel.setText(Integer.toString(DestinationArmy));

	}

	public void newArmyLabel() {
		jSourceArmyLabel.setText("");
		jDestinationArmyLabel.setText("");
	}

	public void updateDestinationArmyLabel(int destinationArmy) {
//		System.out.println("updatePlayerLabel Value: "+  playerName);
		jDestinationArmyLabel.setText(Integer.toString(destinationArmy));
//		jDestinationArmyLabel.setText(Integer.toString(DestinationArmy));

	}

	public void updateComboBoxSourceCountries(List<CountryModel> countryModels) {

		jComboBoxSourceCountries.removeAllItems();
		jComboBoxSourceCountries.addItem("Select country:");
		for (CountryModel countryModel : countryModels) {
//			System.out.println("updateComboBoxCountries Value: "+  countryModel.getCountryName());

			jComboBoxSourceCountries.addItem(countryModel.getCountryName());
		}

	}

	public void updateComboBoxDestinationCountries(ArrayList<String> arrayList) {

		jComboBoxDestinationCountries.removeAllItems();
		jComboBoxDestinationCountries.addItem("Select country:");

		for (int i = arrayList.size() - 1; i >= 0; i--) {
//			System.out.println("updateComboBoxCountries Value: "+  countryModel.getCountryName());

			jComboBoxDestinationCountries.addItem(arrayList.get(i));
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == jButtonSend) {
			System.out.println("Selected Player Name: " + jPlayerLabel.getText().toString());
			System.out
					.println("Selected Source Country Name: " + jComboBoxSourceCountries.getSelectedItem().toString());
//			System.out.println("Selected Destination Country Name: " + jComboBoxDestinationCountries.getSelectedItem().toString());

			if (jComboBoxSourceCountries.getSelectedItem().toString().equalsIgnoreCase("Select Country:")) {

				JOptionPane.showMessageDialog(this, "Select Country to continue.", "Error Message",
						JOptionPane.ERROR_MESSAGE);

			} else {

				armies = Integer.valueOf(jComboBoxChosenNoOfArmies.getSelectedItem().toString());
				reinforcementWindowController.sending(armies);
//			reinforcementWindowController.updateSourceUI();
//			reinforcementWindowController.updatePlayer();
//			reinforcementWindowController.updateUIInfo();	   
			}

		} else if (e.getSource() == jComboBoxSourceCountries) {
			System.out.println("JCombo Source COuntry selected");
			if (jComboBoxSourceCountries.getItemCount() != 0) {
				selectedSourceCountry = jComboBoxSourceCountries.getSelectedItem().toString();
//			selectedDestinationCountry = jComboBoxDestinationCountries.getSelectedItem().toString();
				reinforcementWindowController.finding(selectedSourceCountry);
				reinforcementWindowController.updateUIInfo();
			}
		} else if (e.getSource() == jComboBoxDestinationCountries) {
			if (jComboBoxDestinationCountries.getItemCount() != 0) {
				selectedDestinationCountry = jComboBoxDestinationCountries.getSelectedItem().toString();
			} else {
				selectedDestinationCountry = "";
			}
			reinforcementWindowController.updateDestinationUI(selectedDestinationCountry);

		}

	}

}