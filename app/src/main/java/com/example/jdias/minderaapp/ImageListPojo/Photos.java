package com.example.jdias.minderaapp.ImageListPojo;


public class Photos
{
    private String perpage;

    private String total;

    private String pages;

    private Photo[] photo;

    private String page;

    public String getPerpage ()
    {
        return perpage;
    }

    public void setPerpage (String perpage)
    {
        this.perpage = perpage;
    }

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getPages ()
    {
        return pages;
    }

    public void setPages (String pages)
    {
        this.pages = pages;
    }

    public Photo[] getPhoto ()
    {
        return photo;
    }

    public void setPhoto (Photo[] photo)
    {
        this.photo = photo;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [perpage = "+perpage+", total = "+total+", pages = "+pages+", photo = "+photo+", page = "+page+"]";
    }
}

