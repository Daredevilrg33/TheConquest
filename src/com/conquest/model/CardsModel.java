package com.conquest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * The Class CardsModel.
 */
public class CardsModel extends Observable implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long SERIAL_VERSION_UID = 11L;

	/** The card name. */
	private String cardName;

	/** The type. */
	private int type;

	/**
	 * Instantiates a new cards model.
	 *
	 * @param name the name
	 * @param type the type
	 */
	public CardsModel(String name, int type) {
		this.cardName = name;
		this.type = type;
	}

	/**
	 * Gets the card name.
	 *
	 * @return cardName
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
	 * @param type the new type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Update changes.
	 */
	private void updateChanges() {
		setChanged();
		notifyObservers(this);
	}
}
