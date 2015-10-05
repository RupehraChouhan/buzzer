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

/**
 * Created by rupehra on 10/2/15.
 */



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
