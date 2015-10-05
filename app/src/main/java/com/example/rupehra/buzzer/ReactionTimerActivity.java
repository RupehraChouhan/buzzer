package com.example.rupehra.buzzer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

//timer class that starts the timer, notifiies the user to click, complains when the user clicks too early adn restarts the timer


public class ReactionTimerActivity extends ActionBarActivity {


    private static final String FILENAME = "times.sav";
    private boolean isPressed = false;
    private boolean istimerRunning;
    private Button btn;
    private TimeList timeList = new TimeList();
    public TimerClass timer;
    private CountDownTimer countdowntimer;
    private double limit = (double) (Math.random() * 1991) + 10; //suggested by Nicole Lovas and from http://stackoverflow.com/questions/7961788/math-random-explained 2015-09-27


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);

        btn = (Button) findViewById(R.id.tap);
        countdowntimer = new CDTimer((long) limit, 100); //http://androidbite.blogspot.ca/2012/11/android-count-down-timer-example.html
        countdowntimer.start();
        istimerRunning = true;
    }

    public void setTimer() {
        istimerRunning = !istimerRunning;
    } //sets the timer to running or not running

    public void onClick(View view) {                                    //performs the checks and takes actions accordingly
        if ((istimerRunning == true) && (isPressed == false)) {
            countdowntimer.cancel();
            istimerRunning = false;
            Intent intent = new Intent(this, ClickedEarlyActivity.class);
            startActivity(intent);
        } else if ((istimerRunning == false) && (isPressed == false)) {
            timer.setEndTime();
            timer.setInterval();
            loadFromFile();
            timeList.addTime(timer);
            saveInFile();
            btn.setText("Time taken:  " + (int) timer.getInterval() + "ms"); //problem solved with Stack Overflow, User William Morrison, 2015-09-28
            isPressed = true;
        } else {
            Intent intent = new Intent(ReactionTimerActivity.this, RestartReactionActivity.class);
            startActivity(intent);
        }

    }

    class CDTimer extends CountDownTimer {
        public CDTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            setTimer();
            btn.setText("Click Now!");
            timer = new TimerClass();
            timer.setStartTime();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_timer, menu);
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

    //Following two functions are from the lab exercise of CMPUT 301, Spet 28, 2015
    //this is used to load the data in a file
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));


            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html retrieved 2015-09-21
            Type listType = new TypeToken<TimeList>() {
            }.getType();

            timeList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            timeList = new TimeList();
            //tweets = new ArrayList<Tweet>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //this function is used from the lab session of CMPUT 301
    //this is used to store the data in a file
    private void saveInFile(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(timeList,writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

