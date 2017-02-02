package com.daepstein.marvel.marvelcharacters.display;

import android.app.Activity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.daepstein.marvel.marvelcharacters.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Example from: http://www.journaldev.com/9942/android-expandablelistview-example-tutorial
 * Adapter by daepstein 02/01/2017
 */


public class CustomExpandableList {

    ExpandableListView expandableListView;
    CustomExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    Activity activity;

    public  CustomExpandableList (Activity a)
    {
        activity = a;
        expandableListDetail = new HashMap<>();
        expandableListView = (ExpandableListView) a.findViewById(R.id.exp_listview);
        expandableListTitle = new ArrayList<String>();
        expandableListAdapter = new CustomExpandableListAdapter(a, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        }

    /**
     * Custom actions for click events. It is not used here
     */
    private void listeners()
    {
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(activity.getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(activity.getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        activity.getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }

    public HashMap<String, List<String>> getExpandableListDetail() {
        return expandableListDetail;
    }

    public void setExpandableListDetail(HashMap<String, List<String>> expandableListParameter) {
        this.expandableListDetail.clear();
        this.expandableListTitle.clear();
        this.expandableListTitle.addAll(expandableListParameter.keySet());
        this.expandableListDetail.putAll(expandableListParameter);
        expandableListAdapter.notifyDataSetChanged();
        collapseAll();
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = expandableListAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            expandableListView.collapseGroup(i);
        }
    }
}
