package com.daepstein.marvel.marvelcharacters.login;

/**
 * Created by daepstein on 1/30/2017.
 */

public class Login {


    private static Login instance= null;

    //Keys to be used
    private String publicKey;
    private String privateKey;


    private Login(){
        publicKey = "";
        privateKey = "";
    }

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public LoginPair getKeys()
    {
        return new LoginPair(getPublicKey(), getPrivateKey());
    }

/*
    //No need to control it right now
      private static Object mutex= new Object();
           public static Login getInstance(){
            if(instance==null){
                synchronized (mutex){
                    if(instance==null) instance= new Login();
                }
            }
            return instance;
        }
*/

}
