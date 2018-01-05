package com.fri.musicbook;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private int id;
    private String bandId;
    private List<String> bandPosts= new ArrayList<>();

    public Post() {
    }

    public Post(int id, String bandId) {
        this.id = id;
        this.bandId = bandId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }

    public List<String> getBandPosts() {
        return bandPosts;
    }

    public void addBandPost(String post){
        bandPosts.add(post);
    }

    public void setBandPosts(List<String> bandPosts) {
        this.bandPosts = bandPosts;
    }
}
