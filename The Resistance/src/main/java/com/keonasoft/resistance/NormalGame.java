package com.keonasoft.resistance;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by kushal on 1/13/14.
 */
public class NormalGame extends Game {

    protected int numResistanceWins = 0, numSpyWins = 0;

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
        if(numResistanceWins < 3 && numSpyWins < 3 && currRoundNum < 5){
            activity.setContentView(R.layout.setup_mission);
            ListView playerNameListView = (ListView) activity.findViewById(R.id.playerNameListView);

            setUpMissionDetails();
            List<String> playerNames = new ArrayList<String>();
            for(Player p : players) playerNames.add(p.name);

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity,
                    android.R.layout.simple_list_item_multiple_choice, playerNames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            playerNameListView.setAdapter(dataAdapter);

            //isMissionSuccess();
        }
        else;
            //TODO end game
    }

    /**
     * determines whether the mission was a success or not and increments the current round
     */
    protected void isMissionSuccess(){
        //If the required number of spies to fail or more fail, then subtract that number in missionSuccess
        if(currNumFails >= numSpiesToFail[currRoundNum]){
            missionSuccess[currRoundNum] -= currNumFails;
            numSpyWins++;
        }
        //If less than required number of spies fail, add that number so players know how many spies failed
        else{
            missionSuccess[currRoundNum] += currNumFails;
            numResistanceWins++;
        }
        currNumFails = 0;
        currRoundNum++;
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
