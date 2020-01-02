/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Services;

import com.mycompany.apphelpu.Model.Usuario;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import redis.clients.jedis.Jedis;

/**
 * REST Web Service
 *
 * @author apalencia
 */
@Path("init")
public class InitResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of InitResource
     */
    public InitResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.apsol_helpdesk.Service.InitResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of InitResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    
    
    @Path("q/{idusuario}")
    @GET
    
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getJSON(@PathParam("idusuario") String idusuario ) {
        
        Usuario usuario = null;
        
        Jedis jedis = new Jedis("10.35.10.233", 6379);
        Map<String, String> rediscamp = jedis.hgetAll("user#"+idusuario);
        
        String idusuarioredis = rediscamp.get("idusuario");
        String perfil = rediscamp.get("sessionperfil");
        String session = rediscamp.get("sessionid");
        
        usuario = new Usuario(idusuarioredis, session, perfil, "", 0);
        
        jedis.disconnect();
        return usuario;
        
        
        
    }
    
    
}
