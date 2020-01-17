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
import com.mycompany.apphelpu.Util.Resultado;

import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
 * @author usuario
 */
@Path("tecnicos")
public class TecnicosResource {

    @Context
    private UriInfo context;
    
    TecnicosDAO tdao  = new TecnicosDAO();
    Resultado resultado = null;

    /**
     * Creates a new instance of TecnicosResourceResource
     */
    public TecnicosResource() {
    }


    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    
    @POST
    @Path("reg_tecnico")
    @Consumes( {MediaType.APPLICATION_FORM_URLENCODED , MediaType.APPLICATION_XML } )
    @Produces(  MediaType.APPLICATION_JSON             )
    
    public Response registroTecnicos(
            @FormParam("tpide")    String tipo_ide,
            @FormParam("doc")      String doc,
            @FormParam("nombre")   String nombre,
            @FormParam("apellido") String apellido,
            @FormParam("celular")  String celular,
            @FormParam("personal") String personal,
            @FormParam("ext")      String ext,
            @FormParam("tipo_cargo") String tipo_cargo
            
    ) {
        
        System.out.println(""+tipo_ide);
        System.out.println(""+doc);
        System.out.println(""+nombre);
        System.out.println(""+apellido);
        System.out.println(""+celular);
        System.out.println(""+personal);
        System.out.println(""+ext);
        System.out.println(""+tipo_cargo);
        
        
        JsonObject json = new JsonObject();  
        resultado = tdao.crearTecnico(new Tecnico(Integer.parseInt(tipo_ide), doc, nombre, apellido, celular, personal, ext, Integer.parseInt(tipo_cargo)));
        
        json.addProperty("codigo",    resultado.getCodigo());
        json.addProperty("respuesta", resultado.getResultado());
        json.addProperty("estado",    resultado.getEstado());
        
        return  Response.ok(json.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
    @GET
    @Path("listar/{serv_asig}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        
        public Response detalleServicioBySolicitud( @PathParam("serv_asig") String serv_asig ){
            
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        List<Tecnico> listasunto =  tdao.listarTecnicos(serv_asig);        
        Type ListType = new TypeToken<java.util.LinkedList<Tecnico>>(){}.getType();
        
        String json = gson.toJson(listasunto, ListType);
       
        
                    
            return  Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
        }
        
        
        @GET
        @Path("listarnuevo")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        
        public Response listarTEcnicos(  ){
            
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        List<Tecnico> listasunto =  tdao.listarTecnicosNuevos();        
        Type ListType = new TypeToken<java.util.LinkedList<Tecnico>>(){}.getType();
        
        String json = gson.toJson(listasunto, ListType);

            return  Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
        }
    
    
}
