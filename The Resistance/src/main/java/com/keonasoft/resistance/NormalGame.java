package com.keonasoft.resistance;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        int minToWin = (numMissions/2) + 1;  //finds the number of missions needed to win
        if(numResistanceWins < minToWin && numSpyWins < minToWin){ //determines if the game is over yet
            activity.setContentView(R.layout.setup_mission);
            setUpMissionDetails();

            //makes sure no agents are currently selected when the ListView check boxes reset
            agentsSelectedForMission.clear();
            TextView currCommanderTextView = (TextView) activity.findViewById(R.id.currCommanderTextView);
            currCommanderTextView.setText(players[currCommander].name);

            //The following code fills in the multi select ListView with player names
            ListView playerNameListView = (ListView) activity.findViewById(R.id.playerNameListView);
            List<String> playerNames = new ArrayList<String>();
            for(Player p : players) playerNames.add(p.name);

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity,
                    android.R.layout.simple_list_item_multiple_choice, playerNames);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            playerNameListView.setAdapter(dataAdapter);
            playerNameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String nameSelected = (String) (parent.getItemAtPosition(position));
                    if(agentsSelectedForMission.contains(nameSelected))
                        agentsSelectedForMission.remove(nameSelected);
                    else
                        agentsSelectedForMission.add(nameSelected);
                }
            });

            /**
             * This determines what happens when the commanders mission is accepted
             * if the correct number is selected, it goes to voting, otherwise it displays a toast
             */
            Button acceptCommanderChoiceBtn = (Button) activity.findViewById(R.id.acceptCommanderChoiceBtn);
            acceptCommanderChoiceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(agentsSelectedForMission.size() == requiredAgents[currRoundNum]){
                        Player[] selectedPlayers = new Player[requiredAgents[currRoundNum]];
                        int j = 0;
                        for(Player p : players){
                            if(agentsSelectedForMission.contains(p.name)){
                                selectedPlayers[j] = p;
                                j++;
                            }
                        }
                        goToNextCommander();
                        showPlayerRoles(0, selectedPlayers);
                    }
                    else
                        Toast.makeText(activity.getApplicationContext(), "You must select " + requiredAgents[currRoundNum] + " players", Toast.LENGTH_SHORT).show();
                }
            });

            /**
             * determines what to do if commander choice is rejected
             * if numselected is correct change commander to next commander and playGame to reset
             * checks and players Selected
             */
            Button rejectCommanderChoiceBtn = (Button) activity.findViewById(R.id.rejectCommanderChoiceBtn);
            rejectCommanderChoiceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(agentsSelectedForMission.size() == requiredAgents[currRoundNum]){
                        goToNextCommander();
                        playGame();
                    }
                    else
                        Toast.makeText(activity.getApplicationContext(), "You must select " + requiredAgents[currRoundNum] + " players", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            activity.setContentView(R.layout.game_over_view);
            TextView winnerTextView = (TextView) activity.findViewById(R.id.winnerTextView);
            if(numResistanceWins == minToWin)
                winnerTextView.setText("Resistance Wins");
            else if(numSpyWins == minToWin)
                winnerTextView.setText("Spy's Win");
        }
    }

    /**
     * increments the commander by one, making sure to loop to the first player if necessary
     */
    protected void goToNextCommander(){
        if(currCommander == (players.length - 1))
            currCommander = 0;
        else
            currCommander++;
    }

    /**
     * populates mission images and details for top of setup_mission layout
     */
    protected void setUpMissionDetails(){
        LinearLayout missionImageContainer = (LinearLayout) activity.findViewById(R.id.missionImageContainer);
        LinearLayout missionDetailsContainer = (LinearLayout) activity.findViewById(R.id.missionDetailsContainer);

        for(int i = 0; i < numMissions; i++){
            ImageView missionImage = new ImageView(activity);
            missionImage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, (float)(1.00/numMissions)));
            TextView missionDetails = new TextView(activity);
            missionDetails.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, (float)(1.00/numMissions)));
            missionDetails.setGravity(Gravity.CENTER);
            missionDetails.setText(Math.abs(missionSuccess[i]) + "");

            if(missionSuccess[i] >= 0 && i < currRoundNum){  //Resistance won this round

            }
            else if(missionSuccess[i] < 0){  //Spies won this round

            }
            else{ //round hasn't started yet
                missionDetails.setText(requiredAgents[i] + "");
            }
            missionImageContainer.addView(missionImage);
            missionDetailsContainer.addView(missionDetails);
        }
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
        playGame();
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
