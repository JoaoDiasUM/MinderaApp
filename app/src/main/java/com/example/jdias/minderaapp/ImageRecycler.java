package com.example.jdias.minderaapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


public class ImageRecycler extends RecyclerView.Adapter<ImageRecycler.ImageHolder> {

    private ArrayList<Bitmap> current_bitmaps;

    public ImageRecycler(ArrayList<Bitmap> bitmaps) {
        this.current_bitmaps = bitmaps;
    }

    @NonNull
    @Override
    public ImageRecycler.ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new ImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageRecycler.ImageHolder holder, int position) {

        holder.imageView.setImageBitmap(current_bitmaps.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return current_bitmaps.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        ImageHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
