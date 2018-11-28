/**
 * 
 */
package com.conquest.model;

import java.io.Serializable;

/**
 * @author Rohit Gupta
 *
 */
public class HumanPlayer extends PlayerModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param playerName
	 * @param playerType
	 */
	public HumanPlayer(String playerName, PlayerType playerType) {
		super(playerName, playerType);
		// TODO Auto-generated constructor stub
	}

}
