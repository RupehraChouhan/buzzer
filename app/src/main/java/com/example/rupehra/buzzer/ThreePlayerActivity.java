// Copyright 2015 Rupehra Chouhan
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License



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

public class ThreePlayerActivity extends ActionBarActivity {

    private static final String FILENAME = "threeplayerstat.sav";		/* cant change this file name*/

    private ArrayList<Integer> countList = new ArrayList<Integer>();

    ThreePlayerCount tpc1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tpc1 = new ThreePlayerCount();
        setContentView(R.layout.activity_three_player);
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
        int newID;
        if (id == R.id.player13) {
            newID = R.id.player13;
            tpc1.incrementP13();

        } else if (id == R.id.player23){                    //checking which player pressed the button and incrementing its count
            newID = R.id.player23;
            tpc1.incrementP23();
        }
        else{
            tpc1.incrementP33();
            newID = R.id.player33;
        }
        dialog(newID);
    }

    private void dialog(int id) {
        String player;
        Integer count;
        if (id == R.id.player13) {
            player = "Player 1";
            count = tpc1.getP13();
        } else if (id == R.id.player23){
            player = "Player 2";
            count = tpc1.getP23();
        }
        else{
            player = "Player 3";
            count = tpc1.getP33();
        }
        String msg = player+" pressed first!\n count = "+count;                     //using dialog box to show the winner
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
        loadFromFile();             //loading data from file when the game starts
        if(countList==null){
            throw new RuntimeException();
        }
        else if (countList.size()==0){
            ThreePlayerCount tpc1 = new ThreePlayerCount();
        }
        else {
            tpc1.setP13(countList.get(0));
            tpc1.setP23(countList.get(1));
            tpc1.setP33(countList.get(2));
        }
    }

    @Override
    protected void onPause(){
        super.onPause();                    //saving data in a list when user presses the back button
        countList.add(0, tpc1.getP13());
        countList.add(1, tpc1.getP23());
        countList.add(2, tpc1.getP33());
        saveInFile();
    }


    private void saveInFile(){

        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(countList,writer);                  //saving list contents in a file
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadFromFile() {    //function to load the data
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
