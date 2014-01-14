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
    protected String getDetails(Player[] players) {
        StringBuilder sb = new StringBuilder();
        sb.append("Spies: ");
        for(Player p : players){
            if(p.role.equals(this.role)){
                sb.append(p.name + ", ");
            }
        }
        return sb.toString();
    }

    @Override
    protected int viewRole() {
        return 0;
    }

    @Override
    protected int viewVote() {
        return 0;
    }
}
