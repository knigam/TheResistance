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
     * @param role
     */
    protected Player(String name, String role){
        this.name = name;
        this.role = role;
    }

    /**
     * this will display the player's current role and additional game details they must know
     * based on their role in the game
     */
    protected abstract R.layout viewRole();

    /**
     * This displays the screen which allows players to either accept or decline a mission
     * they are put on based on the limitations of their role
     */
    protected abstract R.layout viewAcceptOrDecline();
}
