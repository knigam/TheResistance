package com.keonasoft.resistance;

import android.content.Intent;
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

/**
 * @author kushal
 * The first activity the app opens to when the app is started
 * allows user to choose to start a new game or load a game
 */
public class MainScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
    }

    /**
     * Button OnClick listener for newGameBtn
     * @param view
     */
    public void newGameOnClick(View view){
        Intent intent = new Intent(MainScreen.this, MainGame.class);
        MainScreen.this.startActivity(intent);
    }

    /**
     * Button OnClick listener for loadGameBtn
     * @param view
     */
    public void loadGameOnClick(View view){
        //TODO
    }

    /**
     * Button OnClick listener for customGameBtn
     * @param view
     */
    public void customGameOnClick(View view){
        //TODO
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

   *//**
     * A placeholder fragment containing a simple view.
     *//*
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_screen, container, false);
            return rootView;
        }
    }*/

}
