package com.keonasoft.resistance;

/**
 * Created by kushal on 1/12/14.
 */
public abstract class Game {
    protected int numResistance;
    protected int numSpies;
    protected int[] requiredAgents; //this is how many agents must go on each mission
    protected int[] numSpiesToFail; //the number of spies which must fail for a mission to fail
    protected int numMissions;
    protected int[] missionSuccess = new int[] {0,0,0,0,0}; //-1 means mission failed, 0 means mission hasn't started, 1 means succeeded
    protected int currRoundNum = 0;
    protected int currNumFails = 0; //The number of spies who chose to decline the mission
    protected Player[] players;

    /**
     * constructor for a game with predefined values based on number of players
     * @param numPlayers
     */
    protected Game(int numPlayers){
        players = new Player[numPlayers];
        numMissions = 5;


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
    protected Game(int numPlayers, int numResistance, int numSpies, int[] requiredAgents, int[] numSpiesToFail, int numMissions){
        players = new Player[numPlayers];
        this.numResistance = numResistance;
        this.numSpies = numSpies;
        this.requiredAgents = requiredAgents;
        this.numSpiesToFail = numSpiesToFail;
        this.numMissions = numMissions;
    }

    /**
     * setter method for players
     * @param players
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    protected abstract void playGame();
    protected abstract void createPlayerTypes(String[] playerNames);
}
