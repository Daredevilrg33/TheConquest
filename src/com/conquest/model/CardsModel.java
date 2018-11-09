package com.conquest.model;

import java.util.ArrayList;

/**
 * The Class CardsModel.
 */
public class CardsModel {

	/** The card name. */
	private String cardName;

	/** The game model. */
	private GameModel gameModel;

	/** The type. */
	private int type;

	/**
	 * Instantiates a new cards model.
	 *
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @param gameModel
	 *            the game model
	 */
	public CardsModel(String name, int type, GameModel gameModel) {
		this.cardName = name;
		this.type = type;
		this.gameModel = gameModel;
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
	 * @param cardName
	 *            the new card name
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(int type) {
		this.type = type;
	}

}
