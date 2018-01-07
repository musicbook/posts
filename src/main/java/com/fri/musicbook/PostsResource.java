package com.fri.musicbook;

import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.annotation.Metered;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ParamConverter;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("posts/")
@Metered(name = "PostResources")
@Log
@ApplicationScoped
 public class PostsResource {

    @Inject
    private PostsBean postsBean;

    @GET
    public Response getAllBandPosts(){

        List<Post> posts=postsBean.getAllPosts();
        return Response.ok(posts).build();
    }

    @POST
    @Path("bandId/{bandId}")
    public Response setBandPost(List<String> messages, @PathParam("bandId") String bandId){
        if (postsBean.setBandPosts(bandId,messages)){
            return Response.status(Response.Status.CREATED).entity(messages).build();
        }
        return Response.status( Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("bandId/{bandId}/posts/{lt_gt_eq}:{no}")
    public Response getBandPosts(@PathParam("bandId") String bandId, @PathParam("lt_gt_eq") String lt_gt_eq, @PathParam("no") int no){

        return Response.ok(postsBean.getBandPosts(bandId,lt_gt_eq,no)).build();
    }


    @POST
    public Response createBandPost(Post bandpost){
        if(postsBean.createBandPost(bandpost)){
            return Response.status(Response.Status.CREATED).entity(bandpost).build();
        }
        return Response.status( Response.Status.CONFLICT).build();
    }

    @DELETE
    @Path("bandId/{bandId}")
    public Response deleteBandPost(@PathParam("bandId") String bandId){
        if(postsBean.deleteBandPost(bandId)){
            return Response.ok().build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

}
