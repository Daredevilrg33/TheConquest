/**
 * 
 */
package com.conquest.model;

/**
 * The Interface Strategy.
 *
 * @author Rohit Gupta
 */
public interface Strategy {

	/**
	 * Reinforcement phase.
	 *
	 * @param gameModel   the game model
	 * @param playerModel the player model
	 */
	public abstract void reinforcementPhase(GameModel gameModel, PlayerModel playerModel);

	/**
	 * Fortification phase.
	 *
	 * @param gameModel   the game model
	 * @param playerModel the player model
	 */
	public abstract void fortificationPhase(GameModel gameModel, PlayerModel playerModel);

	/**
	 * Attack phase.
	 *
	 * @param gameModel   the game model
	 * @param playerModel the player model
	 */
	public abstract void attackPhase(GameModel gameModel, PlayerModel playerModel);

	/**
	 * Assign initial army to country.
	 *
	 * @param gameModel   the game model
	 * @param playerModel the player model
	 */
	public abstract void assignInitialArmyToCountry(GameModel gameModel, PlayerModel playerModel);

}
