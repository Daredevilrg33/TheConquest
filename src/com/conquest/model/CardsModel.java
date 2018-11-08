package com.conquest.model;

import java.util.ArrayList;
import java.util.Random;


/**
 * The Class CardsModel.
 */
public class CardsModel {

	/** The card name. */
	private String cardName;
	
	/** The game model. */
	private GameModel gameModel;
	
	/** The random generator. */
	private Random randomGenerator;
	
	/** The type. */
	private int type;


//	public CardsModel(String name) {
//		this.cardName = name;
//	}	
	
	
	/**
 * Instantiates a new cards model.
 *
 * @param name the name
 * @param type the type
 * @param gameModel the game model
 */
public CardsModel(String name,int type, GameModel gameModel)
	{
		this.cardName =  name;
		this.type =  type;
		this.gameModel= gameModel;
		randomGenerator= new Random();
	}

	/**
	 * Gets the card name.
	 *
	 * @return the card name
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * Sets the card name.
	 *
	 * @param cardName the new card name
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}




//	public CardsModel generateRandomCard() {
//
//		String[] names = { "Infantry", "Cavalry", "Artillery" };
//		int index = (int) (Math.random() * names.length);
//		String name = names[index];
//		CardsModel newCard = new CardsModel(name);
/**
 * Gets the type.
 *
 * @return the type
 */
//	}	
	public int getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * Generate random card.
	 *
	 * @return the cards model
	 */

	public CardsModel generateRandomCard()
	{
		
		int index = randomGenerator.nextInt(gameModel.getTotalCards().size());
		ArrayList<CardsModel> cardsList =  gameModel.getTotalCards();
        CardsModel newCard =cardsList.get(index);
        cardsList.remove(index);
		return newCard;
	}

}
