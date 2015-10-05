package com.example.rupehra.buzzer;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Rupehra on 2015-10-05.
 */

//Class that maintains all the times in a list


public class TimeList {

    private ArrayList<TimerClass> timesList;

    public TimeList(){
        this.timesList = new ArrayList<TimerClass>();
    }

    public void addTime(TimerClass time){
        timesList.add(time);
    }

    public Collection getTimeList(){
        return timesList;
    }

    public int getTimeListSize(){
        return timesList.size();
    }

    public void clearTimeList(){
        timesList.clear();
    }

    public TimerClass deleteTime(int index){
        return timesList.get(index);
    }

}
