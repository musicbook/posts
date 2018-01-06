package com.fri.musicbook;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ParamConverter;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("posts/")
public class PostsResource {

    //to get travis started
    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getAllBandPosts(){
        List<Post> posts=PostsBean.getAllPosts();
        return Response.ok(posts).build();
    }

    @POST
    @Path("bandId/{bandId}")
    public Response setBandPost(List<String> messages, @PathParam("bandId") String bandId){
        if (PostsBean.setBandPosts(bandId,messages)){
            return Response.status(Response.Status.CREATED).entity(messages).build();
        }
        return Response.status( Response.Status.CONFLICT).build();
    }

    @GET
    @Path("bandId/{bandId}/posts/{lt_gt_eq}:{no}")
    public Response getBandPosts(@PathParam("bandId") String bandId, @PathParam("lt_gt_eq") String lt_gt_eq, @PathParam("no") int no){

        return Response.ok(PostsBean.getBandPosts(bandId,lt_gt_eq,no)).build();
    }


    @POST
    public Response createBandPost(Post bandpost){
        if(PostsBean.createBandPost(bandpost)){
            return Response.status(Response.Status.CREATED).entity(bandpost).build();
        }
        return Response.status( Response.Status.CONFLICT).build();
    }

    @DELETE
    @Path("bandId/{bandId}")
    public Response deleteBandPost(@PathParam("bandId") String bandId){
        if(PostsBean.deleteBandPost(bandId)){
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

}
