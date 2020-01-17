/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.DAO;

import com.google.gson.JsonObject;
import com.mycompany.apphelpu.Data.Database;
import com.mycompany.apphelpu.Facade.IMenuServicio;
import com.mycompany.apphelpu.Model.MenuPermisos;
import com.mycompany.apphelpu.Model.MenuServicio;
import com.mycompany.apphelpu.Model.OpcionesUsuario;
import com.mycompany.apphelpu.Model.SubMenuServicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class MenuServicioDAO implements IMenuServicio {
    
    Database postconn = new Database();
    
        Database conexion = null;
    PreparedStatement pst = null;
    ResultSet         rs  = null;

    @Override
    public MenuServicio addMenuServicio(MenuServicio perfil) {
        
        PreparedStatement   pst = null;
        java.sql.ResultSet  rs  = null;
               
        JsonObject jsonob = new JsonObject();
    
        try{
                
            postconn.conectar("apalencia", "asd.123*-");
            
            pst = conexion.getCon().prepareStatement("select * from helpdesk_opciones.crear_menu(?,?)");
            
           
            pst.setString(1, perfil.getDescripcion());
            pst.setString(2, perfil.getIcono());
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                    
                 
                return new MenuServicio(perfil.getDescripcion(), "", 0, "", perfil.getIcono());
                
            }
            
            
        }catch(Exception e){
            
        }
        
        return null;
        
    }
    
    public SubMenuServicio registroSbMenu(SubMenuServicio submenu){
        
        try{
        
        postconn.conectar("apalencia", "asd.123*-");
            
            pst = conexion.getCon().prepareStatement("select * from helpdesk_opciones.crear_submenu(?,?,?,?)");
            pst.setString(1, submenu.getDescripcion_sbmenu());
            pst.setInt(2, submenu.getFk_menu_servicio());
            pst.setString(3, submenu.getAcceso());
            pst.setString(4, submenu.getIcono());
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                    
                 
                return new SubMenuServicio(submenu.getDescripcion_sbmenu(), submenu.getFk_menu_servicio(), submenu.getAcceso(), submenu.getIcono());
                
            }
            
            
        }catch(Exception e){
            
        }
        
        return null;
        
    }

    @Override
    public void deleteMenuServicioId(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MenuServicio updateMenuServicioById(Object param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<MenuServicio> getAllMenus(){
        
        
        PreparedStatement   pst = null;
        java.sql.ResultSet  rs  = null;
               
        JsonObject jsonob = new JsonObject();
        List<MenuServicio> listar_menu_serv = new LinkedList<>();
        
                
        try{
                
            postconn.conectar("apalencia", "asd.123*-");
            
            pst = postconn.getCon().prepareStatement("select ms.*\n" +
"from \n" +
"helpdesk_opciones.gnr_menu_servicio ms\n" +
"\n" +
"GROUp BY ms.gnr_idmenu_servicio\n" +
"ORDER BY ms.gnr_idmenu_servicio ASC" +
"");
           
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_menu_serv.add(new MenuServicio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6)));         
            }
            
            rs.close();
            pst.close();
            postconn.getCon().close();
            
            
            return listar_menu_serv;
        
    }catch(Exception e){
            try {
                postconn.getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        e.printStackTrace();
    }   
        
        return null;  
            
        
        
    }

    @Override
    public List getAllMenuServicio(int user) {
        
        
        PreparedStatement   pst = null;
        java.sql.ResultSet  rs  = null;
               
        JsonObject jsonob = new JsonObject();
        List<MenuServicio> listar_menu_serv = new LinkedList<>();
        
                
        try{
                
            postconn.conectar("apalencia", "asd.123*-");
            
            pst = postconn.getCon().prepareStatement("select ms.*\n" +
"from helpdesk_opciones.gnr_detalle_menu_permisos dtp,\n" +
"helpdesk_opciones.gnr_menu_servicio ms,\n" +
"helpdesk_opciones.gnr_usuario us\n" +
"where dtp.fk_usuario = us.gnr_idusuario \n" +
"and dtp.fk_menu_servicio = ms.gnr_idmenu_servicio \n" +
"and us.gnr_idusuario = ?\n" +
"and us.gnr_idusuario !=999999999\n" +
"GROUp BY ms.gnr_idmenu_servicio\n" +
"ORDER BY ms.gnr_idmenu_servicio ASC\n" +
"");
            pst.setInt(1,user);
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_menu_serv.add(new MenuServicio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5),rs.getString(6)));         
            }
            
            rs.close();
            pst.close();
            postconn.getCon().close();
            
            
            return listar_menu_serv;
        
    }catch(Exception e){
            try {
                postconn.getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        e.printStackTrace();
    }   
        
        return null;  
        
        
    }

    @Override
    public List getAllMenuServicio_Submenu(int user, int idmenu) {
        
        PreparedStatement   pst = null;
        java.sql.ResultSet  rs  = null;
               
        JsonObject jsonob = new JsonObject();
        List<SubMenuServicio> listar_sub_menu_serv = new LinkedList<>();
        
                
        try{ 
            
            postconn.conectar("apalencia", "asd.123*-");
            
            pst = postconn.getCon().prepareStatement("select smbs.* \n" +
"from helpdesk_opciones.gnr_detalle_menu_permisos dtp , \n" +
"helpdesk_opciones.gnr_menu_servicio ms,\n" +
"helpdesk_opciones.gnr_submenu_servicio smbs,\n" +
"helpdesk_opciones.gnr_usuario us\n" +
"\n" +
"where dtp.fk_usuario = us.gnr_idusuario \n" +
"and dtp.fk_menu_servicio = ms.gnr_idmenu_servicio \n" +
"and dtp.fk_sub_menu_servicio = smbs.gnr_idsubmenu_servicio  \n" +
"\n" +
"and us.gnr_idusuario = ? and dtp.fk_menu_servicio = ? and dtp.estado = 'A' AND smbs.estado = true "
                    + "ORDER BY gnr_idsubmenu_servicio ASC ");
            pst.setInt(1, user);
            pst.setInt(2, idmenu);
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_sub_menu_serv.add(new SubMenuServicio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));         
            }
            
            rs.close();
            pst.close();
            postconn.getCon().close();
            
           
            return listar_sub_menu_serv;
        
    }catch(Exception e){
            try {
                postconn.getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        e.printStackTrace();
    }   
        
        return null;  
        
    }
    
    
    public List<SubMenuServicio> getSubMenu_By_Menu(int menu){
            
        
        PreparedStatement   pst = null;
        java.sql.ResultSet  rs  = null;
               
        JsonObject jsonob = new JsonObject();
        List<SubMenuServicio> listar_sub_menu_serv = new LinkedList<>();
        
                
        try{ 
            
            postconn.conectar("apalencia", "asd.123*-");
            
            pst = postconn.getCon().prepareStatement("select smbs.* \n" +
"from helpdesk_opciones.gnr_submenu_servicio smbs where sbms.fk_gnr_fk_menu_servicio = ? \n" +
"\n" +
"                   ORDER BY gnr_idsubmenu_servicio ASC");

            
            pst.setInt(1, menu);
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_sub_menu_serv.add(new SubMenuServicio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5) ));         
            }
            
            rs.close();
            pst.close();
            postconn.getCon().close();
            
           
            return listar_sub_menu_serv;
        
    }catch(Exception e){
            try {
                postconn.getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        e.printStackTrace();
    }   
        
        return null;  
        
        
    }
    
    //////////////////////////////////////////////////////
    
    
     public List<SubMenuServicio> getAllMenuServicio_Submenu() {
        
        PreparedStatement   pst = null;
        java.sql.ResultSet  rs  = null;
               
        JsonObject jsonob = new JsonObject();
        List<SubMenuServicio> listar_sub_menu_serv = new LinkedList<>();
        
                
        try{ 
            
            postconn.conectar("apalencia", "asd.123*-");
            
            pst = postconn.getCon().prepareStatement("select smbs.* \n" +
"from helpdesk_opciones.gnr_submenu_servicio smbs\n" +
"\n" +
"                   ORDER BY gnr_idsubmenu_servicio ASC");

            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_sub_menu_serv.add(new SubMenuServicio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5) ));         
            }
            
            rs.close();
            pst.close();
            postconn.getCon().close();
            
           
            return listar_sub_menu_serv;
        
    }catch(Exception e){
            try {
                postconn.getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        e.printStackTrace();
    }   
        
        return null;  
        
    }
     
     
     public MenuPermisos registroPermisos(MenuPermisos permiso, String documento){
         
         PreparedStatement   pst = null;
         java.sql.ResultSet  rs  = null;
         
         try{
        
        postconn.conectar("apalencia", "asd.123*-");
            
            pst = postconn.getCon().prepareStatement("select * from helpdesk_opciones.permisos(?,?,?)");
            pst.setInt(1, permiso.getMenu_servicio());
            pst.setInt(2, permiso.getSubmenu_servicio());
            pst.setString(3, documento);
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                    
                 
                return new MenuPermisos(0, 0, 0, "A", null);
                
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
         
     }
     
     
     
     public List<OpcionesUsuario> opcionesUsuario(String cedula) {
        
        PreparedStatement   pst = null;
        java.sql.ResultSet  rs  = null;
               
        JsonObject jsonob = new JsonObject();
        List<OpcionesUsuario> listar_sub_menu_serv = new LinkedList<>();
        
                
        try{ 
            
            postconn.conectar("apalencia", "asd.123*-");
            
            pst = postconn.getCon().prepareStatement("select * from helpdesk_opciones.opciones_usuario(?)");
            pst.setString(1, cedula);
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_sub_menu_serv.add( new OpcionesUsuario(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)) );         
            }
            
            rs.close();
            pst.close();
            postconn.getCon().close();
            
           
            return listar_sub_menu_serv;
        
    }catch(Exception e){
            try {
                postconn.getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        e.printStackTrace();
    }   
        
        return null;  
        
    }
       
    
}
