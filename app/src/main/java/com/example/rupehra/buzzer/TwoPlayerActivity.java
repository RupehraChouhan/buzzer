package com.example.rupehra.buzzer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class TwoPlayerActivity extends ActionBarActivity {

    private static final String FILENAME = "twoplayerstat.sav";		/* cant change this file name*/

    private ArrayList<Integer> countList = new ArrayList<Integer>();

    TwoPlayerCount tpc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tpc = new TwoPlayerCount();
        setContentView(R.layout.activity_two_player);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_three_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClick(View view) {
        playerClicked(view.getId());
    }

    public void playerClicked(int id) {
        int newID ;
        if (id == R.id.player12) {
            newID = R.id.player12;
            tpc.addPlayerOneCount();                    //checking which player hit the button

        } else  {
            newID = R.id.player22;
            tpc.addPlayerTwoCount();
        }

        dialog(newID);
    }

    private void dialog(int id) {
        String player;
        Integer count;
        if (id == R.id.player12) {
            player = "Player 1";                //getting button id
            count = tpc.getP12();
        } else {
            player = "Player 2";
            count = tpc.getP22();
        }
        String msg = player+" pressed first\n count = "+count;                  //displaying the winner
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(msg);
        dialogBuilder.setNeutralButton(R.string.dismiss, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    @Override
    protected void onStart(){
        super.onStart();
        tpc = new TwoPlayerCount();
        loadFromFile();
        if(countList==null){                        //loading previous counts using load function
            throw new RuntimeException();
        }
        else if (countList.size()==0){
            TwoPlayerCount tpc = new TwoPlayerCount();
        }
        else {
            tpc.setP12(countList.get(0));
            tpc.setP22(countList.get(1));
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        countList.add(0, tpc.getP12());                 //storing counts in a list when user presses the back button
        countList.add(1, tpc.getP22());
        saveInFile();
    }


    private void saveInFile(){

        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(countList,writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {                     //storing list content in a file
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Toast.makeText(ThreePlayerActivity.this, countList.size(), Toast.LENGTH_SHORT).show();

    }

    private void loadFromFile() {                           //loading file contents
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html retrieved 2015-09-21
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
            countList = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            countList = new ArrayList<>();
            //tweets = new ArrayList<Tweet>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
