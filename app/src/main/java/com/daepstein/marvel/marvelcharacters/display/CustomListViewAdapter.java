package com.daepstein.marvel.marvelcharacters.display;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.daepstein.marvel.marvelcharacters.R;

import java.util.List;

/**
 * Created by daepstein on 9/7/2016.
 */
public class CustomListViewAdapter extends ArrayAdapter {

    private Context mContext;
    private int id;
    private List<String> items ;

    public CustomListViewAdapter(Context context, int textViewResourceId , List<String> list )
    {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list ;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            mView = vi.inflate(id, null);
        }

        TextView text = (TextView) mView.findViewById(R.id.textViewList);

        if(items.get(position) != null )
            text.setText(items.get(position));

        return mView;
    }

}