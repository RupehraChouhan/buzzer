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

import android.media.Image;
import android.net.sip.SipAudioCall;
import android.net.sip.SipSession;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

//this class is responsible for holding the counts of all the players in a two player mode

public class TwoPlayerCount  {

    protected Integer p12;
    protected Integer p22;

    public TwoPlayerCount(){
        p12 = 0;
        p22 = 0;
    }

    public Integer getP12() {
        return p12;
    }

    public void setP12(Integer p12) {this.p12 = p12;}

    public void setP22(Integer p22) {
        this.p22 = p22;
    }

    public Integer getP22() { return p22; }

    public void addPlayerOneCount() {p12  = p12 +1;}

    public void  addPlayerTwoCount() {p22 = p22 +1;}


}
