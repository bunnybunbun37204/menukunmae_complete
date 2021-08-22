package com.wfh.menukunmae.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class LoadImage extends AsyncTask<String, Void, Bitmap> {

    private ImageView imageView;

    public LoadImage(ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    public Bitmap doInBackground(String... strings) {
        final String URL = strings[0];
        Bitmap bitmap = null;
        try (InputStream inputStream = new java.net.URL(URL).openStream()) {
            bitmap = BitmapFactory.decodeStream(inputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
