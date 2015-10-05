
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

/**
 * Created by Rupehra on 2015-10-05.
 */
//class that calculates the min time of all reaction time, last 10 time, last 100 times
    //ran out of time to try this class

public class MinTimeClass {

    private int minOfAll;
    private int minLastTen;
    private int minLast100;

    //sett all the values to -1 since

    public MinTimeClass() {
        minOfAll=-1;
        minLastTen=-1;
        minLast100=-1;
    }

    public int getMinAll()
    {
        return minOfAll;
    }

    public int getMin10() {
        return minLastTen;
    }

    public int getMin100() {
        return minLast100;
    }

    public void setMinLastTen(TimeList timeList){           //calculates the min time of last ten times

        int size = timeList.getTimeListSize();
        if(timeList.getTimeListSize()!=0) {
            int tempmin = (int) timeList.deleteTime(0).getInterval();

            if (size < 10) {

                for (int i = 0; i < size; i++) {
                    if (timeList.deleteTime(i).getInterval() < tempmin) {
                        tempmin = (int) timeList.deleteTime(i).getInterval();
                    }
                }
                minLastTen = tempmin;

            } else {


                int start = size - 10;
                for (int i = start; i < size; i++) {
                    if (timeList.deleteTime(i).getInterval() < tempmin) {
                        tempmin = (int) timeList.deleteTime(i).getInterval();
                    }
                }
                minLastTen = tempmin;
            }
        }
    }

    public void setMinLast100(TimeList timeList) {

        int size = timeList.getTimeListSize();
        if (timeList.getTimeListSize() != 0) {
            int min = (int) timeList.deleteTime(0).getInterval();

            if (size < 100) {

                for (int i = 0; i < size; i++) {
                    if (timeList.deleteTime(i).getInterval() < min) { //if the time removed from list has a higher millis than tempmax
                        min = (int) timeList.deleteTime(i).getInterval();   //replace tempmax's time
                    }
                }
                minLast100 = min;

            } else {


                int start = size - 100;
                for (int i = start; i < size; i++) {
                    if (timeList.deleteTime(i).getInterval() < min) {
                        min = (int) timeList.deleteTime(i).getInterval();
                    }
                }
                minLast100 = min;
            }

        }
    }
    public void setMinOfAll(TimeList timeList) {
        if (timeList.getTimeListSize() != 0) {
            int min = (int) timeList.deleteTime(0).getInterval();
            for (int i = 0; i < timeList.getTimeListSize(); i++) {
                if (timeList.deleteTime(i).getInterval() < min) {
                    min = (int) timeList.deleteTime(i).getInterval();
                }
            }
            minOfAll = min;
        }
    }
}


