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
import android.content.Intent;
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




//records the counts of all the players, saves them, loads them and diplay the winner and the count


public class FourPlayerActivity extends ActionBarActivity {

    private static final String FILENAME = "fourplayerstat.sav";		/* cant change this file name*/
    private ArrayList<Integer> countList = new ArrayList<Integer>();
    FourPlayerCount fpc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fpc = new FourPlayerCount();
        setContentView(R.layout.activity_four_player);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_four_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
        if (id == R.id.player14) {
            newID = R.id.player14;                          //checking which player hit the button
            fpc.incrementP14();

        } else if (id == R.id.player24){
            newID = R.id.player24;
            fpc.incrementP24();
        }
        else if(id == R.id.player34){
            fpc.incrementP34();
            newID = R.id.player34;
        }else {
            fpc.incrementP44();
            newID = R.id.player44;
        }
        dialog(newID);
    }

    private void dialog(int id) {
        String player;
        Integer count;
        if (id == R.id.player14) {
            player = "Player 1";
            count = fpc.getP14();
        } else if (id == R.id.player24){                //retrieving button id
            player = "Player 2";
            count = fpc.getP24();
        }else if(id == R.id.player34){
            player = "Player 3";
            count = fpc.getP34();
        }
        else{
            player = "Player 4";
            count = fpc.getP44();
        }
        String msg = player+" pressed first!\ncount = "+count;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);      //using the dialog box to display the winner
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
        loadFromFile();
        if(countList==null){
            throw new RuntimeException();
        }
        else if (countList.size()==0){                              //load the counts when activity starts
            FourPlayerCount fpc = new FourPlayerCount();
        }
        else {
            fpc.setP14(countList.get(0));
            fpc.setP24(countList.get(1));
            fpc.setP34(countList.get(2));
            fpc.setP44(countList.get(3));
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        super.onPause();
        countList.add(0, fpc.getP14());
        countList.add(1, fpc.getP24());                 //save all the counts when user presses back button to the countList
        countList.add(2, fpc.getP34());
        countList.add(3, fpc.getP44());

        saveInFile();
    }


    private void saveInFile(){

        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(countList,writer);                  //save the countList to the file
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadFromFile() {
        try {                                                       //function to load the file
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html retrieved 2015-09-21
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();

            countList = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            countList = new ArrayList<>();
            //tweets = new ArrayList<Tweet>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}



