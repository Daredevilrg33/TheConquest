/**
 * 
 */
package com.conquest.model;

/**
 * @author Rohit Gupta
 *
 */
public interface GamePhase {

	public abstract void reinforcementPhase();

	public abstract void fortificationPhase(GameModel gameModel);

	public abstract String attackPhase(GameModel gameModel);

	public abstract void assignInitialArmyToCountry(GameModel gameModel);

}
