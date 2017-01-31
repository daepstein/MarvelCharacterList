package com.daepstein.marvel.marvelcharacters.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.daepstein.marvel.marvelcharacters.R;
import com.daepstein.marvel.marvelcharacters.util.Constants;

import static com.daepstein.marvel.marvelcharacters.util.ImageResize.ResizeImage;

public class SplashActivity extends AppCompatActivity {

    // Thread to process splash screen events
    private Thread splashThread;
    public static final String splashLog="SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ((ImageView)findViewById(R.id.img_splash)).setImageDrawable(ResizeImage(R.drawable.splash_image, this));

        // The thread to wait for splash screen events
        splashThread = new Thread() {
            @Override
            public void run() {

                try {

                    // Wait given period of time or exit on touch
                    sleep(Constants.SPLASH_TIMEOUT_SEC);

                } catch (InterruptedException ex) {
                    Log.e(splashLog, "SplashActivity thread interrupted!");
                }

                finish();

                // Open MainActivity
                Intent mainActivityIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(mainActivityIntent);
                finish();
            }
        };

        splashThread.start();
    }

}
