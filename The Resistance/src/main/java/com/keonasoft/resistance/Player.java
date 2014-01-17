package com.keonasoft.resistance;

/**
 * Created by kushal on 1/12/14.
 */
public abstract class Player {
    protected String name;
    protected String role;
    protected boolean canReject;

    /**
     * default constructor for any player
     * @param name
     */
    protected Player(String name){
        this.name = name;
    }

    /**
     * Allows certain types of players to have a details section which shows when players view their
     * role
     * @param players
     * @return
     */
    protected abstract String getDetails(Player[] players);
}
