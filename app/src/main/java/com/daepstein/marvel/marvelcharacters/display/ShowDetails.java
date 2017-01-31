package com.daepstein.marvel.marvelcharacters.display;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.daepstein.marvel.marvelcharacters.R;
import com.daepstein.marvel.marvelcharacters.model.CharacterModel;
import com.squareup.picasso.Picasso;

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

    public ShowDetails(Activity a)
    {
        activity = a;
        cm = null;
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

        ((TextView)activity.findViewById(R.id.txt_charName)).setText(charName+charModel.getName());
        ((TextView)activity.findViewById(R.id.txt_charDescr)).setText(charDesc+description);
        ((TextView)activity.findViewById(R.id.txt_charComic)).setText(charComics+charModel.getComics().available);
        ((TextView)activity.findViewById(R.id.txt_charEvent)).setText(charEvent+charModel.getEvents().available);
        ((TextView)activity.findViewById(R.id.txt_charSeries)).setText(charSeries+charModel.getSeries().available);
        ((TextView)activity.findViewById(R.id.txt_charStories)).setText(charStories+charModel.getStories().available);

        /*******************************************
         Images are loaded as per request to save data.
         To download all images, check "network.DonwloadImages" and util."AskPermission"
         */
        Picasso.with(activity).load(charModel.getThumbnail().path+"."+charModel.getThumbnail().extension)
                .into(((ImageView)activity.findViewById(R.id.img_char)));

    }
    public void update(Observable o, Object arg) {
        updateDetails ((CharacterModel) arg);
    }


}
