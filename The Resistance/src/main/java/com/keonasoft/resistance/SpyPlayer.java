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
    protected R.layout viewRole() {
        return null;
    }

    @Override
    protected R.layout viewAcceptOrDecline() {
        return null;
    }
}
