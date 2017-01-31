package com.daepstein.marvel.marvelcharacters.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by daepstein on 1/31/2017.
 */

public class DownloadImages {

    private Context context;

    public DownloadImages(Context c)
    {
        context = c;
    }

    public void download(String url, final String fileName) {
        Picasso.with(context).
                load(url).
                into(new Target() {
                         @Override
                         public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                             try {
                                 String root = Environment.getExternalStorageDirectory().toString();
                                 File myDir = new File(root + "/yourDirectory");

                                 if (!myDir.exists()) {
                                     myDir.mkdirs();
                                 }

                                 myDir = new File(myDir, fileName);
                                 FileOutputStream out = new FileOutputStream(myDir);
                                 bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

                                 out.flush();
                                 out.close();
                             } catch (Exception e) {
                                 // some action
                             }
                         }

                         @Override
                         public void onBitmapFailed(Drawable errorDrawable) {
                         }

                         @Override
                         public void onPrepareLoad(Drawable placeHolderDrawable) {
                         }
                     }
                );
    }
}
