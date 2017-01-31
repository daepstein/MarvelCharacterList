package com.daepstein.marvel.marvelcharacters.model;


import java.util.List;


/**
 * Created by Trey Robinson on 2/14/14.
 * Adapted by daepstein on 30/01/17
 */
public class DataContainer <E> {

    public int offset;
    public int limit;
    public int total;
    public int count;
    public List<E> results;

}
