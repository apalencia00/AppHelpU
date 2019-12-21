/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.DAO;


import com.mycompany.apphelpu.Data.Database;
import com.mycompany.apphelpu.Model.Servicio;
import com.mycompany.apphelpu.Model.UbicacionServicio;
import com.mycompany.apphelpu.Util.Resultado;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mx-linux
 */
public class PuntoVentaDAO {
    
    Database conexion = null;
    PreparedStatement pst = null;
    ResultSet         rs  = null;
    
    Resultado resultado = null;
    
     public Resultado crearPuntoVenta(UbicacionServicio ub){
            
         
          try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("");
            
            pst.setInt(1,    ub.getPunto());
            pst.setString(2, ub.getNombre());
            pst.setString(3, ub.getDireccion());
            pst.setString(4, ub.getRecurso());
            pst.setString(5, ub.getLatitud());
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                return new Resultado(rs.getInt(1), rs.getString(2), rs.getString(3) );
            }
            
            //conexion.getCon().close();
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
               
            }
        
        }
            
         return null;
         
     }
     
     public List listServiciosPuntosVenta(String idpunto) {
        
        List<Servicio> listar_servicio = new LinkedList<>();
                
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("SELECT sol.* FROM helpdesk_core.gnr_solicitud sol WHERE sol.id_punto_venta = ?");
            pst.setString(1, idpunto);
            rs = pst.executeQuery();
            
                
            while ( rs.next() ) {
                
                listar_servicio.add(new Servicio(rs.getString("num_solicitud"), rs.getDate("fecha_servicio"), rs.getString("sucursal"), rs.getString("id_punto_venta"), rs.getString("obs")) );
                
            }
            
            conexion.getCon().close();
            return listar_servicio;
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
           
        }
            
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
              return null;  
        
        
    }
    
}
