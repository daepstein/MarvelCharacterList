package com.daepstein.marvel.marvelcharacters.login;

import com.daepstein.marvel.marvelcharacters.util.HashUtil;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by daepstein on 1/30/2017.
 */

public class Signature {

    private static Signature instance= null;

    //Keys to be used
    private String publicKey;
    private String timestamp;
    private String hash;
    private Calendar calendar;

    private Signature(){
        publicKey = "";
        timestamp = "";
        hash = "";
        calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    }

    public static Signature getInstance() {
        if (instance == null) {
            instance = new Signature();
        }
        return instance;
    }

    public void generateSignature ()
    {
        calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        timestamp = String.valueOf(calendar.getTimeInMillis() / 1000L);
        publicKey = Login.getInstance().getPublicKey();
        hash = HashUtil.md5(
                timestamp+
                        Login.getInstance().getPrivateKey() +
                        Login.getInstance().getPublicKey());
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }

    public Calendar getCalendar() {
        return calendar;
    }
}
