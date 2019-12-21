/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mycompany.apphelpu.DAO.UsuarioDAO;
import com.mycompany.apphelpu.Model.MenuServicio;
import com.mycompany.apphelpu.Model.Usuario;
import com.mycompany.apphelpu.Util.Resultado;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("usuario")
public class UsuarioResource {

    @Context
    private UriInfo context;
    
    UsuarioDAO udao = new UsuarioDAO();

    /**
     * Creates a new instance of UsuarioResource
     */
    public UsuarioResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.apsol_helpdesk.Service.UsuarioResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UsuarioResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    
    @Path("crear")
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED , MediaType.APPLICATION_XML })
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response crearUsuario(
            
            @FormParam("tipodoc") int tipodoc, 
            @FormParam("documento") String documento, 
            @FormParam("usuario") String usuario,
            @FormParam("nombre") String nombre, 
            @FormParam("apellido") String apellido,
            @FormParam("perfil") int perfil,
            @FormParam("estado") String estado
 
            
            ){
        
        JsonObject json = new JsonObject();
               
        try{
            
                   
           Resultado respuesta =  udao.addUsuario(tipodoc,documento,usuario,nombre,apellido,perfil,estado);
            
            json.addProperty("codigo",respuesta.getCodigo() );
            json.addProperty("resultado",respuesta.getResultado() );
            json.addProperty("estado",respuesta.getEstado() );
            
            
        }catch(Exception e){
            
            e.printStackTrace();
        }
        
        return Response.ok(json.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    @DELETE
    @Path("borrar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response borrarUsuario(@PathParam("id") int id ){
        
        
        try{
            
            if ( id == 0 ){
                Response.serverError().build();
            }
            
            udao.deleteUsuarioById(id);
            
            
        }catch(Exception e){
            
            return Response.serverError().build();
        }
        
        return Response.ok("Usuario Borrado Con Exito!", MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
    @GET
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response listarUsuario(){
        
        
        Gson gson = new Gson();
        
         List<MenuServicio> listar_usuarios = udao.getAllUsuarios();
        
         Type listType = new TypeToken<LinkedList<Usuario>>(){}.getType();

         String json = gson.toJson(listar_usuarios, listType);
        
        return Response.ok(json.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    @GET
    @Path("consultarusuario/{documento}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response consultarUsuario(@PathParam("documento") String documento ){
        
         JsonObject json = new JsonObject();
         Usuario usuario = udao.getUsuarioByCriteria(documento);
         json.addProperty("nombre", usuario.getNombre());
         json.addProperty("apellido", usuario.getSucursal());
        
        return Response.ok(json.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    @PUT
    @Path("actualizar")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response actualizarUsuario(
            

            @FormParam("documento")  String gnr_dcment,
            @FormParam("nombre")     String gnr_nombre,
            @FormParam("apellido")   String gnr_apellido,
            @FormParam("estado")     String estado,
            @FormParam("perfil")     String gnr_tperfi
            
            ){
        
        try{
            
            Usuario usuario = new Usuario();
            usuario.setDocumento(gnr_dcment);
            usuario.setNombre(gnr_nombre);
            usuario.setApellido(gnr_apellido);
            usuario.setEstado(estado);
            usuario.setTipo_perfil(Integer.parseInt(gnr_tperfi));
           
            JsonObject jsonb = new JsonObject();
            
           Resultado resusu = udao.updateUsuarioById(usuario);
           jsonb.addProperty("Usuario Actualizado ", resusu.getCodigo());
            
            return Response.ok(jsonb.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
            
        }catch(Exception e){
             return Response.serverError().build();
        }
        
        
                
    }
    
}
