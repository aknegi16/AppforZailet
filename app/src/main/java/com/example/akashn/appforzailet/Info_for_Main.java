package com.example.akashn.appforzailet;

import java.net.URL;

/**
 * Created by akashn on 07-06-2017.
 */

public class Info_for_Main {

static int counter=0;

   private String Title ;
    private String tick="false";

    public String getTick() {
        return tick;
    }

    public void setTick(String tick) {
        this.tick = tick;
        if(tick.equals("true"))
        counter++;
        if(tick.equals("false"))
            counter--;

    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    private String Picture;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
