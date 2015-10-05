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
 * Created by rupehra on 10/3/15.
 */



//this class is responsible for holding the counts of all the players in a three player mode

public class ThreePlayerCount {

    protected Integer p13;
    protected Integer p23;
    protected Integer p33;

    public ThreePlayerCount(){
        p13 = 0;
        p23 = 0;
        p33 = 0;
    }

    public Integer getP13() {return p13;}

    public Integer getP23() {
        return p23;
    }

    public Integer getP33() {
        return p33;
    }

    public void setP13(Integer p13) {
        this.p13 = p13;
    }

    public void setP23(Integer p23) {
        this.p23 = p23;
    }

    public void setP33(Integer p33) {
        this.p33 = p33;
    }

    public void incrementP13() {p13  = p13 +1;}

    public void  incrementP23() {p23 = p23 +1;}

    public void incrementP33(){ p33 = p33 +1; }

}
