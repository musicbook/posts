package com.fri.musicbook;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class PostsBean {
    @Inject
    private PostsDB postsDB;

    public PostsBean() {
    }

    public List<Post> getAllPosts(){
        return postsDB.getAllBandPosts();
    }

    public boolean setBandPosts(String bandId, List<String> newPosts){
        List<String> oldPosts= postsDB.getBandPosts(bandId);
        if(oldPosts == null) return false;
        oldPosts.addAll(newPosts);
        return postsDB.setBandPosts(bandId,oldPosts);
    }

    public List<String> getBandPosts(String bandId,String lt_gt_eq,int no){
        switch (lt_gt_eq){
            case "EQ" : return getBandPosts_engine(postsDB.getBandPosts(bandId), no,no);
            case "LT" : return getBandPosts_engine(postsDB.getBandPosts(bandId), 0,no-1);
            case "GT" : return getBandPosts_engine(postsDB.getBandPosts(bandId), no+1,-1);
            default   : return new ArrayList<String>();
        }
    }

    private List<String> getBandPosts_engine(List<String> posts,int min, int max){
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

    public boolean createBandPost(Post bandpost) {
        if ((bandpost.getBandId() != null && !bandpost.getBandId().equals("")) &&
                postsDB.getBandPosts(bandpost.getBandId())==null){
            postsDB.addNewBandPost(bandpost);
            return true;
        }
        return false;
    }


    public boolean deleteBandPost(String bandId){
        return postsDB.removeBandPost(bandId);
    }


}
