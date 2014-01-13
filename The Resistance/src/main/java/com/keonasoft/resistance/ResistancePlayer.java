package com.keonasoft.resistance;

/**
 * Created by kushal on 1/13/14.
 */
public class ResistancePlayer extends Player {

    protected ResistancePlayer(String name){
        super(name);
        role = "Resistance";
    }

    @Override
    protected int viewRole() {
        return 0;
    }

    @Override
    protected int viewAcceptOrDecline() {
        return 0;
    }
}
