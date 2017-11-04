package com.forkthecode.moviesprovider;

/**
 * Created by ralph on 04/11/17.
 */

public class Movie {

    private String title;
    private String descripion;

    public Movie(String title, String descripion) {
        this.title = title;
        this.descripion = descripion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }
}
