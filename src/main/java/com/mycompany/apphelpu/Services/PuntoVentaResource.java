/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mycompany.apphelpu.DAO.PuntoVentaDAO;
import com.mycompany.apphelpu.Model.Servicio;
import com.mycompany.apphelpu.Model.UbicacionServicio;
import com.mycompany.apphelpu.Util.Resultado;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author mx-linux
 */
@Path("puntoventa")
public class PuntoVentaResource {

    @Context
    private UriInfo context;
    PuntoVentaDAO pvdao = new PuntoVentaDAO();

    /**
     * Creates a new instance of GenericResource
     */
    public PuntoVentaResource() {
    }

 
    
    @POST 
    @Path("crearpv")
    @Consumes( {MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_XML} )
    @Produces( MediaType.APPLICATION_JSON )
    
    public Response  crearPuntoVenta(
            
    @FormParam("punto")            String tipo_urgencia,
    @FormParam("cda")              String cda,
    @FormParam("direccion")        String direccion,
    @FormParam("zona")             String zona,
    @FormParam("zonadesc")         String zonadesc
            
    )
    
    {
        
        
        UbicacionServicio pv = new UbicacionServicio(Integer.parseInt(tipo_urgencia), cda, direccion, zona, zonadesc);
        JsonObject jobject = new JsonObject();
           
            Resultado obtenerinfopunto = pvdao.crearPuntoVenta(pv);
            
            jobject.addProperty("codigo", obtenerinfopunto.getCodigo());
            jobject.addProperty("respuesta", obtenerinfopunto.getResultado());
            jobject.addProperty("estado", obtenerinfopunto.getEstado());
     
            return  Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
        
    }
    
    
    @GET
    @Path("listarservpv/{idpunto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response listarServiciosPuntoVenta(@PathParam("idpunto") String idpunto ){
        
        Gson gson = new Gson();
        
        List<Servicio> listasunto =  pvdao.listServiciosPuntosVenta(idpunto);
        Type listType = new TypeToken<LinkedList<Servicio>>(){}.getType();

        String json = gson.toJson(listasunto, listType);
        
       return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
    
}
