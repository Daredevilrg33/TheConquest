/**
 * 
 */
package com.conquest.window;

import java.util.List;

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
public class AttackPhaseWindow extends JFrame {
	private JLabel jSourceArmyLabel;
	private JLabel jDestinationArmyLabel;
	private JComboBox<String> jComboBoxSourceCountries;
	private JComboBox<String> jComboBoxDestinationCountries;

	public AttackPhaseWindow(MapHierarchyModel mapHierarchyModel, PlayerModel playerModel) {

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
		updateComboBoxCountries(playerModel.getPlayerCountryList());
		jComboBoxDestinationCountries = new JComboBox<>();
		jComboBoxDestinationCountries.setBounds(300, 50, 100, 30);
		add(jComboBoxDestinationCountries);

		JLabel jSourceCountryLabel = new JLabel();
		jSourceCountryLabel.setBounds(170, 20, 200, 30);
		jSourceCountryLabel.setText("Source Country");
		add(jSourceCountryLabel);

		JLabel jDestinationCountryLabel = new JLabel();
		jDestinationCountryLabel.setBounds(300, 20, 200, 30);
		jDestinationCountryLabel.setText("Destination Country");
		add(jDestinationCountryLabel);

		jSourceArmyLabel = new JLabel();
		jSourceArmyLabel.setBounds(217, 78, 200, 30);
		add(jSourceArmyLabel);

		jDestinationArmyLabel = new JLabel();
		jDestinationArmyLabel.setBounds(337, 78, 200, 30);
		add(jDestinationArmyLabel);

	}

	public void updateComboBoxCountries(List<CountryModel> countryModels) {
		jComboBoxSourceCountries.removeAllItems();
		for (CountryModel countryModel : countryModels) {
			jComboBoxSourceCountries.addItem(countryModel.getCountryName());
		}
	}
}
