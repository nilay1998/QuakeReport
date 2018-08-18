package com.example.android.quakereport;

public class Earthquake
{
    private double mMag;
    private String mPlace;
    private String mDate;

    public Earthquake(double mag, String place, String date)
    {
        mMag=mag;
        mPlace=place;
        mDate=date;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmPlace() {
        return mPlace;
    }

    public String getmMag() {
        return ""+mMag;
    }
}
