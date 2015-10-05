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
