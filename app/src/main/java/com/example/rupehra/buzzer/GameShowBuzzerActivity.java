package com.example.rupehra.buzzer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

//asks the user to choose the number of players,and takes them to the game mode page


public class GameShowBuzzerActivity extends ActionBarActivity {

    private RadioGroup radioPlayerGroup;
    private RadioButton player2, player3, player4;
    private Button proceedBtn;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_show_buzzer);

        radioPlayerGroup = (RadioGroup) findViewById(R.id.radioPlayer);
        radioPlayerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if (checkedId == R.id.player2) {
                    Intent intent = new Intent(GameShowBuzzerActivity.this, TwoPlayerActivity.class);
                    startActivity(intent);
                } else if (checkedId == R.id.player3) {
                    Intent intent = new Intent(GameShowBuzzerActivity.this, ThreePlayerActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(GameShowBuzzerActivity.this, FourPlayerActivity.class);
                    startActivity(intent);
                }
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_show_buzzer, menu);
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
}
