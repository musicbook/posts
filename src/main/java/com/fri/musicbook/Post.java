package com.fri.musicbook;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity(name = "posts")
@NamedQueries(value =
        {
                @NamedQuery(name = "posts.getAll", query = "SELECT o FROM posts o"),
                @NamedQuery(name = "posts.getBandPost", query = "SELECT o FROM posts o WHERE o.bandId = :bandId")
        })
@UuidGenerator(name = "idGenerator")
public class Post {
    @Id
    @Column(name = "bandId")
    private String bandId;

    @ElementCollection
    @Column(name = "bandPosts")
    private List<String> bandPosts= new ArrayList<>();

    public Post() {
    }

    public Post(String bandId) {
        this.bandId = bandId;
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
