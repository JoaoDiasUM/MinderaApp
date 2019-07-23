package com.example.jdias.minderaapp.ImageDetailsPojo;

/**
 * Created by jdias on 22/07/2019.
 */

public class ImageDetailsPojo
{
    private String stat;

    private Sizes sizes;

    public String getStat ()
    {
        return stat;
    }

    public void setStat (String stat)
    {
        this.stat = stat;
    }

    public Sizes getSizes ()
    {
        return sizes;
    }

    public void setSizes (Sizes sizes)
    {
        this.sizes = sizes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [stat = "+stat+", sizes = "+sizes+"]";
    }
}