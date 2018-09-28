package com.novumlogic.flightapp.Model;

/**
 * Created by NOVUMLOGIC-2 on 6/1/2017.
 */

public class ChildItemAirline {

    public String option1;
    public String option2;

    public ChildItemAirline(String option1, String option2) {
        this.option1 = option1;
        this.option2 = option2;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }
}
