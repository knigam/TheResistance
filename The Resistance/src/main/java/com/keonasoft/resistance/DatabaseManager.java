package com.keonasoft.resistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kushal on 2/12/14.
 */
public class DatabaseManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DatabaseManager";
    private static final String DATABASE_NAME = "ResistanceDB";
    private static final String GAME_TABLE_NAME = "game";
    private static final String PLAYER_TABLE_NAME = "player";
    private static final String ROUND_TABLE_NAME = "round";
    private static final String GAME_TABLE_CREATE ="CREATE TABLE " + GAME_TABLE_NAME + " (" +
            "game_id int, " +
            "curr_commander int, " +
            "round int, " +
            "num_players int, " +
            "num_rounds int, " +
            "game_type varchar(20)" +
            ");";
    private static final String PLAYER_TABLE_CREATE = "CREATE TABLE " + PLAYER_TABLE_NAME + " (" +
            "player_id int, " +
            "game_id, " +
            "player_name, " +
            "turn_number, " +
            "role varchar(20)" +
            ");";
    private static final String ROUND_TABLE_CREATE = "CREATE TABLE" + ROUND_TABLE_NAME + " (" +
            "round_id int, " +
            "game_id int, " +
            "round_num, " +
            "num_agents int, " +
            "num_spies_to_fail int, " +
            "round_winner varchar(20)" +
            ");";

    DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GAME_TABLE_CREATE);
        db.execSQL(PLAYER_TABLE_CREATE);
        db.execSQL(ROUND_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * Loads a certain game from database and starts that game
     * @return
     */
    public static boolean loadGame(){
        try{

            return true;
        }
        catch (Exception e){
            Log.i(TAG, "Load Failed: " + e.toString());
            return false;
        }
    }

    /**
     * saves the current state of the game to database
     * @return
     */
    public static Game saveGame(){
        Game game = null;
        try{
            return game;
        }
        catch (Exception e){
            Log.i(TAG, "Save Failed: " + e.toString());
            return null;
        }
    }
}
