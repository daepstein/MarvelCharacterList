package com.daepstein.marvel.marvelcharacters.display;

import android.app.Activity;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.daepstein.marvel.marvelcharacters.R;
import com.daepstein.marvel.marvelcharacters.model.CharacterModel;
import com.daepstein.marvel.marvelcharacters.model.Event;
import com.daepstein.marvel.marvelcharacters.model.Items;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.daepstein.marvel.marvelcharacters.util.Constants.charComics;
import static com.daepstein.marvel.marvelcharacters.util.Constants.charDesc;
import static com.daepstein.marvel.marvelcharacters.util.Constants.charEvent;
import static com.daepstein.marvel.marvelcharacters.util.Constants.charName;
import static com.daepstein.marvel.marvelcharacters.util.Constants.charSeries;
import static com.daepstein.marvel.marvelcharacters.util.Constants.charStories;

/**
 * Created by daepstein on 1/31/2017.
 */

public class ShowDetails extends Activity implements Observer {

    Activity activity;
    CharacterModel cm;
    public HashMap<String, List<String>> expandableListDetail;

    public ShowDetails(Activity a)
    {
        activity = a;
        cm = null;
        expandableListDetail = new HashMap<String, List<String>>();
    }

    private void updateDetails(CharacterModel charModel)
    {
        if (charModel == null)
            return;

        cm = charModel;

        //Just to ensure that 'description' is not blank
        String description = charModel.getDescription();
        if (description.isEmpty())
            description = "Not Available";

        String eventsTitle = charEvent+charModel.getEvents().available;
        String comicsTitle = charComics+charModel.getComics().available;
        String seriesTitle = charSeries+charModel.getSeries().available;
        String storiesTitle = charStories+charModel.getStories().available;

        ((TextView)activity.findViewById(R.id.txt_charName)).setText(charName+charModel.getName());
        ((TextView)activity.findViewById(R.id.txt_charDescr)).setText(charDesc+description);
        ((TextView)activity.findViewById(R.id.txt_charComic)).setText(comicsTitle);
        ((TextView)activity.findViewById(R.id.txt_charEvent)).setText(eventsTitle);
        ((TextView)activity.findViewById(R.id.txt_charSeries)).setText(seriesTitle);
        ((TextView)activity.findViewById(R.id.txt_charStories)).setText(storiesTitle);

        updateExpandableList();
        /*******************************************
         Images are loaded as per request to save data.
         To download all images, check "network.DonwloadImages" and util."AskPermission"
         ****************************************/
        Picasso.with(activity).load(charModel.getThumbnail().path+"."+charModel.getThumbnail().extension)
                .into(((ImageView)activity.findViewById(R.id.img_char)));

    }

    private void updateExpandableList ()
    {
        HashMap<String, List<String>> newDetailList =  new LinkedHashMap<>();;

        String comicsTitle = charComics+cm.getComics().available;
        String eventsTitle = charEvent+cm.getEvents().available;
        String seriesTitle = charSeries+cm.getSeries().available;
        String storiesTitle = charStories+cm.getStories().available;

        newDetailList.clear();
            newDetailList.put(comicsTitle, updateComics());
            newDetailList.put(eventsTitle, updateEvents());
            newDetailList.put(seriesTitle, updateSeries());
            newDetailList.put(storiesTitle, updateStories());

        setExpandableListDetail(newDetailList);

    }

    public void setExpandableListDetail(HashMap<String, List<String>> expandableListDetail) {
        this.expandableListDetail = expandableListDetail;
    }

    public HashMap<String, List<String>> getExpandableListDetail() {
        return expandableListDetail;
    }

    public void update(Observable o, Object arg) {
        updateDetails ((CharacterModel) arg);
    }

    private List<String> updateSeries()
    {
        List<String> expandableListDetailSeries = new ArrayList<>();
        for(Items e: cm.getSeries().items)
            expandableListDetailSeries.add(e.name);
        if (expandableListDetailSeries.isEmpty())
            expandableListDetailSeries.add(activity.getString(R.string.media_not_found));

        return expandableListDetailSeries;
    }

    private  List<String> updateComics()
    {
        List<String> expandableListDetailComics = new ArrayList<>();
        for(Items e: cm.getComics().items)
            expandableListDetailComics.add(e.name);
        if (expandableListDetailComics.isEmpty())
            expandableListDetailComics.add(activity.getString(R.string.media_not_found));

        return expandableListDetailComics;
    }

    private  List<String> updateEvents()
    {
        List<String> expandableListDetailEvents = new ArrayList<>();
        for(Items e: cm.getEvents().items)
            expandableListDetailEvents.add(e.name);
        if (expandableListDetailEvents.isEmpty())
            expandableListDetailEvents.add(activity.getString(R.string.media_not_found));

        return expandableListDetailEvents;
    }

    private  List<String> updateStories()
    {
        List<String> expandableListDetailStories = new ArrayList<>();
        for(Items e: cm.getStories().items)
            expandableListDetailStories.add(e.name);
        if (expandableListDetailStories.isEmpty())
            expandableListDetailStories.add(activity.getString(R.string.media_not_found));

        return expandableListDetailStories;
    }
}
