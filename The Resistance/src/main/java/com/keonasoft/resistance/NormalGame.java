package com.keonasoft.resistance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by kushal on 1/13/14.
 */
public class NormalGame extends Game {

    public NormalGame(int numPlayers){
        super(numPlayers);
    }
    public NormalGame(int numPlayers, int numResistance, int numSpies, int[] requiredAgents, int[] numSpiesToFail, int numMissions){
        super(numPlayers, numResistance, numSpies, requiredAgents, numSpiesToFail, numMissions);
    }
    @Override
    protected void playGame() {

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
