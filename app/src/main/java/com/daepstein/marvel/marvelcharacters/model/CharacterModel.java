package com.daepstein.marvel.marvelcharacters.model;

/**
 * Created by daepstein on 1/30/2017.
 */

public class CharacterModel {


    private int id;
    private String description;
    private String name;
    private String modified;
    private Comics comics;
    private Series series;
    private Event events;
    private Stories stories;
    private OrderBy orderBy;
    private Thumbnail thumbnail;


    public CharacterModel(){

        orderBy = OrderBy.Default;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modifiedSince) {
        this.modified = modifiedSince;
    }

    public Comics getComics(){
        return this.comics;
    }

    public void setComics(Comics comics) {
        this.comics = comics;
    }

    public Event getEvents() {
        return events;
    }

    public void setEvents(Event events) {
        this.events = events;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Stories getStories() {
        return stories;
    }

    public void setStories(Stories stories) {
        this.stories = stories;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public enum OrderBy {
        Default("")
        ,Name ("name")
        ,NameDescending("-name")
        ,Date("date")
        ,DateDescending("-date");

        private String value;

        OrderBy(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }
    }

}
