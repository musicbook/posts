package com.fri.musicbook;

import java.util.ArrayList;
import java.util.List;

public class PostsBean {
    public static List<Post> getAllPosts(){
        return PostsDB.getAllBandPosts();
    }

    public static boolean setBandPosts(String bandId, List<String> newPosts){
        List<String> oldPosts= PostsDB.getBandPosts(bandId);
        if(oldPosts == null) return false;
        oldPosts.addAll(newPosts);
        return PostsDB.setBandPosts(bandId,oldPosts);
    }

    public static List<String> getBandPosts(String bandId,String lt_gt_eq,int no){
        switch (lt_gt_eq){
            case "EQ" : return getBandPosts_engine(PostsDB.getBandPosts(bandId), no,no);
            case "LT" : return getBandPosts_engine(PostsDB.getBandPosts(bandId), 0,no-1);
            case "GT" : return getBandPosts_engine(PostsDB.getBandPosts(bandId), no+1,-1);
            default   : return new ArrayList<String>();
        }
    }

    private static List<String> getBandPosts_engine(List<String> posts,int min, int max){
        List<String> returnPosts = new ArrayList<>();
        int real_max=max;
        if (max == -1){
            real_max=posts.size()-1;
        }
        for(int i=min; i<=real_max; i++){
            try {
                if (posts.get(i) != null) returnPosts.add(posts.get(i));
            }catch (Exception e){}
        }
        return returnPosts;
    }

    public static boolean createBandPost(Post bandpost) {
        if ((bandpost.getBandId() != null && !bandpost.getBandId().equals("")) &&
             PostsDB.getBandPosts(bandpost.getBandId())==null){

            List<Post> bandposts = PostsDB.getAllBandPosts();
            bandpost.setId(bandposts.get(bandposts.size() - 1).getId() + 1);
            PostsDB.addNewBandPost(bandpost);
            return true;
        }
        return false;
    }


    public static boolean deleteBandPost(String bandId){
        return PostsDB.removeBandPost(bandId);
    }


}
