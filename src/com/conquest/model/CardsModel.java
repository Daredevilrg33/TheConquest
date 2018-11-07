package com.conquest.model;

public class CardsModel {
	
	private String cardName;

	public CardsModel(String name)
	{
		this.cardName =  name;
	}
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	public CardsModel generateRandomCard()
	{
		
		String[] names = {"Infantry", "Cavalry", "Artillery"};
		int index = (int) (Math.random() * names.length);
		String name = names[index];
		CardsModel newCard = new CardsModel(name);
		return newCard;
	}

}
