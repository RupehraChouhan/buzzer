package com.example.rupehra.buzzer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


//opens the main activity and displays two buttons, one to start the reaction timer and other one is a game mode

public class MainActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void singleUserClicked(View view){
        Intent intent = new Intent(MainActivity.this, Instructions.class);
        //Intent intent = new Intent(MainActivity.this, TimerActivity.class);

        startActivity(intent);
    }

    public void gameShowBuzzerClicked(View view){
        Intent intent = new Intent(MainActivity.this, GameShowBuzzerActivity.class);
        startActivity(intent);
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
        else if(id ==R.id.reaction_stats){
            Intent intent = new Intent(MainActivity.this, StatisticsActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.game_show_stats){
            Intent intent = new Intent(MainActivity.this, DisplayCountActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}
