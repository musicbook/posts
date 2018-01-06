package com.fri.musicbook.health;

import com.fri.musicbook.config.ConfigProperties;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;


@Health
@ApplicationScoped
public class AddAndRemoveBandpostHealthCheck implements HealthCheck {

    Client httpClient = ClientBuilder.newClient();

    private static final Logger LOG = Logger.getLogger(AddAndRemoveBandpostHealthCheck.class.getSimpleName());

    @Inject
    private ConfigProperties configProperties;

    @Override
    public HealthCheckResponse call(){
        if(CreateNewBandPost() && RemoveCreatedBandpost()){
            return HealthCheckResponse.named(AddAndRemoveBandpostHealthCheck.class.getSimpleName()).up().build();
        }
        return HealthCheckResponse.named(AddAndRemoveBandpostHealthCheck.class.getSimpleName()).down().build();

    }

    private boolean CreateNewBandPost(){
        try {
            Response res = httpClient
                    .target(configProperties.getBaseServer()+"/v1/posts")
                    .request().post(Entity.json("{ \"bandId\" : \"-1\"}"));
            if(res.getStatus()== Response.Status.CREATED.getStatusCode()) return true;
            LOG.warning("Get status message code: " + res.getStatus() +" Data: " +res.getEntity());
        }catch (Exception e){
            LOG.severe(e.getMessage());
        }
        return false;
    }

    private boolean RemoveCreatedBandpost(){
        try {
            Response res = httpClient
                    .target(configProperties.getBaseServer()+"/v1/posts/bandId/-1")
                    .request().delete();
            if(res.getStatus()==Response.Status.OK.getStatusCode()) return true;
            LOG.warning("Get status message code: " + res.getStatus() +" Data: " +res.getEntity());
        }catch (Exception e){
            LOG.severe(e.getMessage());
        }
        return false;

    }
}
