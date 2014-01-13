package com.keonasoft.resistance;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kushal on 1/12/14.
 */
public class MainGame extends ActionBarActivity {
    private Spinner gameTypeSpinner, numPlayersSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game_setup);
        setUpGameTypeSpinner();
        setUpNumPlayerSpinner();

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
    }

    public void defaultOptionNextOnClick(View view){

    }

    /**
     * This sets the items for the Game Type Spinner in game setup
     */
    private void setUpGameTypeSpinner(){
        gameTypeSpinner = (Spinner) findViewById(R.id.gameTypeSpinner);

        List<String> gameTypes = new ArrayList<String>();
        gameTypes.add("Normal");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, gameTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameTypeSpinner.setAdapter(dataAdapter);

        gameTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }});
    }

    /**
     * This sets items for the Number of Players Spinner in game setup
     */
    private void setUpNumPlayerSpinner(){
        numPlayersSpinner = (Spinner) findViewById(R.id.numPlayersSpinner);

        List<Integer> numPlayers = new ArrayList<Integer>();
        numPlayers.add(5);
        numPlayers.add(6);
        numPlayers.add(7);
        numPlayers.add(8);
        numPlayers.add(9);
        numPlayers.add(10);

        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, numPlayers);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numPlayersSpinner.setAdapter(dataAdapter);

        numPlayersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }});
    }

}
