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
