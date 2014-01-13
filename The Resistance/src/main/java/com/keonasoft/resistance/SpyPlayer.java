package com.keonasoft.resistance;

/**
 * Created by kushal on 1/13/14.
 */
public class SpyPlayer extends Player {

    protected SpyPlayer(String name){
        super(name);
        role = "Spy";
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
