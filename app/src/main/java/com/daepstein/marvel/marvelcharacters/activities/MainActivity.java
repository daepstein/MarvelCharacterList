package com.daepstein.marvel.marvelcharacters.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.daepstein.marvel.marvelcharacters.Interface.RetroInterface;
import com.daepstein.marvel.marvelcharacters.R;
import com.daepstein.marvel.marvelcharacters.display.CharacterDetails;
import com.daepstein.marvel.marvelcharacters.display.CustomExpandableList;
import com.daepstein.marvel.marvelcharacters.display.CustomListViewAdapter;
import com.daepstein.marvel.marvelcharacters.display.ShowDetails;
import com.daepstein.marvel.marvelcharacters.login.Signature;
import com.daepstein.marvel.marvelcharacters.model.CharacterModel;
import com.daepstein.marvel.marvelcharacters.model.ServiceResponse;
import com.daepstein.marvel.marvelcharacters.network.RetroFitFunction;
import com.daepstein.marvel.marvelcharacters.network.SharedPrefs;
import com.daepstein.marvel.marvelcharacters.util.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ListView listView ;
    private int dislayedChar = -1;
    private List<String> characterList;
    private List<CharacterModel> fullListChar;
    private CharacterDetails charDetails;
    private ShowDetails show;
    boolean doubleBackToExitPressedOnce = false;
    private int limit = 100;
    private int offset = 0;
    CustomExpandableList customExpandableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getAllCharacters();
        listView.setOnScrollListener(onScrollListener());
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAffinity();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, Constants.TIME_TO_EXIT);
    }

    /**
     * Used to check if the user is at the end of our list view
     * @return
     */
    private OnScrollListener onScrollListener() {
        return new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int threshold = 1;
                int count = listView.getCount();

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (listView.getLastVisiblePosition() >= count - threshold ) {
                        offset+=limit;
                        getAllCharacters();                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
            }

        };
    }

    /**
     * Initialize list view and out character list
     */
    private void init()
    {
        characterList = new ArrayList<>();
        initCharacterList(characterList);
        fullListChar = new ArrayList<>();
        charDetails = new CharacterDetails();
        show = new ShowDetails(this);
        charDetails.addObserver(show);
        customExpandableList = new CustomExpandableList(this);
    }

    /**
     * Calls Retrofit to retrieve information from Marvel's API
     */
    private void getAllCharacters(){

        RetroInterface restInt = RetroFitFunction.getRetroInterface();
        Signature.getInstance().generateSignature();
        final Call<ServiceResponse<CharacterModel>> call = restInt.listCharactersFull(limit, offset,Signature.getInstance().getTimestamp(),
                Signature.getInstance().getPublicKey(), Signature.getInstance().getHash());

        //Work the response from Marvel's API
        call.enqueue(new Callback<ServiceResponse<CharacterModel>>() {
            @Override
            public void onResponse(Call<ServiceResponse<CharacterModel>> call, Response<ServiceResponse<CharacterModel>>response) {

                if (response.code() == 200) {
                    SharedPrefs sharedPrefs = new SharedPrefs(getBaseContext());
                    sharedPrefs.updateKeys();

                    for (CharacterModel cm : response.body().data.results) {
                        characterList.add(cm.getName());
                    }
                    fullListChar.addAll(response.body().data.results);

                    //Notify that Character list is loaded
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
                else
                {
                    //Received some bad response code and the keys probably dont work
                    backToLogin();
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<CharacterModel>> call, Throwable t) {
                // Log error here since request failed
                logError(t.toString());
                tryAgain();
            }
        });
    }

    /**
     * Our List view, with its custom configuration
     * @param charNames List with the name of all Marvel's character
     */
    private void initCharacterList(List<String> charNames){
        listView = (ListView) findViewById(R.id.listViewCharacters);

        //Remove divisor line between elements
        listView.setDividerHeight(0);

        listView.setEmptyView(findViewById(R.id.txt_loadginChar));

        adapter = new CustomListViewAdapter(MainActivity.this , R.layout.custom_list , charNames );
        listView.setAdapter(adapter);
        listView.clearChoices();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (dislayedChar == position)
                    return;
                dislayedChar = position;
                showFullCard(dislayedChar);
            }

        });
    }

    private void tryAgain()
    {
        getAllCharacters();
    }

    /**
     * Change the selected character when the user tap over a new one
     * @param position The Character position on the listview
     */
    private void showFullCard(int position)
    {
        charDetails.setCm(fullListChar.get(position));
        customExpandableList.setExpandableListDetail(show.getExpandableListDetail());
    }

    private void log(String s)
    {
        Log.d("MainActivityLog", s);
    }

    private void logError(String s)
    {
        Log.e("MainActivityError", s);
    }

    public void backToLogin(){

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.wrong_key_title));
        alertDialog.setMessage(getString(R.string.wrong_key));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent goBack = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(goBack);
                        finish();
                    }
                });
        alertDialog.show();

    }
}
