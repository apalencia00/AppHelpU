/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.apphelpu.Data.Database;
import com.mycompany.apphelpu.Facade.ITecnicos;
import com.mycompany.apphelpu.Model.Tecnico;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class TecnicosDAO implements ITecnicos{
    
    Database cone = new Database();
    java.sql.PreparedStatement pst = null;
    java.sql.ResultSet rs = null;

    @Override
    public Tecnico crearTecnico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int borrarTecnico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tecnico actualizarTecnico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tecnico> listarTecnicos(String serv_asig) {
       
        List listar_tecnicos = new LinkedList<>();
        Gson gson = new GsonBuilder().create();
        String sql = "";
        
        try {
            try {
                cone.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if ( serv_asig == "" || serv_asig.equals("")) {
            
            pst = cone.getCon().prepareStatement("select id_personal_tecnico,tipo_identificacion,identificacion,nombre || ' ' || apellido as nomb_comp,apellido,celular,personal,ext,fk_tipo_cargo from helpdesk_core.grn_personal_tecnico order by 1");
            
            }else{
                pst = cone.getCon().prepareStatement("SELECT\n" +
"id_personal_tecnico,tipo_identificacion,identificacion,nombre || ' ' || apellido as nomb_comp,apellido,celular,personal,ext,fk_tipo_cargo\n" +
"FROM helpdesk_core.gnr_solicitud sol,\n" +
"     helpdesk_core.gnr_asignacion_solicitud asg,\n" +
"     helpdesk_core.grn_personal_tecnico tec\n" +
"\n" +
"\n" +
"WHERE sol.id_solicitud = asg.fk_solicitud\n" +
"AND   sol.num_solicitud = ? \n" +
"AND   asg.fk_tecnico = tec.id_personal_tecnico");
                pst.setString(1, serv_asig);
            }
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_tecnicos.add(new Tecnico(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9)));
                
            }
            
            cone.getCon().close();
            return listar_tecnicos;
            
        }catch(Exception e){
            try {
                cone.getCon().close();
            } catch (SQLException ex) {
                Logger.getLogger(TecnicosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
        
        return null;
        
    }
    
}
