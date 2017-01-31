package com.daepstein.marvel.marvelcharacters.model;
/**
 * Created by Trey Robinson on 2/13/14.
 * Adapter by daepstein on 01/30/2017
 */
public class ServiceResponse<E>{
    public int code;
    public String status;
    public String etag;
    private String copyright;
    public DataContainer<E> data;

}
