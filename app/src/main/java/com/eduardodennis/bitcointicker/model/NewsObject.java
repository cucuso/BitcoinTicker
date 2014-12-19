package com.eduardodennis.bitcointicker.model;

/**
 * Created by Eddie on 12/15/2014.
 */
public class NewsObject{

    private String title;
    private String link;

    public NewsObject(String title, String link){

        this.title = title;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }


}