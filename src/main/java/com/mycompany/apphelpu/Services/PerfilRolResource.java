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
import com.mycompany.apphelpu.DAO.PerfilUsuarioDAO;
import com.mycompany.apphelpu.Model.MenuServicio;
import com.mycompany.apphelpu.Model.PerfilUsuario;
import com.mycompany.apphelpu.Model.SubMenuServicio;
import com.mycompany.apphelpu.Model.Usuario;
import com.mycompany.apphelpu.Util.CFG;

import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
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
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import redis.clients.jedis.Jedis;

/**
 * REST Web Service
 *
 * @author usuario
 */
@Path("perfil_menu")
public class PerfilRolResource {

    @Context
    private UriInfo context;
    
        PerfilUsuarioDAO opcion = new PerfilUsuarioDAO();
        MenuServicioDAO menuserv = new MenuServicioDAO();

    /**
     * Creates a new instance of PerfilRolResource
     */
    public PerfilRolResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.apsol_helpdesk.Service.PerfilRolResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of PerfilRolResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    
    @GET
    @Path("accesousuario/{userid}/{passw}")
    @Consumes( { MediaType.APPLICATION_JSON } )
    @Produces( { MediaType.APPLICATION_JSON } )
    
    public Response loginUsuario(@PathParam("userid") String id, @PathParam("passw") String passw ) throws UnknownHostException{
        
        JsonObject json = new JsonObject();
           
        Jedis jedis = new Jedis("10.35.10.233", 6379);
        String hash_session = CFG.getSHA(id+"#"+passw);
        
        Usuario usuario = opcion.loginUsuario(id, passw); 
        
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();

        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( currentTimestamp );
        
        if ( usuario.getDocumento() != "N/A"  && usuario.getEstado() != "A" ) {
            
            opcion.log_sesion_user_redis(id, id, timeStamp);
            
            // SAVE SESSION ON REDIS WITH JEDIS
            //jedis.sadd("nicknames", hash_session+"|"+id);
            jedis.hset("user#"+id, "idusuario", ""+usuario.getId());
            jedis.hset("user#"+id, "sessionperfil", ""+usuario.getTipo_perfil());
            jedis.hset("user#"+id, "sessionid", hash_session  );
            
                json.addProperty("id", usuario.getId());
                json.addProperty("documento", usuario.getDocumento());
                json.addProperty("usuario",   usuario.getUsuarioacc());
                json.addProperty("nombre",    usuario.getNombre());
                json.addProperty("apellido",  usuario.getApellido());
                json.addProperty("estado",    usuario.getEstado());
                json.addProperty("tipo_perfil",  usuario.getTipo_perfil());
                
                jedis.close();
                jedis.disconnect();

        
        }
        else if( usuario.getDocumento() == "N/A" ) {
            
                json.addProperty("noencontrado", "Documento no Existe");
                json.addProperty("valido",   "Usuario no Valido o no Existe");

        
        }else{
            
                json.addProperty("codigo",         2);
                json.addProperty("descripcion",   "Revisar Conexion a base de Datos");
                json.addProperty("estado",        "E");
            
        }

        return Response.ok(json.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                            
                          .build();
        
        
    }
    
    
    @GET
    @Path("notcifra_accesousuario/{userid}/{passw}")
    @Consumes( { MediaType.APPLICATION_JSON } )
    @Produces( { MediaType.APPLICATION_JSON } )
    
    public Response loginUsuario_nocifrado(@PathParam("userid") String id, @PathParam("passw") String passw ){
        
        JsonObject json = new JsonObject();
        String hash = CFG.getSHA(passw);
        
        System.out.println(""+hash);
        
        Usuario usuario = opcion.loginUsuario(id, hash);      
        
        
        if ( usuario.getDocumento() != "" ) {

            
        json.addProperty("id", usuario.getId());
        json.addProperty("documento", usuario.getDocumento());
        json.addProperty("usuario",   usuario.getUsuarioacc());
        json.addProperty("nombre",    usuario.getNombre());
        json.addProperty("apellido",  usuario.getApellido());
        json.addProperty("estado",    usuario.getEstado());
        json.addProperty("tipo_perfil",  usuario.getTipo_perfil());
        
        }
        if ( usuario.getDocumento() != "N/A" ) {
            
        json.addProperty("noencontrado", "Documento no Existe");
        json.addProperty("usuario",   "Usuario no Valido o no Existe");

        
        }else{
            
        json.addProperty("codigo",         2);
        json.addProperty("descripcion",   "Revisar Conexion a base de Datos");
        json.addProperty("estado",        "E");
            
        }
       
        return Response.ok(json.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
        
    }
    
    
    @GET
    @Path("usuariorecurso/{usera}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    
    public Response cargarPerfilUsuario(@PathParam("usera") int usera ){
        
        if ( usera == 0 )
        {
           return Response.serverError().build();
        }
        
         Gson gson = new Gson();
        
         List<PerfilUsuario> listar_opciones = opcion.getAllPerfilOpcion(usera);
        
         Type listType = new TypeToken<LinkedList<PerfilUsuario>>(){}.getType();

         String json = gson.toJson(listar_opciones, listType);
        
        
        
        return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
    @POST
    @Path("crearmenu")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_XML })
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response regMenuHelpdesk(@FormParam("nombre") String nombre, @FormParam("icono") String icono ){
     
        
         JsonObject json = new JsonObject();
         
        
         MenuServicio reg_menu = opcion.registroMenu(nombre, icono);
      
         json.addProperty("descripcion", reg_menu.getDescripcion());
         json.addProperty("icono", reg_menu.getIcono());
        
        return Response.ok(json.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
    @POST
    @Path("crearsubmenu")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response crearSubMenuhelpdesk( @FormParam("id_menu") int id_menu, @FormParam("nombre") String nombre , @FormParam("acceso") String acceso , @FormParam("icono") String icono ){
        
         JsonObject json = new JsonObject();
         
        
         SubMenuServicio reg_sbmenu = opcion.registroSubMenu(nombre, id_menu, acceso, icono);
         
         
         json.addProperty("descripcion", reg_sbmenu.getDescripcion_sbmenu());
         json.addProperty("acceso", reg_sbmenu.getAcceso());
         json.addProperty("icono", reg_sbmenu.getIcono());
        
        return Response.ok(json.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
}
