package com.example.rupehra.buzzer;

/**
 * Created by rupehra on 10/3/15.
 */



//this class is responsible for holding the counts of all the players in a four player mode

public class FourPlayerCount {

    protected Integer p14;
    protected Integer p24;
    protected Integer p34;
    protected Integer p44;

    public FourPlayerCount(){
        p14 = 0;
        p24 = 0;
        p34 = 0;
        p44 = 0;
    }

    public Integer getP14() {
        return p14;
    }

    public Integer getP24() {
        return p24;
    }

    public Integer getP34() {
        return p34;
    }

    public Integer getP44() {
        return p44;
    }

    public void incrementP14() {p14  = p14 +1;}

    public void  incrementP24() {p24 = p24 +1;}

    public void incrementP34(){ p34 = p34 +1; }

    public void incrementP44(){ p44 = p44 +1; }

    public void setP14(Integer p14) {this.p14 = p14;}

    public void setP24(Integer p24) {this.p24 = p24;}

    public void setP34(Integer p34) {this.p34 = p34;}

    public void setP44(Integer p44) {this.p44 = p44;}
}
