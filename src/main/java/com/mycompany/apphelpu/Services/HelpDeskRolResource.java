/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mycompany.apphelpu.DAO.MenuServicioDAO;
import com.mycompany.apphelpu.Model.MenuPermisos;
import com.mycompany.apphelpu.Model.MenuServicio;
import com.mycompany.apphelpu.Model.SubMenuServicio;

import java.lang.reflect.Type;
import java.util.LinkedList;
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


@Path("helpdesk")
public class HelpDeskRolResource {

    @Context
    private UriInfo context;
    
    MenuServicioDAO opcion = new MenuServicioDAO();

    /**
     * Creates a new instance of HelpDeskRolResource
     */
    public HelpDeskRolResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.apsol_helpdesk.Service.HelpDeskRolResource
     * @param usera
     * @return an instance of java.lang.String
     */
    
    @GET
        @Path("cargaropciones")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    
    public Response cargarTodosMenu(){
        
        Gson gson = new Gson();
        
         List<MenuServicio> listar_opciones = opcion.getAllMenus();
        
         Type listType = new TypeToken<LinkedList<MenuServicio>>(){}.getType();

         String json = gson.toJson(listar_opciones, listType);
       
        return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
        
    }
    
    
       
    @GET
    @Path("cargarsubopcionestodas")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    
    public Response cargarTodosSubMenu(){
        
        Gson gson = new Gson();
        
         List<SubMenuServicio> listar_subopciones = opcion.getAllMenuServicio_Submenu();
        
         Type listType = new TypeToken<LinkedList<MenuServicio>>(){}.getType();

         String json = gson.toJson(listar_subopciones, listType);
       
        return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
        
    }
    
    
    @GET
    @Path("cargaperfilhelpdesk/{usera}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    
    public Response carga_Perfil_Usuario_HelpDesk(@PathParam("usera") int usera ){
        
        if ( usera == 0 )
        {
           return Response.serverError().build();
        }
        
         Gson gson = new Gson();
        
         List<MenuServicio> listar_opciones = opcion.getAllMenuServicio(usera);
        
         Type listType = new TypeToken<LinkedList<MenuServicio>>(){}.getType();

         String json = gson.toJson(listar_opciones, listType);
       
        return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
    @GET
    @Path("cargaperfilsbhelpdesk/{usera}/{idmenu}")
    @Consumes( { MediaType.APPLICATION_JSON } )
    @Produces( { MediaType.APPLICATION_JSON } )
    
    public Response carga_Perfil_Submenu_Helpdesk(@PathParam("usera") int usera, @PathParam("idmenu") int idmenu ){
        
        Gson gson = new Gson();
        
        List<SubMenuServicio> listar_menu = opcion.getAllMenuServicio_Submenu(usera,idmenu);
         
        Type listType = new TypeToken<LinkedList<SubMenuServicio>>(){}.getType();
        String json = gson.toJson(listar_menu, listType);
        
        return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
        
    }
    
    @POST
    @Path("registromenu/{nombre}/{icono}")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_XML } )
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response registroMenu(@FormParam("nombre") String nombre, @FormParam("icono") String icono  ){
        
        opcion.addMenuServicio(new MenuServicio(nombre, "", 0, "", icono));
        
        return Response.ok("", MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
    @POST
    @Path("registrosbmenu/{descripcion}/{acceso}/{idmenu}/{icono}")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_XML } )
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response registroSbMenu(@FormParam("descripcion") String descripcion ,@FormParam("acceso") String acceso ,@FormParam("idmenu") String idmenu, @FormParam("icono") String icono  ){
        
        opcion.registroSbMenu(new SubMenuServicio(descripcion, Integer.parseInt(idmenu), acceso, icono));
        
        return Response.ok("", MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
    @POST
    @Path("permisos")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_XML } )
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response registroPermisos(@FormParam("idmenu") String idmenu, @FormParam("idsubmenu") String idsubmenu, @FormParam("documento") String documento ) {
            
        MenuPermisos menu = opcion.registroPermisos(new MenuPermisos(0, Integer.parseInt(idmenu), Integer.parseInt(idsubmenu), "A", null), documento);
            
        JsonObject json = new JsonObject();
        json.addProperty("mensaje", "Registro Permisos");
        json.addProperty("documento", documento );
        
        return Response.ok(json.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
        
    }
    
    @GET
    @Path("submenu_permiso/{menu}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response filtroSubMenu_Permisos(@PathParam("menu") int menu ){
        
        Gson gson = new Gson();
        
        List<SubMenuServicio> listar_menu = opcion.getSubMenu_By_Menu(menu);
         
        Type listType = new TypeToken<LinkedList<SubMenuServicio>>(){}.getType();
        String json = gson.toJson(listar_menu, listType);
        
        return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
}
