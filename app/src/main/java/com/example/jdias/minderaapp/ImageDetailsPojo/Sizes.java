package com.example.jdias.minderaapp.ImageDetailsPojo;

/**
 * Created by jdias on 22/07/2019.
 */

public class Sizes
{
    private Size[] size;

    private String canprint;

    private String canblog;

    private String candownload;

    public Size[] getSize ()
    {
        return size;
    }

    public void setSize (Size[] size)
    {
        this.size = size;
    }

    public String getCanprint ()
    {
        return canprint;
    }

    public void setCanprint (String canprint)
    {
        this.canprint = canprint;
    }

    public String getCanblog ()
    {
        return canblog;
    }

    public void setCanblog (String canblog)
    {
        this.canblog = canblog;
    }

    public String getCandownload ()
    {
        return candownload;
    }

    public void setCandownload (String candownload)
    {
        this.candownload = candownload;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [size = "+size+", canprint = "+canprint+", canblog = "+canblog+", candownload = "+candownload+"]";
    }
}