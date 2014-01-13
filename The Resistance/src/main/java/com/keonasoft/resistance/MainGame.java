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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kushal on 1/12/14.
 */
public class MainGame extends ActionBarActivity {
    private Spinner gameTypeSpinner, numPlayersSpinner;
    List<String> gameTypes;
    List<Integer> numPlayersList;
    int numPlayers;
    String gameType;
    Game game;

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

    /**
     * OnClick Button Listener for the next button in default game option screen
     * @param view
     */
    public void defaultOptionNextOnClick(View view){
        //Use if/else if statements to determine which game type to play
        if (gameType.equals(gameTypes.get(0))){ //This is a normal game
                game = new NormalGame(numPlayers);
        }
        else
            game = new NormalGame(numPlayers);

        generatePlayerCreationView();
    }

    private void generatePlayerCreationView(){
        setContentView(R.layout.player_creation);
        LinearLayout container = (LinearLayout) findViewById(R.id.namePlayerView);
        ArrayList<EditText> textViewList = new ArrayList<EditText>();
        for(int i = 0; i < game.players.length; i++){
            EditText text = new EditText(this);
            text.setHint("Player " + (i + 1) + " name");
            text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            text.setTextSize(25);
            textViewList.add(text);
            container.addView(text);

        }
    }

    /**
     * This sets the items for the Game Type Spinner in game setup
     */
    private void setUpGameTypeSpinner(){
        gameTypeSpinner = (Spinner) findViewById(R.id.gameTypeSpinner);

        gameTypes = new ArrayList<String>();
        gameTypes.add("Normal");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, gameTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameTypeSpinner.setAdapter(dataAdapter);

        gameTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gameType = gameTypes.get(position);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                //Left empty because this is not necessary
            }});
    }

    /**
     * This sets items for the Number of Players Spinner in game setup
     */
    private void setUpNumPlayerSpinner(){
        numPlayersSpinner = (Spinner) findViewById(R.id.numPlayersSpinner);

        numPlayersList = new ArrayList<Integer>();
        numPlayersList.add(5);
        numPlayersList.add(6);
        numPlayersList.add(7);
        numPlayersList.add(8);
        numPlayersList.add(9);
        numPlayersList.add(10);

        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, numPlayersList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numPlayersSpinner.setAdapter(dataAdapter);

        numPlayersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                numPlayers = numPlayersList.get(position);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                //Left empty because this is not necessary
            }});
    }

}
