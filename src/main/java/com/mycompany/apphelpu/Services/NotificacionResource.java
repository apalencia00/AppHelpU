/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.apphelpu.Model.Notificacion;

import com.pusher.rest.Pusher;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Monitoreo
 */
@Path("api/notificacion")
public class NotificacionResource {

    @Context
    private UriInfo context;
    
    private String app_id    = "884445";
    private String key       = "6ed81cdac3e242e7c999";
    private String secret    = "7acfcd20028a60221f88";
    private String cluster   = "us2";
    

    /**
     * Creates a new instance of NotificacionResource
     */
    public NotificacionResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.apsol_helpdesk.Service.NotificacionResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of NotificacionResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    
    @GET
    @Path("alertas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response notificacionAplicacionDesktop() 
    {
        
        Pusher pusher = new Pusher(app_id, key, secret);
           pusher.setCluster(cluster);
           
           Gson gson = new Gson();
           Map<String,String> map = new HashMap();
           map.clear();
           
           List<Notificacion> listasignados =  null;
           Type listType = new TypeToken<LinkedList<Notificacion>>(){}.getType();

           String json = gson.toJson(listasignados, listType);
        
        pusher.trigger("my-channel", "my-event", json);
            
        
       return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
}
