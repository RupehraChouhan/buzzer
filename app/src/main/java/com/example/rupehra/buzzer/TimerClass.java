package com.example.rupehra.buzzer;

/**
 * Created by Rupehra on 2015-10-05.
 */

//Class that keeps track of the timer

public class TimerClass {

    private double startTime;
    private double endTime;
    private double interval;


    public TimerClass(){
        startTime= 0;
        endTime=0;
        interval=0;

    }

    public void setStartTime(){
        this.startTime = System.currentTimeMillis();
    }
    public  void setEndTime(){
        this.endTime = System.currentTimeMillis();
    }
    public void setInterval(){
        this.interval = (endTime-startTime);
    }
    public double getInterval(){
        return this.interval;
    }

}
