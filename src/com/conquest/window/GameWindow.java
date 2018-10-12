package com.conquest.window;


import java.util.Random; 
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.conquest.window.SecondScreen;

import com.conquest.mapeditor.model.ContinentModel;
import com.conquest.mapeditor.model.CountryModel;
import com.conquest.mapeditor.model.MapModel;
import com.conquest.mapeditor.model.PlayerModel;
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
				
		System.out.println("Size of Continent List: " + mapModel.getContinentModels().size());
		
		System.out.println("Size of Country List: " + mapModel.getCountryModels().size());
		
		/* randomly placing army of each player 
		 * in different country by round robin
		 * */
		
		int players = 0;
		players=Integer.parseInt(SecondScreen.noOfPlayers);
		PlayerModel[] player= new PlayerModel[players];
		
		for(int j = 0; j<players;j++) {
			player[j] = new PlayerModel();
		}
		
		// player[0].AddCountry(countryModelTest);

		//System.out.println(player[0].getPlayerCountryList().get(0).getCountryName());
		
		int count1 = 0;
		Random rand = new Random();
		int pickedNumber = 0;
		//player[count1].AddCountry();
		//pickedNumber = rand.Random(mapModel.getCountryModels().size());

		//int h =0;
		
		while(!(mapModel.getCountryModels().isEmpty())) {
			for (count1 = 0;count1 < players;count1++) {
				if(!(mapModel.getCountryModels().isEmpty())) {
					pickedNumber = rand.nextInt(mapModel.getCountryModels().size());
					CountryModel countryModelTest = mapModel.getCountryModels().get(pickedNumber);
					player[count1].AddCountry(countryModelTest);
					//System.out.println(mapModel.getCountryModels().get(pickedNumber));
					mapModel.getCountryModels().remove(pickedNumber);
					//h++;
					//System.out.println("size "+mapModel.getCountryModels().size());
					//System.out.println("Picked Number "+pickedNumber);
					//System.out.println(h);
			
				}
			}
		}
		
		
		/*countries can be accessed by 
		player[index].getPlayerCountryList().get(index);
		*/
		
		
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
