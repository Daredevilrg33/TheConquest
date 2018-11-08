package com.conquest.model;

import java.util.ArrayList;
import java.util.Random;

public class CardsModel {

	private String cardName;
	private GameModel gameModel;
	private Random randomGenerator;
	private int type;


//	public CardsModel(String name) {
//		this.cardName = name;
//	}	
	public CardsModel(String name,int type, GameModel gameModel)
	{
		this.cardName =  name;
		this.type =  type;
		this.gameModel= gameModel;
		randomGenerator= new Random();
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}


//	public CardsModel generateRandomCard() {
//
//		String[] names = { "Infantry", "Cavalry", "Artillery" };
//		int index = (int) (Math.random() * names.length);
//		String name = names[index];
//		CardsModel newCard = new CardsModel(name);
//	}	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public CardsModel generateRandomCard()
	{
		
		int index = randomGenerator.nextInt(gameModel.getTotalCards().size());
		ArrayList<CardsModel> cardsList =  gameModel.getTotalCards();
        CardsModel newCard =cardsList.get(index);
        cardsList.remove(index);
		return newCard;
	}

}
