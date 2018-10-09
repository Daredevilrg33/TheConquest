package com.conquest.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapModel;
import com.conquest.utilities.Constants;
import com.conquest.utilities.Utility;

/**
 * @author Rohit Gupta
 *
 */
public class GameWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 705136013521611381L;


	public GameWindow(String filePath, String noOfPlayers ) {
		
		setTitle("Game Window");
		setResizable(false);
		setSize(Constants.WIDTH,Constants.HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		Utility utility = new Utility();
		MapModel mapModel = utility.parseMapFile(filePath);
		
		System.out.println("List of Continents size: " + mapModel.getContinentModels().size());
		for(ContinentModel continentModel : mapModel.getContinentModels())
		{
			System.out.println("Continent is: " + continentModel);
		}
		for(CountryModel countryModel: mapModel.getCountryModels())
		{
			System.out.println("Country is: " + countryModel);
		}
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
				InitialScreen initialScreen = new InitialScreen();
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
	}
}
