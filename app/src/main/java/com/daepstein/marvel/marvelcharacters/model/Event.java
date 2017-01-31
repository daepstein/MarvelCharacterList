package com.daepstein.marvel.marvelcharacters.model;


import java.util.Date;
import java.util.List;

/**
 * Created by Trey Robinson on 2/14/14.
 * Adapted by daepstein on 30/01/17
 */
public class Event {
    public int id;
    public String available;
    public List<Items> items;
    public String description;
    public String resourceURL;
    public Date modified;
    public Date start;
    public Date end;


}