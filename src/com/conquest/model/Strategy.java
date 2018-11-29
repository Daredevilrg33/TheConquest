/**
 * 
 */
package com.conquest.model;

/**
 * @author Rohit Gupta
 *
 */
public interface Strategy {

	public abstract void reinforcementPhase(GameModel gameModel,PlayerModel playerModel);

	public abstract void fortificationPhase(GameModel gameModel,PlayerModel playerModel);

	public abstract void attackPhase(GameModel gameModel,PlayerModel playerModel);

	public abstract void assignInitialArmyToCountry(GameModel gameModel,PlayerModel playerModel);

}
