package com.example.jdias.minderaapp.AsyncTasks;

import android.os.AsyncTask;

import com.example.jdias.minderaapp.Interface.OnTaskCompleted;
import com.example.jdias.minderaapp.MainActivity;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpImageSearch extends AsyncTask<String, Void, String> {

    private OkHttpClient okHttpClient = new OkHttpClient();
    private OnTaskCompleted listener;
    private WeakReference<MainActivity> activityReference;

    public HttpImageSearch(MainActivity context, OnTaskCompleted listener) {
        activityReference = new WeakReference<>(context);
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    /*    okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(30, TimeUnit.MINUTES) // write timeout
                .readTimeout(30, TimeUnit.MINUTES) // read timeout
                .build();*/
    }

    @Override
    protected String doInBackground(String... params) {

        Request.Builder builder  = new Request.Builder();
        builder.url(params[0]);
        Request request = builder.build();

        try{
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        MainActivity activity = activityReference.get();
        if (activity == null || activity.isFinishing()) return;

        listener.onTaskCompleted(s,"1");
    }
}
