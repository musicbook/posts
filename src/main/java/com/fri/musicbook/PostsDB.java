package com.fri.musicbook;

import java.util.ArrayList;
import java.util.List;

public class PostsDB {
    private static List<Post> bandPostDB = new ArrayList<Post>(){{
        add(new Post(1,"1"));
        add(new Post(2,"2"));
    }};

    public static boolean setBandPosts(String bandId,List<String> posts){
        for(Post bandPost : bandPostDB){
            if(bandPost.getBandId().equals(bandId)){
                bandPost.setBandPosts(posts);
                return true;
            }
        }
        return false;
    }

    public static List<Post> getAllBandPosts() {
        return bandPostDB;
    }

    public static List<String> getBandPosts(String bandId){
        for(Post post : bandPostDB){
            if(post.getBandId().equals(bandId)){
                return post.getBandPosts();
            }
        }
        return null;
    }

    public static boolean addBandPost(String bandId,String post){
        for(Post bandPost : bandPostDB){
            if(bandPost.getBandId().equals(bandId)){
                bandPost.addBandPost(post);
                return true;
            }
        }
        return false;
    }

    public static void addNewBandPost(Post post){
        bandPostDB.add(post);
    }

    public static boolean removeBandPost(String bandId){
        for(Post bandPost : bandPostDB){
            if(bandPost.getBandId().equals(bandId)) {
                bandPostDB.remove(bandPost);
                return true;
            }
        }
        return false;
    }


}
