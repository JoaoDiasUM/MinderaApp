package com.example.jdias.minderaapp.AsyncTasks;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import com.example.jdias.minderaapp.Interface.OnImageTaskCompleted;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class ImageRetrieval extends AsyncTask<Void, Void, Bitmap> {

    private String image_url;
    private OnImageTaskCompleted callback;

    public ImageRetrieval(String url, OnImageTaskCompleted callback) {
        this.image_url = url;
        this.callback = callback;
    }



    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(image_url);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = calculateInSampleSize(options);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream(),null,options);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        callback.onImageTaskCompleted(bitmap);

    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > 1000 || width > 1000) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= 150
                    && (halfWidth / inSampleSize) >= 150) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


}
