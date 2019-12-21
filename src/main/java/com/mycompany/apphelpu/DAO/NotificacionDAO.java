/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.apphelpu.Data.Database;
import com.mycompany.apphelpu.Model.Notificacion;
import com.mycompany.apphelpu.Util.Resultado;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Monitoreo
 */
public class NotificacionDAO {
    
     Database conexion = null;
    PreparedStatement pst = null;
    ResultSet         rs  = null;
    
    Resultado resultado = null;
    
    public java.util.List<Notificacion> estadoNotificacion(){
        
        
        List<Notificacion> listar_notificacion = new LinkedList<>();
        Gson gson = new GsonBuilder().create();
        
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            pst = conexion.getCon().prepareStatement("");
            
            rs = pst.executeQuery();
            
                
            while ( rs.next() ) {
                
                listar_notificacion.add(new Notificacion("", "", "") );
                
            }
            
            conexion.getCon().close();
            return listar_notificacion;
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println(""+ex.getMessage());
        }
            
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
              return null;  
        
        
        
        
    }
    
}
