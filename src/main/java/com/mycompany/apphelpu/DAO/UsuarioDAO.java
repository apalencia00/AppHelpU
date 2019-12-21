/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.DAO;


import com.mycompany.apphelpu.Data.Database;
import com.mycompany.apphelpu.Facade.IUsuario;
import com.mycompany.apphelpu.Model.Usuario;
import com.mycompany.apphelpu.Util.Resultado;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;

/**
 *
 * @author usuario
 */
public class UsuarioDAO implements IUsuario{
    
    Database postconn = new Database();
    PreparedStatement pst = null;
    ResultSet rs;
    
    Resultado resultado;



  
    @Override
    public Resultado addUsuario(int tipodoc,String documento,String usuario,String nombre, String apellido,int perfil,String estado) {
        
        
        try {
                  
            postconn.conectar("apalencia", "asd.123*-");
            if ( ( tipodoc != 0 || perfil != 0 ) && ( documento != null || nombre != null || apellido != null ) ) {
                
              pst = postconn.getCon().prepareStatement("select * from helpdesk_opciones.crear_usuario(?,?,?,?,?,?,?)");
              pst.setInt(1, tipodoc);
              pst.setString(2, documento);
              pst.setString(3, usuario);
              pst.setString(4, nombre);
              pst.setInt(5, perfil);
              pst.setString(6, apellido);
              pst.setString(7, estado);
    
              
              rs = pst.executeQuery();
            
            while ( rs.next() ) {
                    
                resultado = new Resultado(rs.getInt(1), rs.getString(2), rs.getString(3));
                
            }
            postconn.getCon().close();
            return resultado;
                
                
            }else{
                return null;
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
        
    

    @Override
    public void deleteUsuarioById(int i) {
        
        PreparedStatement pst = null;
        
        try {
            
            pst = postconn.getCon().prepareStatement("DELETE FROM helpdesk_opciones.gnr_usuario\n" +
"	WHERE gnr_idusuario = ?;");
            pst.setInt(1, i);
            
            pst.executeUpdate();
            
            postconn.getCon().close();
          
            
        }catch(Exception e){
            try {
                postconn.getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        
    }

  
    public Resultado updateUsuarioById(Usuario user) {
        
         try {
                  
            postconn.conectar("apalencia", "asd.123*-");
            if ( ( user != null ) ) {
                
              pst = postconn.getCon().prepareStatement("select * from helpdesk_opciones.crear_usuario(?,?,?,?,?,?,?)");
           
              pst.setInt(1, 1);
              pst.setString(2, user.getDocumento());
              pst.setString(3, "");
              pst.setString(4, user.getNombre());
              pst.setString(7, user.getEstado());
              pst.setString(6, user.getApellido());
              pst.setInt(5, user.getTipo_perfil());
      
              
              rs = pst.executeQuery();
            
            while ( rs.next() ) {
                    
                resultado = new Resultado(rs.getInt(1), rs.getString(2), rs.getString(3));
                
            }
            postconn.getCon().close();
            return resultado;
                
                
            }else{
                return null;
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    @Override
    public List getAllUsuarios() {
        
        List<Usuario> list_usuario = new LinkedList<Usuario>();
        PreparedStatement pst = null;
        
        try {
            
            postconn.conectar("apalencia", "asd.123*-");
          
            pst = postconn.getCon().prepareStatement("SELECT gnr_idusuario, gnr_tipo_dcment, gnr_dcment, gnr_usuario, gnr_nombre,  gnr_estado, gnr_fecha_creacion, gnr_tperfil, gnr_apellido\n" +
"	FROM helpdesk_opciones.gnr_usuario");
            
            ResultSet rs = pst.executeQuery();
            
            while ( rs.next() ) {
                list_usuario.add( new Usuario( rs.getInt("gnr_idusuario") ,rs.getString("gnr_tipo_dcment"), rs.getString("gnr_dcment"), rs.getString("gnr_usuario") , rs.getString("gnr_nombre"), rs.getString("gnr_apellido") ,rs.getInt("gnr_tperfil"), rs.getString("gnr_estado") ) );
            }
        
            postconn.getCon().close();
            return list_usuario;
            
            }catch(Exception e){
            try {
                postconn.getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
                e.printStackTrace();
            }
    
        return null;
        
}

    @Override
    public Usuario getUsuarioByCriteria(Object param) {
        
        try {
                             
            postconn.conectar("apalencia", "asd.123*-");
            pst = postconn.getCon().prepareStatement("SELECT gnr_idusuario, gnr_tipo_dcment, gnr_dcment, gnr_usuario, gnr_nombre, gnr_sucursal, gnr_direccion,  gnr_estado, gnr_fecha_creacion, gnr_tperfil, gnr_apellido,gnr_punto_venta\n" +
                    "	FROM helpdesk_opciones.gnr_usuario WHERE gnr_dcment = ?");
            pst.setString(1, param.toString());
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) { 
                return new Usuario( rs.getString("gnr_tipo_dcment"), rs.getString("gnr_nombre"), rs.getString("gnr_apellido") , rs.getString("gnr_direccion"), rs.getInt("gnr_punto_venta") ) ;
            }
            
            postconn.getCon().close();
    
        } catch (SQLException | IOException ex) {
           ex.printStackTrace();
        }
        
        return null;
    }

    @Override
    public Usuario updateUsuarioById(Object param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
