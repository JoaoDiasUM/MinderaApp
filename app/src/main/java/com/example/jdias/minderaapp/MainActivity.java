package com.example.jdias.minderaapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.jdias.minderaapp.AsyncTasks.HttpGetImageDetails;
import com.example.jdias.minderaapp.AsyncTasks.HttpImageSearch;
import com.example.jdias.minderaapp.AsyncTasks.ImageRetrieval;
import com.example.jdias.minderaapp.ImageDetailsPojo.ImageDetailsPojo;
import com.example.jdias.minderaapp.ImageDetailsPojo.Size;
import com.example.jdias.minderaapp.ImageListPojo.ImageListPojo;
import com.example.jdias.minderaapp.ImageListPojo.Photo;
import com.example.jdias.minderaapp.Interface.OnImageTaskCompleted;
import com.example.jdias.minderaapp.Interface.OnTaskCompleted;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements OnTaskCompleted, OnImageTaskCompleted {

    private RecyclerView recyclerView;
    ArrayList<Bitmap> bitmaps = new ArrayList<>();
    Integer max_size = 50;
    Integer results =0;
    Boolean connected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.picture_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusableInTouchMode(false);

        connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;
            }
            else
                connected = false;
        }

        if(connected.equals(true)) {
            HttpImageSearch httpImageSearch = new HttpImageSearch(this, this);
            httpImageSearch.execute("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=f9cc014fa76b098f9e82f1c288379ea1&tags=kitten&page=1&format=json&nojsoncallback=1");
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Network not available", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onTaskCompleted(String result, String code) {

        Gson gson = new Gson();

        switch (code)
        {
            case "1":

                CustomThreadPoolExecutor poolExecutor = new CustomThreadPoolExecutor(4,20, 5, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());

                ImageListPojo imageListPojo = gson.fromJson(result, ImageListPojo.class);

                Photo[] photos = imageListPojo.getPhotos().getPhoto();

                for (Photo photo : photos) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if(!Objects.equals(results, max_size)) {
                            HttpGetImageDetails httpGetImageDetails = new HttpGetImageDetails(this, this);
                            httpGetImageDetails.executeOnExecutor(poolExecutor,photo.getId());
                        }
                    }
                }

                break;

            case "2":

                ImageDetailsPojo imageDetailsPojo = gson.fromJson(result,ImageDetailsPojo.class);

                if(imageDetailsPojo!=null) {

                    Size[] sizes = imageDetailsPojo.getSizes().getSize();
                    String image_url;

                    for (Size size : sizes) {
                        if (size.getLabel().equals("Large Square")) {
                            image_url = size.getSource();
                            ImageRetrieval imageRetrieval = new ImageRetrieval(image_url, this);
                            imageRetrieval.execute();
                        }
                    }

                }

                break;

        }
    }

    @Override
    public void onImageTaskCompleted(Bitmap result) {

        if(result!=null && bitmaps.size()<max_size) {
            bitmaps.add(result);
            results++;
        }

        if(bitmaps.size()==max_size)
        {
           ImageRecycler imageRecycler = new ImageRecycler(bitmaps);
           recyclerView.setAdapter(imageRecycler);
        }

    }

}
