package com.keonasoft.resistance;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by kushal on 1/13/14.
 */
public class NormalGame extends Game {

    /**
     * creates a game based on default play options
     * @param numPlayers
     * @param activity
     */
    public NormalGame(int numPlayers, ActionBarActivity activity){
        super(numPlayers, activity);
    }

    /**
     * creates a custom game
     * @param numPlayers
     * @param numResistance
     * @param numSpies
     * @param requiredAgents
     * @param numSpiesToFail
     * @param numMissions
     * @param activity
     */
    public NormalGame(int numPlayers, int numResistance, int numSpies, int[] requiredAgents, int[] numSpiesToFail, int numMissions, ActionBarActivity activity){
        super(numPlayers, numResistance, numSpies, requiredAgents, numSpiesToFail, numMissions, activity);
    }

    @Override
    /**
     * This game types implementation of how to play the game
     */
    protected void playGame() {
        System.out.println("LET THE GAMES BEGIN!!");
    }

    /**
     * randomly selects the players which will be spies for the round and creates the players for the game
     * @param playerNames
     */
    protected void createPlayerTypes(String[] playerNames) {
        List spyPlayers = new ArrayList();
        Random rand = new Random();

        for(int i = 0; i < numSpies; i++){
            int spyIndex = rand.nextInt(players.length);
            if(!spyPlayers.contains(spyIndex))
                spyPlayers.add(spyIndex);
            else
                i--;
        }

        for(int i = 0; i < players.length; i++){
            if(spyPlayers.contains(i))
                players[i] = new SpyPlayer(playerNames[i]);
            else
                players[i] = new ResistancePlayer(playerNames[i]);
        }
    }
}
