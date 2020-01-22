/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Services;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mycompany.apphelpu.DAO.ServicioDAO;
import com.mycompany.apphelpu.DAO.UsuarioDAO;
import com.mycompany.apphelpu.Model.AsignarServicio;
import com.mycompany.apphelpu.Model.Asunto;
import com.mycompany.apphelpu.Model.Servicio;
import com.mycompany.apphelpu.Model.UbicacionServicio;
import com.mycompany.apphelpu.Model.Usuario;
import com.mycompany.apphelpu.Model.Visita;
import com.mycompany.apphelpu.Util.FILES;
import com.mycompany.apphelpu.Util.Resultado;

import com.pusher.rest.Pusher;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * REST Web Service
 *
 * @author usuario
 */
@Path("servicio")
public class ServicioResource {

    @Context
    private UriInfo context;
    ServicioDAO sdao  = new ServicioDAO();
    Servicio servicio = new Servicio();
    UsuarioDAO udao   = new UsuarioDAO();
    Resultado result = null;
    
    private String app_id    = "648736";
    private String key       = "2087d84e920911468266";
    private String secret    = "771d8907662a040ac1bb";
    private String cluster   = "ap1";
    private int solicitud  ;
    
    /** The path to the folder where we want to store the uploaded files */
	private static final String UPLOAD_FOLDER = "/home/aplicaciones/";
        
        FILES myfile = new FILES();

    
    JsonObject jobject = new JsonObject();
    Gson gson = new Gson();
    


    public ServicioResource() {
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
    

     
    
    @GET
    @Path("personabydato/{dato}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response buscarPersona(
            
            @PathParam("dato") String dato
            
    ){
        
        Usuario user =  udao.getUsuarioByCriteria(dato);
        

        jobject.addProperty("solicitante", user.getNombre());
        jobject.addProperty("sucursal", user.getSucursal());
        jobject.addProperty("direccion", user.getDireccion());
        jobject.addProperty("punto", user.getPunto_venta());
        
        return Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
        
    }
    
    
    
    @GET
    @Path("cargaasunto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response cargarAsunto() {
        
        
        List<Asunto> listasunto =  sdao.listAsuntos();
        Type listType           =  new TypeToken<LinkedList<Asunto>>(){}.getType();

        String json = gson.toJson(listasunto, listType);
        
       return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
       
    }
    
    
    @POST
    @Path("crear")
    @Consumes( { MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_XML }  )
    @Produces(  MediaType.APPLICATION_JSON )

    public Response registrarServicio(
            
            @FormParam("num_servicio") String num_servicio,
            @FormParam("solicitante")  String tipo_solicitante,
            @FormParam("cedula")       String cedula,
            @FormParam("direccion")    String direccion,
            @FormParam("sucursal")     String sucursal,
            @FormParam("recepcion")    String tipo_recepcion,
            @FormParam("asunto")       String tipo_asunto,
            @FormParam("punto")        String punto,
            @FormParam("descripcion")  String descripcion,
            @FormParam("fechaser")     String fechaser,
            @FormParam("imagen")       String imagen,
            @FormParam("usuario")      String usuario,
            @FormParam("estado")       String estado,
            @FormParam("idepunto")     String idepunto
            ) {
        
                    // Gets client IP address
            
            
            Pusher pusher = new Pusher(app_id, key, secret);
            pusher.setCluster(cluster);
             
            Map<String,String> map = new HashMap();
            map.clear();

        
            int res = sdao.lastServicio();

            servicio.setNum_servicio(num_servicio+""+res);
            servicio.setFk_tipo_solicitante(Integer.parseInt(tipo_solicitante));
            servicio.setIdentificacion_solictante(cedula);
            servicio.setDireccion_servicio(direccion);
            servicio.setSucursal(sucursal);
            servicio.setTipo_recepcion(Integer.parseInt(tipo_recepcion));
            servicio.setTipo_asunto(Integer.parseInt(tipo_asunto));
            servicio.setPunto_movil_fijo(punto);
            servicio.setDescripcion(descripcion);
            servicio.setFechaser(null);
            servicio.setImagen(imagen);
            servicio.setFk_usuario(Integer.parseInt(usuario));
            servicio.setEstado(estado);
            servicio.setIde_punto(Integer.parseInt(idepunto));
        
        
            
            result = sdao.addServicio(servicio);

            pusher.trigger("new-service", "my-new", Collections.singletonMap("message", "Servicio "+num_servicio+""+res+ "Creado con exito"));

            pusher = null;
            jobject.addProperty("codigo", result.getCodigo());
            jobject.addProperty("respuesta", result.getResultado());
            jobject.addProperty("estado", result.getEstado());
            return Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                              .header("Access-Control-Allow-Origin", "*")
                              .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                              .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")

                              .build();
        
              
        
    }
    
    
    @POST
    @Path("asignar")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response asignarServicio(
            
    @FormParam("urgencia")         String tipo_urgencia,
    @FormParam("tecnico")          String tecnico,
    @FormParam("tipo_servicio")    String tipo_servicio,
    @FormParam("obs")              String obs,
    @FormParam("fecha_apertura")   String fecha_apertura,
    @FormParam("fecha_recepcion")  String fecha_recepcion,
    @FormParam("numservicio")      String num_servicio,
    @FormParam("usuario")          String usuario,
    @FormParam("tecniconuevo")     String tecniconuevo
     
    ){
        
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        java.util.Date fasig           = new java.util.Date();
        //String fecha_asignacion = df.format(fasig);
        java.sql.Date fecha_asig = new java.sql.Date(fasig.getTime());
        System.out.println(""+tipo_urgencia);
        System.out.println(""+tecnico);
        System.out.println(""+tipo_servicio);
        System.out.println(""+obs);
        System.out.println(""+fecha_apertura);
        System.out.println(""+fecha_recepcion);
        System.out.println(""+num_servicio);
        System.out.println(""+usuario);
               
  
        AsignarServicio asignarserv = 
                new AsignarServicio(Integer.parseInt(tipo_urgencia), tecnico, obs, fecha_apertura, fecha_recepcion, ""+fecha_asig, num_servicio , Integer.parseInt(usuario), Integer.parseInt(tipo_servicio), tecniconuevo);
        
        result = sdao.asignarServicio(asignarserv);
        
        Pusher pusher = new Pusher(app_id, key, secret);
        pusher.setCluster(cluster);
              
        Map<String,String> map = new HashMap();
        map.clear();

        

        jobject.addProperty("codigo", result.getCodigo());
        jobject.addProperty("respuesta", result.getResultado());
        jobject.addProperty("estado", result.getEstado());
        
            
        pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", "Servicio asignado con exito"));
        
        return Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    @POST
    @Path("agregarnota")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    
    public Response  agregarObservacionTecnica(@FormParam("num_servicio") String num_servicio ,@FormParam("obs") String obs ) {
        
        solicitud = sdao.obtenerIdServicio(num_servicio);
        
        sdao.agregaObsevacionTecnica(solicitud, obs);
        jobject.addProperty("solicitante", "");
        jobject.addProperty("usuario", "");
        jobject.addProperty("operacion", "Observacion agregada Con Exito");
        
        return Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Accep, X-Requested-With")
                .build();
        
    }
    
    @GET
    @Path("listar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response listarServicio() {
        

        List<Servicio> listartodoservicios =  sdao.listServicios();
        Type listType = new TypeToken<LinkedList<Servicio>>(){}.getType();

        String json = gson.toJson(listartodoservicios, listType);
        
       return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
    
    @GET
    @Path("listarbycriterio/{oper}/{dato}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response listarServicioByCriterio( @PathParam("oper") int oper, @PathParam("dato") String dato ) {
        

        List<Servicio> listasunto =  sdao.listServiciosByCriteria(oper,dato);
        Type listType = new TypeToken<LinkedList<Servicio>>(){}.getType();

        String json = gson.toJson(listasunto, listType);
        
       return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
    
    
    
    
    @GET
    @Path("listar_servicio_movil/{usuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response listar_Servicio_Movil(@PathParam("usuario") String usuario ) {
        
        
           Pusher pusher = new Pusher(app_id, key, secret);
           pusher.setCluster(cluster);
           
           Map<String,String> map = new HashMap();
           map.clear();
            
            List<Servicio> listasignados =  sdao.listAsignados(usuario);
            Type listType = new TypeToken<LinkedList<Servicio>>(){}.getType();

            String json = gson.toJson(listasignados, listType);
                                    
            pusher.trigger("my-channel", "my-event", json);
            
        
       return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
    @GET
    @Path("consultar_servicio/{servicio}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response consultarServicioByNumero(@PathParam("servicio") String servicio){
        
        Servicio serv = sdao.getInfoServicio(servicio);
        
            jobject.addProperty("solicitud",   serv.getNum_servicio());
            jobject.addProperty("asunto",      serv.getAsunto() );
            jobject.addProperty("diregccion",  serv.getDireccion_servicio());
            jobject.addProperty("descripcion", serv.getDescripcion());

                return  Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
        
    }
    
    
    
    @GET
    @Path("listar_servicio_admin/{usuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response listar_Servicio_Admin() {
        
        
           Pusher pusher = new Pusher(app_id, key, secret);
           pusher.setCluster(cluster);
           
           Map<String,String> map = new HashMap();
           map.clear();
            
            List<Servicio> listasignados =  sdao.listAsignados_Admin();
            Type listType = new TypeToken<LinkedList<Servicio>>(){}.getType();

            String json = gson.toJson(listasignados, listType);
                                    
            pusher.trigger("my-channel", "my-event", json);
            
        
       return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
    }
    
    
   
    
    @GET
    @Path("guardar_atencion_servicio/{servicio}/{obs}/{estado}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response guardarAtencionServicio(@PathParam("servicio") int servicio, @PathParam("obs") String obs, @PathParam("estado") int estado ){
        
        Servicio serv = sdao.getServicio(servicio);
        
            jobject.addProperty("solicitud", serv.getNum_servicio());
            jobject.addProperty("asunto", serv.getAsunto() );
            jobject.addProperty("descripcion", serv.getDescripcion());

                return  Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
        
    }
    
    @GET
    @Path("localizarservicio/{idtecnico}/{idpunto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response localizarServicio(@PathParam("idtecnico") int idtecnico,@PathParam("idpunto") int punto){
            
           Map<String,String> map = new HashMap();
           map.clear();
            
            List<UbicacionServicio> listasignados =  sdao.localizarServicios(idtecnico,punto);
            Type listType = new TypeToken<LinkedList<UbicacionServicio>>(){}.getType();

            String json = gson.toJson(listasignados, listType);
                                    
          
        
       return Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
        
        
    }
    
    
   
        
        @GET
        @Path("notifica_listar/{estado}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        
        public Response listar_servicios_registrados_pusher(@PathParam("estado") String estado ){
            
           Pusher pusher = new Pusher(app_id, key, secret);
           pusher.setCluster(cluster);
           
           Map<String,String> map = new HashMap();
           map.clear();
            
            List<Servicio> listasunto =  sdao.listServicios_All_criteria(estado);
            Type listType = new TypeToken<LinkedList<Servicio>>(){}.getType();

            String json = gson.toJson(listasunto, listType);
                                    
            pusher.trigger("list-service", "my-assignation", json);
  
                return  Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
   
            
        }
        
        @GET
        @Path("notifica_listar_seguridad/{estado}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        
        public Response listar_servicios_registrados_pusher_seguridad(@PathParam("estado") String estado ){
            
          
            List<Servicio> listasunto =  sdao.listServicios_All_criteria_seguridad(estado);
            Type listType = new TypeToken<LinkedList<Servicio>>(){}.getType();

            String json = gson.toJson(listasunto, listType);
           
                return  Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
   
            
        }
        
        
        
        @GET
        @Path("detalle/{servicio}")
        @Consumes( MediaType.APPLICATION_JSON  )
        @Produces( MediaType.APPLICATION_JSON  )
        
        public Response detalleServicioBySolicitud(@PathParam("servicio") String servicio ){

        List listasunto =  sdao.listServiciosByCriteria(servicio); // 
        
        jobject.addProperty("documento",        listasunto.get(0).toString());
        jobject.addProperty("servicio",         listasunto.get(1).toString());
        jobject.addProperty("punto_mvfijo",     listasunto.get(2).toString());
        jobject.addProperty("descripcion",      listasunto.get(3).toString());
        jobject.addProperty("direccion",        listasunto.get(4).toString());
        jobject.addProperty("estado",           listasunto.get(5).toString());
        jobject.addProperty("fecha",            listasunto.get(6).toString());
        jobject.addProperty("id_asunto",        Integer.parseInt(listasunto.get(7).toString()));
        jobject.addProperty("asunto",           listasunto.get(8).toString());
       
        
                    
            return  Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
        }
        
        
        
        
        @GET
        @Path("getpuntoventa/{servicio}")
        @Consumes( MediaType.APPLICATION_JSON  )
        @Produces( MediaType.APPLICATION_JSON  )
        
        public Response getpuntoventaByOS(@PathParam("servicio") String servicio ){

        String punto =  sdao.getPuntoVenta(servicio); // 55 n 82 21 2 piso
        
     
            return  Response.ok(punto, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
        }
        
        
        @GET
        @Path("ultimo")
        @Consumes( MediaType.APPLICATION_JSON  )
        @Produces( MediaType.APPLICATION_JSON  )
        
        public Response getUltimoServicio(){
            
        java.util.ArrayList servicio =  sdao.getUltimoServicio(); // 
        jobject.addProperty("idservicio", servicio.get(0).toString());
        jobject.addProperty("servicio"  , servicio.get(1).toString());
        
     
            return  Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
        }
        
        
        
        @POST
        @Path("cerrar_servicio")
        @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
        @Produces(MediaType.APPLICATION_JSON)
        
        public Response cerrarServicioTecnico(
                
                @FormParam("servicio") String servicio, 
                @FormParam("usuario") String usuario, 
                @FormParam("descripcion") String descripcion, 
                @FormParam("estado_serv") String estado_serv, 
                @FormParam("pendiente") String pendiente, 
                @FormParam("inventario") String inventario, 
                @FormParam("imei") String imei, 
                @FormParam("sim") String sim, 
                @FormParam("operador") String operador ) throws SQLException {
            
                System.out.println(""+servicio);
                System.out.println(""+usuario);
                System.out.println(""+descripcion);
                System.out.println(""+estado_serv);
                System.out.println(""+pendiente);
                System.out.println(""+inventario);
                System.out.println(""+imei);
                System.out.println(""+sim);
            
                Resultado cerrar_servicio =  sdao.cerrarServicio(servicio, Integer.parseInt(usuario), descripcion,estado_serv, pendiente, Integer.parseInt(inventario), imei,sim, Integer.parseInt(operador)); // 55 n 82 21 2 piso
                jobject.addProperty("codigo", cerrar_servicio.getCodigo());
                jobject.addProperty("estado", cerrar_servicio.getEstado());
                jobject.addProperty("resultado", cerrar_servicio.getResultado());
            
                return  Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
            
        }
        
        @GET
        @Path("pvbydato/{idpv}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        
        public Response consultarPuntoVenta(@PathParam("idpv") String idpv ){

            Servicio obtenerinfopunto = sdao.consultar_PuntoVenta(Integer.parseInt(idpv));
                
            if ( obtenerinfopunto != null ){ 
            
            jobject.addProperty("direccion", obtenerinfopunto.getNum_servicio());
            jobject.addProperty("zona",      obtenerinfopunto.getAsunto());
            jobject.addProperty("cda",       obtenerinfopunto.getDireccion_servicio());
            jobject.addProperty("zonahr",    obtenerinfopunto.getDescripcion());
            
            }
            else{
                
            jobject.addProperty("codigo"   ,      2);
            jobject.addProperty("respuesta",      "Error punto no existe");
            jobject.addProperty("estado"   ,      "I");
                
            }
            
            
            
            return  Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
            }
            
                    
        
        
        
        @GET
        @Path("controlacceso/q1/{idservicio}/q2/{codigoqr}/verifica")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        
        public Response controlAccesoVisita( @PathParam("idservicio") String idservicio , @PathParam("codigoqr") String codigoqr ) throws SQLException{
            
            Pusher pusher = new Pusher(app_id, key, secret);
            pusher.setCluster(cluster);
            
            
            if ( idservicio.equals(codigoqr) ) {

            Resultado resultado = sdao.genera_controlAcceso(idservicio,codigoqr);
            jobject.addProperty("codigo",      resultado.getCodigo());
            jobject.addProperty("descripcion", resultado.getResultado());
            jobject.addProperty("estado",      "success");
            
            }else{
            jobject.addProperty("codigo",      2);
            jobject.addProperty("descripcion", "Error al leer QR");
            jobject.addProperty("estado",      "error");
            }
            
            pusher.trigger("response-access", "event-response", jobject.toString());
         
            
            return  Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
            
        }
        
        @GET
        @Path("validarvisita/{numserv}/{tecnico}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        
        public Response validarVisitaTecnica(@PathParam("numserv") String numserv, @PathParam("tecnico") String tecnico  ) throws SQLException{
  
            Resultado resultado = sdao.genera_controlAcceso(tecnico,"");
            jobject.addProperty("codigo", 1);
         
            
            return  Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
            
            
        }
        
      
        
        
        @GET
        @Path("export/detalle/{estado}")
        @Consumes({ MediaType.APPLICATION_JSON })
        @Produces("application/vnd.ms-excel")
        
        public Response exportaServicioExcelDetalle(@PathParam("estado") String estado ) throws IOException, InvalidFormatException{

            Workbook milibro = new XSSFWorkbook();          
            Sheet mihoja = milibro.createSheet("Servicios");

            List<Servicio> lista_estados_servicios =  sdao.listServiciosByCriteria(2, estado);
            
            // Obtener la cantidad de filas de la consulta
            
            int rowCount = 0;
                
            for ( Servicio servicio : lista_estados_servicios ) {
                    
                Row fila = mihoja.createRow(++rowCount);
                            
                for ( int j = 0; j <= fila.getLastCellNum(); j ++ ) {
                            
                    Cell celda = fila.createCell(j);
                    celda.setCellValue(lista_estados_servicios.get(j).getDescripcion());
                    
                }
                
            }
            
            String directory = System.getProperty("user.home");

            String fileLocation = directory + "/temp.xls";
            File file = new File(fileLocation);
            
            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            milibro.write(outputStream);
            milibro.close();
            outputStream.flush();
            return  Response.ok((Object)file)
                                
                            .header("Access-Control-Allow-Origin", "*")
                            .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                            .header("Content-Disposition","attachment; filename=export-excel"+System.currentTimeMillis()+".xls")
                            .build();

            
            
        
            
        }
  
        

        @GET
        @Path("validaringresotecnico/{servicio}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        
        public Response validarIngreso(@PathParam("servicio") String servicio ) throws SQLException {
                           

            Visita visita = sdao.cargarDatosDeVisita(servicio);
            
            if ( visita != null ) {
             
            jobject.addProperty("servicio", visita.getNum_servicio());
            jobject.addProperty("fecha", visita.getFecha_apertura());
            jobject.addProperty("sucursal", visita.getSucursal());
            jobject.addProperty("direccion", visita.getDireccion());
            jobject.addProperty("observacion", visita.getObs());
            jobject.addProperty("identificacion", visita.getIdentificacion_tecnico());
            jobject.addProperty("nombre_tecnico", visita.getNombre_tecnico());
            jobject.addProperty("asunto", visita.getAsunto());
            
            }else{
                jobject.addProperty("codigo", 1);
                jobject.addProperty("error", "Evento de Aplicacion");
                jobject.addProperty("observacion", "Al parecer este servicio ya no esta asignado");
            }
            
           
            return Response.ok(jobject.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
        
        }
        
        @GET
        @Path("listar_fecha/{fechai}/{fechaf}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        
        public Response listarPorFechas(@PathParam("fechai") String fechai, @PathParam("fechaf") String fechaf ) {
            
            System.out.println(""+fechai);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            java.sql.Date fechaini = new java.sql.Date(Long.parseLong(fechai)); 
            java.sql.Date fechafin = new java.sql.Date(Long.parseLong(fechaf)); 
            System.out.println(""+fechaini);
            
            List<Servicio> lista_servicios = sdao.listarServiciosPorFecha(fechaini, fechafin);
            
            Type listType = new TypeToken<LinkedList<Servicio>>(){}.getType();

            String json = gson.toJson(lista_servicios, listType);
            
            
     
            return  Response.ok(json, MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
        }
        
        @POST
        @Path("enviar_autorizacion")
        @Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_XML })
        @Produces(MediaType.APPLICATION_JSON)
        
        public Response enviarAutorizacion(){
            
            
           JsonObject object = new JsonObject();
           Pusher pusher = new Pusher(app_id, key, secret);
           pusher.setCluster(cluster);
           
           object.addProperty("codigo", 1);
           object.addProperty("respuesta", "autorizado");
           pusher.trigger("my-authorization", "my-event", object.toString());
            
        
           return Response.ok(object.toString(), MediaType.APPLICATION_JSON)
                          .header("Access-Control-Allow-Origin", "*")
                          .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
                          .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")
                          
                          .build();
            
        }
        
//        @POST
//        @Path("subida")
//        @Consumes({MediaType.MULTIPART_FORM_DATA })
//                
//        public Response subirArchivo(
//                        @FormDataParam("file") InputStream uploadedInputStream,
//			@FormDataParam("file") FormDataContentDisposition fileDetail
//        )
//                
//                
//                
//        {
//                
//            // check if all form parameters are provided
//		if (uploadedInputStream == null || fileDetail == null)
//			return Response.status(400).entity("Invalid form data").build();
//		// create our destination folder, if it not exists
//		try {
//			myfile.createFolderIfNotExists(UPLOAD_FOLDER);
//		} catch (SecurityException se) {
//			return Response.status(500)
//					.entity("Can not create destination folder on server")
//					.build();
//		}
//		String uploadedFileLocation = UPLOAD_FOLDER + fileDetail.getFileName();  
//		try {
//			myfile.saveToFile(uploadedInputStream, uploadedFileLocation);
//		} catch (IOException e) {
//			return Response.status(500).entity("Can not save file").build();
//		}
//		return Response.status(200)
//				.entity("File saved to " + uploadedFileLocation).build();
//
//            
//        }
        
    }
    

