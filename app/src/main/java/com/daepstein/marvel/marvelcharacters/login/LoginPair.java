package com.daepstein.marvel.marvelcharacters.login;

/**
 * Created by daepstein on 1/30/2017.
 */

public class LoginPair {

    private final String publicKey, privateKey;

    public LoginPair(String pub, String priv)
    {
        publicKey = pub;
        privateKey = priv;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}
