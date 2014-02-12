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
import android.widget.Button;
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
    private List<String> gameTypes;
    private List<Integer> numPlayersList;
    private int numPlayers;
    private String gameType;
    private Game game;
    private ArrayList<EditText> textViewList;

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
                game = new NormalGame(numPlayers, this);
        }
        else
            game = new NormalGame(numPlayers, this);

        generatePlayerCreationView();
    }

    /**
     * This method creates the screen allowing input for player names, then it checks to see
     * if each player has a name. After the start game button is pressed, it adds the players to the
     * Game object and calls Game.startGame()
     */
    private void generatePlayerCreationView(){
        setContentView(R.layout.player_creation);
        LinearLayout container = (LinearLayout) findViewById(R.id.namePlayerView);
        textViewList = new ArrayList<EditText>();
        for(int i = 0; i < game.players.length; i++){
            EditText text = new EditText(this);
            text.setHint("Player " + (i + 1) + " name");
            text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            text.setTextSize(25);
            textViewList.add(text);
            container.addView(text);
        }
        Button startGameBtn = new Button(this);
        startGameBtn.setText("Start Game");
        startGameBtn.setTextSize(25);
        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] playerNames = new String[numPlayers];
                int i;
                for(i = 0; i < numPlayers; i++){
                    if(textViewList.get(i).getText().toString().length() > 0){
                        System.out.println(textViewList.get(i).getText());
                        playerNames[i] = (textViewList.get(i).getText().toString());
                    }
                    else
                        break;
                }
                if(i != numPlayers)
                    Toast.makeText(getApplicationContext(), "Make sure each player has a name.", Toast.LENGTH_SHORT).show();
                else{
                    game.createPlayerTypes(playerNames);
                    showPlayerRoles();
                }
            }
        });
        container.addView(startGameBtn);
    }

    /**
     * this calls the showPlayerRoles method in the Game object while passing this activity to the Game
     * in order to allow players to view their roles
     */
    private void showPlayerRoles(){
        game.showPlayerRoles(0, new Player[0]);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_save) {
            if(game != null && game.saveEnabled == true){
                Toast.makeText(getApplicationContext(), "Game Saved!", Toast.LENGTH_SHORT).show();
                return true;
            }
            else{
                Toast.makeText(getApplicationContext(), "Wait until the end of this round", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return super.onOptionsItemSelected(item);
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
