/**
 * 
 */
package com.conquest.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapHierarchyModel;
import com.conquest.mapeditor.model.PlayerModel;
import com.conquest.utilities.Constants;

/**
 * @author Rohit Gupta
 *
 */
public class AttackPhaseWindow extends JFrame implements ActionListener {
	private JLabel jSourceArmyLabel;
	private JLabel jTargetArmyLabel;
	private JComboBox<String> jComboBoxSourceCountries;
	private JComboBox<String> jComboBoxTargetCountries;
	private JButton jButtonAttack;
	private JButton jButtonAllOutAttack;
	private MapHierarchyModel mapHierarchyModel;

	public AttackPhaseWindow(MapHierarchyModel mapHierarchyModel, PlayerModel playerModel) {
		this.mapHierarchyModel = mapHierarchyModel;
		setTitle("Attack Phase");
		setResizable(false);
		setSize(Constants.MAP_EDITOR_WIDTH, Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);

		JLabel jPlayerLabel = new JLabel(playerModel.getPlayerName());
		jPlayerLabel.setBounds(50, 50, 100, 30);
		add(jPlayerLabel);

		jComboBoxSourceCountries = new JComboBox<>();
		jComboBoxSourceCountries.setBounds(170, 50, 100, 30);

		add(jComboBoxSourceCountries);
		updateComboBoxSourceCountries(playerModel.getPlayerCountryList());
		jComboBoxTargetCountries = new JComboBox<>();
		jComboBoxTargetCountries.setBounds(300, 50, 100, 30);
		add(jComboBoxTargetCountries);

		jButtonAttack = new JButton("Attack");
		jButtonAttack.setBounds(430, 50, 80, 30);
		jButtonAttack.addActionListener(this);
		add(jButtonAttack);

		jButtonAllOutAttack = new JButton("All Out Attack");
		jButtonAllOutAttack.setBounds(540, 50, 100, 30);
		jButtonAllOutAttack.addActionListener(this);
		add(jButtonAllOutAttack);

		JLabel jSourceCountryLabel = new JLabel();
		jSourceCountryLabel.setBounds(170, 20, 200, 30);
		jSourceCountryLabel.setText("Source Country");
		add(jSourceCountryLabel);

		JLabel jTargetCountryLabel = new JLabel();
		jTargetCountryLabel.setBounds(300, 20, 200, 30);
		jTargetCountryLabel.setText("Target Country");
		add(jTargetCountryLabel);

		jSourceArmyLabel = new JLabel();
		jSourceArmyLabel.setBounds(217, 78, 200, 30);
		add(jSourceArmyLabel);

		jTargetArmyLabel = new JLabel();
		jTargetArmyLabel.setBounds(337, 78, 200, 30);
		add(jTargetArmyLabel);
		jComboBoxSourceCountries.addActionListener(this);
	}

	public void updateComboBoxSourceCountries(List<CountryModel> countryModels) {
		jComboBoxSourceCountries.removeAllItems();
		for (CountryModel countryModel : countryModels) {
			jComboBoxSourceCountries.addItem(countryModel.getCountryName());
		}
	}

	public void updateComboBoxTargetCountries(List<CountryModel> countryModels) {
		jComboBoxTargetCountries.removeAllItems();
		jComboBoxTargetCountries.addItem("Select Country");
		for (CountryModel countryModel : countryModels) {
			jComboBoxTargetCountries.addItem(countryModel.getCountryName());
		}
	}

	public void updateTargetArmyLabel(int armyCount) {
		jTargetArmyLabel.setText(String.valueOf(armyCount));
	}

	public void updateSourceArmyLabel(int armyCount) {
		jSourceArmyLabel.setText(String.valueOf(armyCount));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jComboBoxSourceCountries) {
			String sourceCountryName = (String) jComboBoxSourceCountries.getSelectedItem();
			CountryModel sourceCountry = mapHierarchyModel.searchCountry(sourceCountryName);
			updateSourceArmyLabel(sourceCountry.getNoOfArmiesCountry());
		} else if (e.getSource() == jComboBoxTargetCountries) {
			if (jComboBoxTargetCountries.getSelectedIndex() == 0) {
				updateTargetArmyLabel(0);
			} else {
				String targetCountryName = (String) jComboBoxTargetCountries.getSelectedItem();
				CountryModel targetCountry = mapHierarchyModel.searchCountry(targetCountryName);
				updateTargetArmyLabel(targetCountry.getNoOfArmiesCountry());
			}
		} else if (e.getSource() == jButtonAttack) {

		} else if (e.getSource() == jButtonAllOutAttack) {

		}
	}
}