/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mycompany.apphelpu.Data.Database;
import com.mycompany.apphelpu.Facade.IPerfilUsuario;
import com.mycompany.apphelpu.Model.MenuServicio;
import com.mycompany.apphelpu.Model.PerfilUsuario;
import com.mycompany.apphelpu.Model.SubMenuServicio;
import com.mycompany.apphelpu.Model.Usuario;



import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class PerfilUsuarioDAO implements IPerfilUsuario{
    
    Database postconn = null;

    @Override
    public PerfilUsuario addPerfilOpcion(PerfilUsuario perfil) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePerfilOpcionId(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getAllPerfilOpcion(Object usera) {
        
        
        PreparedStatement   pst = null;
        java.sql.ResultSet  rs  = null;
               
        JsonObject jsonob = new JsonObject();
        String json = "";
        List<PerfilUsuario> listar_opciones = new LinkedList<>();
        Gson gson = new GsonBuilder().create();
                
        try{
            
            postconn = new Database();
            postconn.conectar("apalencia", "asd.123*-");
            
            pst = postconn.getCon().prepareStatement("select * from helpdesk_opciones.gnr_menu where fk_usuario = ? order by 1 ASC");
            pst.setInt(1,(Integer) usera);
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_opciones.add(new PerfilUsuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6)));         
            }
            
            rs.close();
            pst.close();
            postconn.getCon().close();
            
            
            return listar_opciones;
        
    }catch(Exception e){
        e.printStackTrace();
    }   
        
        return null;
        
    }

    @Override
    public Usuario loginUsuario(String userio, String pass) {
               
               
        
        Usuario usuario = null;
        String json = "";
        List<PerfilUsuario> listar_opciones = new LinkedList<>();
        Gson gson = new GsonBuilder().create();
                
        try{
            
            postconn = new Database();
            postconn.conectar("apalencia", "asd.123*-");

            PreparedStatement pst = postconn.getCon().prepareStatement("SELECT gnr_idusuario, gnr_tipo_dcment ,gnr_dcment, gnr_usuario,  gnr_nombre, gnr_apellido ,tp.gnr_estado, gnr_tperfil FROM helpdesk_opciones.gnr_usuario, helpdesk_opciones.gnr_rol_perfil tp WHERE gnr_usuario = ? and gnr_passw = ? and gnr_tperfil = gnr_id_rol_perfil");
                
                if ( pst == null ) {
                    
                    usuario = null;
                    
                }else{
            
            pst.setString(1, userio);
            pst.setString(2, pass);
            
                }
      
            java.sql.ResultSet   rs = pst.executeQuery();
            
            
            if ( rs.next() ) {
                
               System.out.println(" res " + rs.getString(3) );
               usuario = new Usuario(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),  rs.getInt(8));
                
            }else{
               usuario = new Usuario("N/A", "Usuario No Existe", "E", "", 0);
            }
            
                
            
            rs.close();
            pst.close();
            postconn.getCon().close();
            return usuario;
            
        }catch( Exception e ){
            
            
            usuario = null;
            e.printStackTrace();
        }
        
        
        return null;
        
    }
    
    public void log_sesion_user_redis(String uuid, String ip, String fecha_act) {
                            
        try{

            postconn = new Database();
            postconn.conectar("apalencia", "asd.123*-");

            PreparedStatement pst = postconn.getCon().prepareStatement("select * from helpdesk_opciones.reg_sesion_usr(?,?,?)");
            pst.setString(1, uuid);
            pst.setString(2, ip);
            pst.setString(3, fecha_act);
            
            pst.execute();
           
            pst.close();
            postconn.getCon().close();
  
        }catch( Exception e ){

            e.printStackTrace();
        }
        
        
     
        
    }
    
    
    public MenuServicio registroMenu(String nombre, String icono){
        
        MenuServicio menu_servicio = null;
        String json = "";
        List<PerfilUsuario> listar_opciones = new LinkedList<>();
        Gson gson = new GsonBuilder().create();
                
        try{

            postconn = new Database();
            postconn.conectar("apalencia", "asd.123*-");

            PreparedStatement pst = postconn.getCon().prepareStatement("INSERT INTO helpdesk_opciones.gnr_menu_servicio( descripcion, icono) VALUES (?, ?)");
              
            pst.setString(1, nombre);
            pst.setString(2, icono);
            
            pst.executeUpdate();
           
                
            menu_servicio = new MenuServicio(nombre, icono, 0, "", "");
            
            
            
            pst.close();
            postconn.getCon().close();
            
            return menu_servicio;
            
            
        }catch( Exception e ){
            
            
            
            e.printStackTrace();
        }
        
        
        return null;
        
        
    }
    
        public SubMenuServicio registroSubMenu(String nombre, int menu , String acceso ,String icono){
        
        SubMenuServicio sub_menu_servicio = null;
        String json = "";
        
        Gson gson = new GsonBuilder().create();
                
        try{

            postconn = new Database();
            postconn.conectar("apalencia", "asd.123*-");

            PreparedStatement pst = postconn.getCon().prepareStatement("INSERT INTO helpdesk_opciones.gnr_submenu_servicio(\n" +
"	gnr_descripcion_submenu_servicio, fk_gnr_fk_menu_servicio, acceso, icono, estado)\n" +
"	VALUES (?, ?, ?, ?, ?);");
              
            pst.setString(1, nombre);
            pst.setInt(2, menu);
            pst.setString(3, acceso);
            pst.setString(4, icono);
            pst.setBoolean(5, Boolean.TRUE);
            
            pst.executeUpdate();
           
                
            
            
            pst.close();
            postconn.getCon().close();
            
            sub_menu_servicio = new SubMenuServicio(nombre, menu, acceso, icono);
            
            return sub_menu_servicio;
            
            
        }catch( Exception e ){
            
            
            
            e.printStackTrace();
        }
        
        
        return null;
        
        
    }
        
       public String obtenerIP() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        return ip.getHostAddress();
    }
    
}
