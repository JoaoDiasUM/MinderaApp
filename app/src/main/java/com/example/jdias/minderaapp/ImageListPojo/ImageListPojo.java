package com.example.jdias.minderaapp.ImageListPojo;



public class ImageListPojo
{
    private String stat;

    private Photos photos;

    public String getStat ()
    {
        return stat;
    }

    public void setStat (String stat)
    {
        this.stat = stat;
    }

    public Photos getPhotos ()
    {
        return photos;
    }

    public void setPhotos (Photos photos)
    {
        this.photos = photos;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [stat = "+stat+", photos = "+photos+"]";
    }
}