package com.fri.musicbook;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@RequestScoped
public class PostsDB {
    @PersistenceContext(unitName = "posts-jpa")
    private EntityManager em;

    private ObjectMapper objectMapper;

    public PostsDB() {
    }

    public boolean setBandPosts(String bandId,List<String> posts){
        Post a=em.find(Post.class,bandId);
        if(a == null) return false;
        a.setBandPosts(posts);
        try {
            beginTx();
            em.merge(a);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            return false;
        }

        return true;
    }

    public List<Post> getAllBandPosts() {
        Query query=em.createNamedQuery("posts.getAll",Post.class);
        List<Post> test = query.getResultList();
        if(test==null) System.out.println("IM EMPTY");
        System.out.println(test);
        return test;
    }

    public List<String> getBandPosts(String bandId){
        Post post = em.find(Post.class,bandId);
        if(post!=null) return post.getBandPosts();
        return null;
    }

    public boolean addNewBandPost(Post post){

        try {
            beginTx();
            em.persist(post);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
            return false;
        }
        return true;
    }

    public boolean removeBandPost(String bandId){
        Post bandPost=em.find(Post.class,bandId);
        if(bandPost!=null) {
            try {
                beginTx();
                em.remove(bandPost);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
                return false;
            }
        }else return false;

        return true;
    }


    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }

}
