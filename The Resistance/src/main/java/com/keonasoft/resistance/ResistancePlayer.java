package com.keonasoft.resistance;

/**
 * Created by kushal on 1/13/14.
 */
public class ResistancePlayer extends Player {

    protected ResistancePlayer(String name){
        super(name);
        role = "Resistance";
        canReject = false;
    }

    @Override
    protected String getDetails(Player[] players) {
        return "Accept every mission. Don't be scummy. Win the game.";
    }
}
