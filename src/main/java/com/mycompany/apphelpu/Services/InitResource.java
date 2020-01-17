/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Services;

import com.google.gson.JsonObject;
import com.mycompany.apphelpu.DAO.PerfilUsuarioDAO;
import com.mycompany.apphelpu.Model.Usuario;
import com.mycompany.apphelpu.Model.UsuarioRedis;
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
import javax.ws.rs.core.Response;
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
    PerfilUsuarioDAO opcion = new PerfilUsuarioDAO();

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
    
    
    
    @Path("q/{iduser}")
    @GET
    
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJSON( @PathParam("iduser") String iduserv ) {
        
        Usuario usuario = null;
        
        java.util.List<UsuarioRedis> lista_usuarios_sesion = opcion.validarSessionRedis(iduserv);
        
        JsonObject jobject = new JsonObject();
        
        for ( UsuarioRedis user : lista_usuarios_sesion ) {
        
                Jedis jedis = new Jedis("10.35.10.233", 6379);

                Map<String, String> rediscamp = jedis.hgetAll("user#"+user.getUsersystem());

                String iduser         = rediscamp.get("idusuario");
                String sessionperfil  = rediscamp.get("sessionperfil");
                String sessionid      = rediscamp.get("sessionid");
                String fecha          = rediscamp.get("fecha");

                jobject.addProperty("idusuario", iduser);
                jobject.addProperty("sessionid", sessionid);
                jobject.addProperty("sessionperfil",  sessionperfil);
                jobject.addProperty("fecha",   fecha);
                
                jedis.disconnect();
        }     

        lista_usuarios_sesion.clear();
        
        return Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accep, X-Requested-With")
                .build();
        
        
        
    }
    
    @GET
    @Path("close/{idusuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response closeRedisSesion(@PathParam("idusuario") String idusuario ) {
        
        JsonObject jobject = new JsonObject();
        //Jedis jedis = new Jedis("10.35.10.233", 6379);
        
        ///Map<String, String> rediscamp = jedis.hgetAll("user#"+idusuario);
        
        opcion.cerrarSesion(Integer.parseInt(idusuario));
        
        return Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accep, X-Requested-With")
                .build();
        
    }
    
    
}
