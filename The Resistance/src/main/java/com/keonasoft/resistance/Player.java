package com.keonasoft.resistance;

/**
 * Created by kushal on 1/12/14.
 */
public abstract class Player {
    protected String name;
    protected String role;

    /**
     * default constructor for any player
     * @param name
     */
    protected Player(String name){
        this.name = name;
    }

    protected abstract String getDetails(Player[] players);

    /**
     * this will display the player's current role and additional game details they must know
     * based on their role in the game
     */
    protected abstract int viewRole();

    /**
     * This displays the screen which allows players to either accept or decline a mission
     * they are put on based on the limitations of their role
     */
    protected abstract int viewVote();
}
