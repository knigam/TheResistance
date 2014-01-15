package com.keonasoft.resistance;

import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kushal on 1/12/14.
 */
public abstract class Game {
    protected int numResistance;
    protected int numSpies;
    protected int[] requiredAgents; //this is how many agents must go on each mission
    protected int[] numSpiesToFail; //the number of spies which must fail for a mission to fail
    protected int numMissions;
    protected int[] missionSuccess; //-1 means mission failed, 0 means mission hasn't started, 1 means succeeded
    protected int currRoundNum = 0;
    protected int currNumFails = 0; //The number of spies who chose to decline the mission
    protected int currCommander = 0;
    protected Player[] players;
    protected ActionBarActivity activity;

    /**
     * constructor for a game with predefined values based on number of players
     * @param numPlayers
     */
    protected Game(int numPlayers, ActionBarActivity activity){
        this.activity = activity;
        players = new Player[numPlayers];
        numMissions = 5;
        missionSuccess = new int[numMissions];

        //This sets up default values from game rules based on number of players
        switch(numPlayers){
            case 5:
                numResistance = 3;
                requiredAgents = new int[] {2,3,2,3,3};
                break;
            case 6:
                numResistance = 4;
                requiredAgents = new int[] {2,3,4,3,4};
                break;
            case 7:
                numResistance = 4;
                requiredAgents = new int[] {2,3,3,4,4};
                break;
            case 8:
                numResistance = 5;
                requiredAgents = new int[] {3,4,4,5,5};
                break;
            case 9:
                numResistance = 6;
                requiredAgents = new int[] {3,4,4,5,5};
                break;
            case 10:
                numResistance = 6;
                requiredAgents = new int[] {3,4,4,5,5};
                break;
        }

        //Determine how many spies must fail at each round for the mission to fail
        if(numPlayers > 6)
            numSpiesToFail = new int[] {1,1,1,2,1};
        else
            numSpiesToFail = new int[] {1,1,1,1,1};

        numSpies = numPlayers - numResistance;
    }

    /**
     * Overloaded constructor allowing for creation of custom games
     * @param numPlayers
     * @param numResistance
     * @param numSpies
     * @param requiredAgents
     * @param numSpiesToFail
     * @param numMissions
     */
    protected Game(int numPlayers, int numResistance, int numSpies, int[] requiredAgents, int[] numSpiesToFail, int numMissions, ActionBarActivity activity){
        players = new Player[numPlayers];
        this.numResistance = numResistance;
        this.numSpies = numSpies;
        this.requiredAgents = requiredAgents;
        this.numSpiesToFail = numSpiesToFail;
        this.numMissions = numMissions;
        this.activity = activity;
        this.missionSuccess = new int[numMissions];
    }

    /**
     * setter method for players
     * @param players
     */
    protected void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Sets up the screen which hides player information such as role and voting details
     * @param playerNum the index of the current player as listed in array players
     * @param playersVoting Players which are voting on mission. If empty, show player roles
     */
    protected void showPlayerRoles(int playerNum, Player[] playersVoting){
        final int PLAYER_NUM = playerNum;
        final Player[] PLAYERS_VOTING = playersVoting;
        activity.setContentView(R.layout.player_view);
        TextView playerNameTextView = (TextView) activity.findViewById(R.id.playerNameTextView);
        Button acceptBtn = (Button) activity.findViewById(R.id.acceptBtn);

        if(playersVoting.length == 0){
            playerNameTextView.setText(players[playerNum].name);
            acceptBtn.setText("Hold to View Role");
            acceptBtn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    viewRole(PLAYER_NUM);
                    return false;
                }
            });
        }
        else{
            playerNameTextView.setText(playersVoting[playerNum].name);
            acceptBtn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    voteOnMission(PLAYER_NUM, PLAYERS_VOTING);
                    return false;
                }
            });
        }
    }

    /**
     * sets up screen so players can view their role discretely
     * @param playerNum
     */
    private void viewRole(int playerNum){
        final int PLAYER_NUM = playerNum;
        TextView roleTextView = (TextView) activity.findViewById(R.id.roleTextView);
        TextView playerRoleTextView = (TextView) activity.findViewById(R.id.playerRoleTextView);
        TextView detailsTextView = (TextView) activity.findViewById(R.id.detailsTextView);
        Button acceptBtn = (Button) activity.findViewById(R.id.acceptBtn);

        roleTextView.setVisibility(1);
        playerRoleTextView.setVisibility(1);
        playerRoleTextView.setText(players[playerNum].role);

        detailsTextView.setVisibility(1);
        detailsTextView.setText(players[playerNum].getDetails(players));

        acceptBtn.setText("Hold to Hide Role");
        acceptBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(PLAYER_NUM + 1 < players.length)
                    showPlayerRoles(PLAYER_NUM + 1, new Player[0]);
                else
                    playGame();
                return false;
            }
        });
    }

    /**
     * Allows players put on missions to vote to either accept or decline the mission
     * @param playerNum
     */
    private void voteOnMission(int playerNum, Player[] playersVoting){
        final int PLAYER_NUM = playerNum;
        final Player[] PLAYERS_VOTING = playersVoting;
        TextView roleTextView = (TextView) activity.findViewById(R.id.roleTextView);
        TextView playerRoleTextView = (TextView) activity.findViewById(R.id.playerRoleTextView);
        TextView detailsTextView = (TextView) activity.findViewById(R.id.detailsTextView);
        Button acceptBtn = (Button) activity.findViewById(R.id.acceptBtn);
        Button rejectBtn = (Button) activity.findViewById(R.id.rejectBtn);

        roleTextView.setVisibility(1);
        playerRoleTextView.setVisibility(1);
        detailsTextView.setVisibility(1);

        //This allows rejection button to be visible and clickable to Players who can reject
        if(players[playerNum].canReject){
            rejectBtn.setVisibility(1);
            rejectBtn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    currNumFails++;
                    if(PLAYER_NUM + 1 < PLAYERS_VOTING.length)
                        showPlayerRoles(PLAYER_NUM + 1, PLAYERS_VOTING);
                    else
                        isMissionSuccess();
                    return false;
                }
            });
        }

        playerRoleTextView.setText(players[playerNum].role);
        detailsTextView.setText(players[playerNum].getDetails(players));

        acceptBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(PLAYER_NUM + 1 < PLAYERS_VOTING.length)
                    showPlayerRoles(PLAYER_NUM + 1, PLAYERS_VOTING);
                else
                    isMissionSuccess();
                return false;
            }
        });
    }

    protected abstract  void isMissionSuccess();
    protected abstract void playGame();
    protected abstract void createPlayerTypes(String[] playerNames);
}
