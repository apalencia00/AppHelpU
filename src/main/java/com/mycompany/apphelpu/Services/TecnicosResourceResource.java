/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mycompany.apphelpu.DAO.TecnicosDAO;
import com.mycompany.apphelpu.Model.Tecnico;

import java.lang.reflect.Type;
import java.util.List;
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

/**
 * REST Web Service
 *
 * @author usuario
 */
@Path("tecnicos")
public class TecnicosResourceResource {

    @Context
    private UriInfo context;
    
    TecnicosDAO tdao  = new TecnicosDAO();

    /**
     * Creates a new instance of TecnicosResourceResource
     */
    public TecnicosResourceResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.apsol_helpdesk.Service.TecnicosResourceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TecnicosResourceResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    
    @GET
    @Path("listar")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        
        public Response detalleServicioBySolicitud( ){
            
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        List<Tecnico> listasunto =  tdao.listarTecnicos();        
        Type ListType = new TypeToken<java.util.LinkedList<Tecnico>>(){}.getType();
        
        String json = gson.toJson(listasunto, ListType);
       
        
                    
            return  Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
        }
    
}
