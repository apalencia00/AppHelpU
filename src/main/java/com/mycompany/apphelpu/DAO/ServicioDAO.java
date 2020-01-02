/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.DAO;


import com.mycompany.apphelpu.Data.Database;
import com.mycompany.apphelpu.Facade.IServicio;
import com.mycompany.apphelpu.Model.AsignarServicio;
import com.mycompany.apphelpu.Model.Asunto;
import com.mycompany.apphelpu.Model.Servicio;
import com.mycompany.apphelpu.Model.UbicacionServicio;
import com.mycompany.apphelpu.Model.Visita;
import com.mycompany.apphelpu.Util.Resultado;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class ServicioDAO implements IServicio {
    
    Database conexion = null;
    PreparedStatement pst = null;
    ResultSet         rs  = null;
    
    Resultado resultado = null;

    @Override
    public Resultado addServicio(Servicio s) {
        
        java.util.Date fechap = new Date();
        java.sql.Date  fechasis = new java.sql.Date(fechap.getTime());
        
        try {
            
            conexion = new Database();
            conexion.conectar("apalencia", "asd.123*-");
                        
            pst = conexion.getCon().prepareStatement("select * from helpdesk_core.registrar_servicio(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            pst.setString(1, s.getNum_servicio());
            pst.setInt(2,    s.getTipo_solicitud());
            pst.setInt(3,    s.getTipo_recepcion());
            pst.setInt(4,    s.getTipo_asunto());
            pst.setString(5, s.getPunto_movil_fijo());
            pst.setString(6, s.getDireccion_servicio());
            pst.setString(7, s.getDescripcion());
            pst.setString(8, s.getSucursal());
            pst.setInt(9,    s.getFk_tipo_solicitante());
            pst.setString(10,s.getIdentificacion_solictante());
            pst.setInt(11,   s.getFk_usuario());
            pst.setString(12, "C");
            pst.setInt(13, s.getIde_punto());
            //pst.setDate(13, fechasis);
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                    
                resultado = new Resultado(rs.getInt(1), rs.getString(2), rs.getString(3));
                
            }
            
            
            conexion.getCon().close();
            return resultado;
            
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return null;
        
    }

    @Override
    public Resultado deleteServicio(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List listServicios() {
        
        List<Servicio> listar_servicio = new LinkedList<>();
               
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement(
                    " SELECT s.num_solicitud,\n" +
"	       s.tipo_solicitud,\n" +
"		   identificacion, \n" +
"		   substring(s.direccion from 0 for 21) as direccion,\n" +
"		   sucursal,\n" +
"		   s.tipo_solicitud,\n" +
"		   s.fk_tipo_recepcion ,\n" +
"		   tp.descripcion as asunto, \n" +
"		   punto_fijo_movil,\n" +
"		   descripcion,\n" +
"		   fecha_servicio,\n" +
"		   tec.nombre || ' ' || tec.apellido as nombrecompleto\n" +
"		   \n" +
"		   \n" +
"				FROM helpdesk_core.gnr_solicitud s,\n" +
"				     helpdesk_core.gnr_tipo_asunto tp , \n" +
"					 helpdesk_core.gnr_asignacion_solicitud asg, \n" +
"					 helpdesk_core.grn_personal_tecnico tec\n" +
"					 \n" +
"					WHERE fk_asunto = id_tipo_asunto \n" +
"					and asg.fk_solicitud = id_solicitud \n" +
"					and tec.id_personal_tecnico = asg.fk_tecnico \n" +
"					and s.fecha_servicio > current_date - interval '20' day\n" +
"					ORDER BY s.id_solicitud DESC	");
            rs = pst.executeQuery();
                
            while ( rs.next() ) {
                
                listar_servicio.add(new Servicio(rs.getString("num_solicitud"), rs.getInt("tipo_solicitud"), rs.getString("identificacion"), rs.getString("direccion"), rs.getString("sucursal"), rs.getInt("tipo_solicitud"), rs.getInt("fk_tipo_recepcion"), rs.getString("asunto"), rs.getString("punto_fijo_movil"), rs.getString("descripcion"), rs.getDate("fecha_servicio"), rs.getString("nombrecompleto"), 0, "") );
                
            }
            
            conexion.getCon().close();
            return listar_servicio;
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
             ex.printStackTrace();
        }
            
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
              return null;  
        
        
    }

    
    public List listAsignados(String usuario) {
        
        List<Servicio> listar_servicio = new LinkedList<>();
                
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("SELECT s.id_solicitud,s.num_solicitud,s.fecha_servicio, substring(s.direccion from 0 for 21) as direccion FROM helpdesk_core.gnr_solicitud s,helpdesk_core.gnr_asignacion_solicitud ds, helpdesk_core.grn_personal_tecnico tec, helpdesk_opciones.gnr_usuario us WHERE s.id_solicitud     = ds.fk_solicitud AND tec.identificacion = us.gnr_dcment AND tec.identificacion = ? AND   ds.estado = 'A' ORDER BY 1 DESC;");
            pst.setString(1, usuario);
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_servicio.add(new Servicio(rs.getInt("id_solicitud"),rs.getString("num_solicitud"), rs.getDate("fecha_servicio"), 0 , rs.getString("direccion"), "" ));
                
            }
            
            conexion.getCon().close();
            return listar_servicio;
            
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
             ex.printStackTrace();
        }
            
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
              return null;  
        
        
    }
    
    public List listAsignados_Admin() {
        
        List<Servicio> listar_servicio = new LinkedList<>();
        
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("SELECT s.id_solicitud,s.num_solicitud,s.id_punto_venta,s.fecha_servicio, s.direccion_servicio,s.estado \n" +
"FROM helpdesk_core.gnr_solicitud s,helpdesk_core.gnr_asignacion_solicitud ds\n" +
"WHERE s.id_solicitud = ds.fk_solicitud and s.fk_usuario = ? order by 1 desc");
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_servicio.add(new Servicio(rs.getInt("id_solicitud"),rs.getString("num_solicitud"), rs.getDate("fecha_servicio"), rs.getInt("id_punto_venta") , rs.getString("direccion_servicio"), rs.getString("estado") ));
                
            }
            
            conexion.getCon().close();
            return listar_servicio;
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
             ex.printStackTrace();
        }
            
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
              return null;  
        
        
    }
    
    public java.util.ArrayList<Object> getUltimoServicio(){
        
        String solicitud = "";
        int    id_solicitud = 0;
        ArrayList valor_servicio = new ArrayList();
        
         try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
                pst = conexion.getCon().prepareStatement("SELECT id_solicitud,num_solicitud FROM helpdesk_core.gnr_solicitud ORDER BY id_solicitud DESC\n" +
"  LIMIT 1 ");
            
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
               id_solicitud = rs.getInt(1);
               solicitud = rs.getString(2);
                
            }
            valor_servicio.add(id_solicitud);
            valor_servicio.add(solicitud);
            conexion.getCon().close();
            return valor_servicio;
            
         }catch ( Exception e ){
            try {
                conexion.getCon().close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
             e.printStackTrace();
         }
         
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
         return null;
        
        
            
    }
    
    public String getPuntoVenta(String num_servicio){
        
        String solicitud = "";
        
         try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
                pst = conexion.getCon().prepareStatement("SELECT id_punto_venta FROM helpdesk_core.gnr_solicitud WHERE num_solicitud = ?");
            
            pst.setString(1, num_servicio);
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
               solicitud = rs.getString(1);
                
            }
            
            conexion.getCon().close();
            return solicitud;
            
         }catch ( Exception e ){
            try {
                conexion.getCon().close();
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
              e.printStackTrace();
         }
         
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         return "";
        
        
    }
    
    
    
    public List localizarServicios(int usuario,int punto){
            
        List<UbicacionServicio> listar_servicio_ubicaciones = new LinkedList<>();
       
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("SELECT nombre,recurso,latitud,longitud,direccion_servicio  \n" +
"FROM helpdesk_core.gnr_solicitud s, helpdesk_core.gnr_asignacion_solicitud ds, \n" +
"helpdesk_core.gnr_ubicaciones_supegiros u\n" +
"WHERE s.id_solicitud = ds.fk_solicitud and ds.tecnico_responsable = 1045718258 "
                    + "and s.id_punto_venta = u.ide_punto and s.id_punto_venta = ? \n" +
"\n" +
"ORDER BY id_solicitud ASC");
            pst.setInt(1, punto);
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_servicio_ubicaciones.add(new UbicacionServicio(rs.getString("nombre"),rs.getString("recurso"), rs.getString("latitud"), rs.getString("longitud"), rs.getString("direccion_servicio") ));
                
            }
            
            conexion.getCon().close();
            return listar_servicio_ubicaciones;
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
             ex.printStackTrace();
        }
            
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
              return null;  
        
        
    }
    
    public List listServicios_All_criteria(String estado) {
        
        List<Servicio> listar_servicio = new LinkedList<>();
        
        String sql ="";
        
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
                    
                sql = "SELECT   s.id_solicitud,\n" +
"                               s.num_solicitud,\n" +
"                               s.tipo_solicitud,\n" +
"                               s.fk_tipo_recepcion,\n" +
"                               s.fk_asunto,\n" +
"                               s.punto_fijo_movil,\n" +
"                               s.obs,\n" +
"                               s.sucursal,\n" +
"                               s.direccion,\n" +
"                               s.fk_solicitante,\n" +
"                              s.documento,\n" +
"                               s.fk_usuario,\n" +
"                               s.estado as estado_servicio,\n" +
"                               s.fecha_servicio,\n" +
"                               s.usuario_actualizacion, \n" +
"                               tp.descripcion as asunto \n" +
"                        \n" +
"                                     FROM helpdesk_core.gnr_solicitud s,\n" +
"                                     helpdesk_core.gnr_tipo_asunto tp \n" +
"                                    \n" +
"                                    WHERE fk_asunto = id_tipo_asunto \n" +
"                                                            and s.estado = 'C'\n" +
"                                                            and fecha_servicio::DATE = NOW()::DATE\n" +
"                                                            and s.fk_tipo_recepcion = 1 \n" +
"                                                            ORDER BY s.id_solicitud DESC";
                pst = conexion.getCon().prepareStatement(sql);
                //pst.setString(1, "C");
            
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_servicio.add(new Servicio(rs.getString("num_solicitud"), rs.getInt("tipo_solicitud"), rs.getString("documento"), rs.getString("direccion"), rs.getString("sucursal"), rs.getInt("tipo_solicitud"), rs.getInt("fk_tipo_recepcion"), 0, rs.getString("punto_fijo_movil"), rs.getString("asunto"), rs.getDate("fecha_servicio"), "", 0, rs.getString("estado_servicio")) );
                
            }
            
            conexion.getCon().close();
            return listar_servicio;
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
             ex.printStackTrace();
        }
            
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
              return null;  
        
        
    }
    
    
        public List listServicios_All_criteria_seguridad(String estado) {
        
        List<Servicio> listar_servicio = new LinkedList<>();
   
        String sql ="";
        
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if ( estado.equalsIgnoreCase("C") || estado.equalsIgnoreCase("A") || estado.equalsIgnoreCase("E") || estado.equalsIgnoreCase("N") || estado.equalsIgnoreCase("F") ) {
                    
                sql = "SELECT s.id_solicitud,s.num_solicitud,s.tipo_solicitud,s.fk_tipo_recepcion,s.fk_asunto,s.punto_fijo_movil,s.obs,s.sucursal,s.direccion,s.fk_solicitante,s.documento,s.fk_usuario,s.estado as estado_servicio,s.fecha_servicio,s.usuario_actualizacion, tp.descripcion as asunto FROM helpdesk_core.gnr_solicitud s,helpdesk_core.gnr_tipo_asunto tp where fk_asunto = id_tipo_asunto and s.estado = ?  and s.fk_tipo_recepcion = 2 ORDER BY s.id_solicitud DESC";
                pst = conexion.getCon().prepareStatement(sql);
                pst.setString(1, estado);
            }else{
                sql = "SELECT s.id_solicitud,s.num_solicitud,s.tipo_solicitud,s.fk_tipo_recepcion,s.fk_asunto,s.punto_fijo_movil,s.obs,s.sucursal,s.direccion,s.fk_solicitante,s.documento,s.fk_usuario,s.estado as estado_servicio,s.fecha_servicio,s.usuario_actualizacion, tp.descripcion as asunto FROM helpdesk_core.gnr_solicitud s,helpdesk_core.gnr_tipo_asunto tp where fk_asunto = id_tipo_asunto and s.fk_tipo_recepcion = 2 ORDER BY s.id_solicitud DESC";
                pst = conexion.getCon().prepareStatement(sql);
                }
            
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_servicio.add(new Servicio(rs.getString("num_solicitud"), rs.getInt("tipo_solicitud"), rs.getString("documento"), rs.getString("direccion"), rs.getString("sucursal"), rs.getInt("tipo_solicitud"), rs.getInt("fk_tipo_recepcion"), rs.getString("asunto"), rs.getString("punto_fijo_movil"), rs.getString("asunto"), rs.getDate("fecha_servicio"), "", 0, rs.getString("estado_servicio")) );
                
            }
            
            conexion.getCon().close();
            return listar_servicio;
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                 ex.printStackTrace();
            }
             ex.printStackTrace();
        }
            
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
              return null;  
        
        
    }
    

    @Override
    public List listServiciosByCriteria(Object id) {
        
        List listar_servicio = new LinkedList<>();
        
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
             pst = conexion.getCon().prepareStatement(""
                     + "SELECT\n" +
"       sol.documento,\n" +
"       sol.num_solicitud, \n" +
"       sol.punto_fijo_movil,\n" +
"	   sol.obs,\n" +
"	   sol.direccion,\n" +
"       sol.estado,\n" +
"	   sol.fecha_servicio,\n" +
"	   tp.id_tipo_asunto,\n" +
"	   tp.descripcion as asunto \n" +
"	   \n" +
"FROM helpdesk_core.gnr_solicitud sol,\n" +
"     helpdesk_core.gnr_tipo_asunto tp\n" +
"\n" +
"WHERE sol.num_solicitud = ? \n" +
"\n" +
"AND tp.id_tipo_asunto = sol.fk_asunto ;");
            
            pst.setString(1, id.toString());
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                
                listar_servicio.add(rs.getString(1));
                listar_servicio.add(rs.getString(2));
                listar_servicio.add(rs.getString(3));
                listar_servicio.add(rs.getString(4));
                listar_servicio.add(rs.getString(5));
                listar_servicio.add(rs.getString(6));
                listar_servicio.add(rs.getDate(7));
                listar_servicio.add(rs.getString(8));
                listar_servicio.add(rs.getString(9));
                
            }
            
            conexion.getCon().close();
            return listar_servicio;
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
                 ex.printStackTrace();
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
    
       public List listServiciosByCriteria(int oper, String dato) {
        
        List listar_servicio = new LinkedList<>();
        
        String query = "";
        
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
            switch(oper) {
                
                case 1:
                    
                     query = "SELECT *, \n" +
"       tp.descripcion as asunto, \n" +
"	   tec.nombre || ' ' || tec.apellido as nombrecompleto, \n" +
"	   tiposerv.descripcion as desc_tipo_serv \n" +
"	   \n" +
"	   FROM helpdesk_core.gnr_solicitud, \n" +
"	   helpdesk_core.gnr_tipo_asunto tp ,\n" +
"	   helpdesk_core.gnr_asignacion_solicitud asg, \n" +
"	   helpdesk_core.grn_personal_tecnico tec,\n" +
"	   helpdesk_core.gnr_tipo_servicio tiposerv \n" +
"	   \n" +
"	   WHERE fk_asunto = id_tipo_asunto \n" +
"	   and asg.fk_solicitud = id_solicitud \n" +
"	   and tec.id_personal_tecnico = asg.fk_tecnico\n" +
"	   and asg.fk_tipo_servicio = tiposerv.id_tipo_servicio \n" +
"	   and asg.fk_tipo_servicio = ? and asg.estado = 'A'";
                        pst = conexion.getCon().prepareStatement(query);
                        pst.setInt(1, Integer.parseInt(dato));
                        
                    break;
                    
                case 2:
                    
                    query = "SELECT *, \n" +
"       tp.descripcion as asunto, \n" +
"	   tec.nombre || ' ' || tec.apellido as nombrecompleto, \n" +
"	   tiposerv.descripcion as desc_tipo_serv \n" +
"	   \n" +
"	   FROM helpdesk_core.gnr_solicitud, \n" +
"	        helpdesk_core.gnr_tipo_asunto tp ,\n" +
"			helpdesk_core.gnr_asignacion_solicitud asg, \n" +
"			helpdesk_core.grn_personal_tecnico tec,\n" +
"			helpdesk_core.gnr_tipo_servicio tiposerv \n" +
"			\n" +
"			WHERE fk_asunto = id_tipo_asunto \n" +
"			and asg.fk_solicitud = id_solicitud \n" +
"			and tec.id_personal_tecnico = asg.fk_tecnico\n" +
"			and asg.fk_tipo_servicio = tiposerv.id_tipo_servicio \n" +
"			and helpdesk_core.gnr_solicitud.estado = 'C' and asg.estado = ?"
                            + ""
                            + "ORDER BY id_solicitud DESC"; 
                    pst = conexion.getCon().prepareStatement(query);
                    pst.setString(1, dato);
                    
                    break;
                    
                case 3:
                    
                     query = "    SELECT *, tp.descripcion as asunto, \n" +
"	          tec.nombre || ' ' || tec.apellido as nombrecompleto, \n" +
"			 tiposerv.descripcion as desc_tipo_serv \n" +
"			  \n" +
"			  FROM helpdesk_core.gnr_solicitud, \n" +
"			       helpdesk_core.gnr_tipo_asunto tp ,\n" +
"				   helpdesk_core.gnr_asignacion_solicitud asg, \n" +
"				   helpdesk_core.grn_personal_tecnico tec,\n" +
"				   helpdesk_core.gnr_tipo_servicio tiposerv \n" +
"				   \n" +
"				   \n" +
"				   WHERE fk_asunto = id_tipo_asunto\n" +
"				   and asg.fk_solicitud = id_solicitud \n" +
"				   and tec.id_personal_tecnico = asg.fk_tecnico \n" +
"				   and asg.fk_tipo_servicio = tiposerv.id_tipo_servicio\n" +
"				   and asg.fk_tecnico = ?"; 
                     pst = conexion.getCon().prepareStatement(query);
                     pst.setString(1, dato);
                    
                    break;
                    
                    
                case 4 :
                    
                    
                    break;
                    
                    
                default:
                    
                    
                     
                    
                
            }
                       
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
              listar_servicio.add(new Servicio(rs.getInt("id_solicitud"),rs.getString("num_solicitud"), rs.getInt("tipo_solicitud"), rs.getString("identificacion"), rs.getString("direccion"), rs.getString("sucursal"), rs.getInt("tipo_solicitud"), rs.getInt("fk_tipo_recepcion"), rs.getString("asunto"), rs.getString("punto_fijo_movil"), "", rs.getDate("fecha_servicio"), rs.getString("nombrecompleto"), 0, rs.getString("desc_tipo_serv")) );
                
            }
            
            conexion.getCon().close();
            return listar_servicio;
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                 ex.printStackTrace();
            }
             ex.printStackTrace();
        }
            
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
              return null;  
        
        
    }

    @Override
    public Servicio updateServicio(Object serv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List listAsuntos() {
        
        List<Asunto> listar_asunto = new LinkedList<>();
        
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("SELECT * FROM helpdesk_core.gnr_tipo_asunto");
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                listar_asunto.add(new Asunto(rs.getInt(1) , rs.getString(2) , rs.getString(3) ) );
                
            }
            
            conexion.getCon().close();
            return listar_asunto;
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                 ex.printStackTrace();
            }
             ex.printStackTrace();
        }
            
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
              return null;      
    }

    @Override
    public int lastServicio() {
        
       
      int res = 0;
        
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("select id_solicitud from helpdesk_core.gnr_solicitud order by 1 desc limit 1");
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                res = rs.getInt(1);
                
            }
            
            conexion.getCon().close();
            return res;
            
        } catch (SQLException ex) {
          try {
              conexion.getCon().close();
          } catch (SQLException ex1) {
               ex.printStackTrace();
          }
             ex.printStackTrace();
        }
            
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
              return 0;      
        
    }

    @Override
    public Resultado asignarServicio(AsignarServicio asignar) {
        
         java.util.Date fechap = new Date();
         java.sql.Date fecha_sis = new java.sql.Date(fechap.getTime());
         java.sql.Date  fechasig = null;
         java.sql.Date  fecharec = null;
        
               
        try {
            conexion = new Database();
            conexion.conectar("apalencia", "asd.123*-");
                        
            pst = conexion.getCon().prepareStatement("select * from helpdesk_core.actualizar_servicio_asignado(?,?,?,?,?,?,?,?,?)");
            
            
            pst.setInt(1,    asignar.getTipo_urgencia());
            pst.setString(2, asignar.getTecnico_responsable());
            pst.setString(3, asignar.getObs());
            pst.setString(4, asignar.getFecha_apertura());
            pst.setString(5, asignar.getFecha_recepcion());
            pst.setString(6, asignar.getFecha_asignacion());
            pst.setString(7, asignar.getNum_solicitud());
            pst.setInt(8,    asignar.getFk_usuario());
            pst.setInt(9,    asignar.getFk_tipo_servicio()); 
            
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                    
                resultado = new Resultado(rs.getInt(1), rs.getString(2), rs.getString(3));
                
            }
            
            conexion.getCon().close();
            return resultado;
            
        } catch (SQLException ex) {
             try {
                 conexion.getCon().close();
             } catch (SQLException ex1) {
                  ex1.printStackTrace();
             }
             ex.printStackTrace();
        } catch (IOException ex2) {
             ex2.printStackTrace();
        }
            
        return null;
        
    }
    
    public void agregaObsevacionTecnica(int fk_solicitud, String obs){
        
        try {
            conexion = new Database();
            conexion.conectar("apalencia", "asd.123*-");
        } catch (SQLException ex) {
             ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try {
            pst = conexion.getCon().prepareStatement("update helpdesk_core.gnr_asignacion_solicitud set obs = ? where fk_solicitud = ?");
            pst.setString(1, obs);
            pst.setInt(2, fk_solicitud);
            
            
            pst.execute();
            conexion.getCon().close();
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
             ex.printStackTrace();
        }
        
        try {
            conexion.getCon().close();
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        
    }

    @Override
    public int obtenerIdServicio(Object id) {
        
        int solicitud = 0;
               
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("SELECT id_solicitud, num_solicitud, tipo_solicitud, tipo_recepcion, tipo_asunto, punto_movil_fijo, descripcion, sucursal, direccion_servicio, fk_tipo_solicitante, identificacion, fk_usuario, estado, fecha_servicio, fecha_actualizacion, usuario_actualizacion\n" +
"	FROM helpdesk_core.gnr_solicitud WHERE num_solicitud = ?");
            
            pst.setString(1, id.toString());
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
               solicitud = rs.getInt(1);
                
            }
            
            conexion.getCon().close();
            return solicitud;
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
             ex.printStackTrace();
        }
            
         return 0;
        
    }

    @Override
    public Servicio getServicio(int num_serv) {
        
        String asunto;
        String descripcion="";
               
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("select sol.num_solicitud, tpa.descripcion, sol.descripcion\n" +
"from helpdesk_core.gnr_solicitud sol, helpdesk_core.gnr_tipo_asunto tpa\n" +
"where sol.tipo_asunto = tpa.id_asunto and sol.id_solicitud = ?");
            
            pst.setInt(1, num_serv);
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                return new Servicio(rs.getString(1), rs.getString(2), rs.getString(3));
            }
            
            conexion.getCon().close();
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
             ex.printStackTrace();
        }
            
         return null;
        
    }

  
    
    public Servicio getInfoServicio(String num_serv) {
        
        String asunto;
        String descripcion="";
               
        try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            pst = conexion.getCon().prepareStatement("select sol.num_solicitud, tpa.descripcion, sol.direccion ,sol.obs\n" +
"from helpdesk_core.gnr_solicitud sol, helpdesk_core.gnr_tipo_asunto tpa\n" +
"where sol.fk_asunto = tpa.id_tipo_asunto and sol.num_solicitud = ?");
            
            pst.setString(1, num_serv);
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                return new Servicio(rs.getString(1), rs.getString(2), rs.getString(3) ,rs.getString(4));
            }
            
            conexion.getCon().close();
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
             ex.printStackTrace();
        }
            
         return null;
        
    }
    
    public Resultado cerrarServicio(String servicio, int usuario, String descripcion, String estado, String pendiente,int inventario, String imei, String simcard, int operador) throws SQLException{
        
          try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("select * from helpdesk_core.cerrar_servicio(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, servicio);
            pst.setInt(2, usuario);
            pst.setString(3, descripcion);
            pst.setString(4, estado);
            pst.setString(5, pendiente);
            pst.setInt(6, inventario);
            pst.setString(7, imei);
            pst.setString(8, simcard);
            pst.setInt(9, operador);
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                    
                resultado = new Resultado(rs.getInt(1), rs.getString(2), rs.getString(3));
                
            }
                        this.conexion.getCon().close();
            return resultado;

            
        
    }
    
    public Servicio consultar_PuntoVenta(int punto){
        
         try {
            try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("SELECT gnr_direccion,gnr_zona,gnr_cda,gnr_zonahr FROM helpdesk_core.gnr_ubicacion_servicio WHERE gnr_pv= ? ");
            
            pst.setInt(1, punto);
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                
                return new Servicio(rs.getString(1), rs.getString(2), rs.getString(3) ,rs.getString(4));
            }
            
            //conexion.getCon().close();
            
        } catch (SQLException ex) {
            try {
                conexion.getCon().close();
            } catch (SQLException ex1) {
                 ex1.printStackTrace();
            }
             ex.printStackTrace();
        }
            
         return null;
        
    }
    
        public Resultado genera_controlAcceso(String idservicio,String qr) throws SQLException{
        
         try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("select * from helpdesk_core.validar_vista(?,?)");
            pst.setString(1, idservicio);
            pst.setString(2, qr);
 
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                    
                resultado = new Resultado(rs.getInt(1), rs.getString(2), rs.getString(3));
                
            }
                        this.conexion.getCon().close();
            return resultado;

        
        
        
    }
    
    
    public Visita cargarDatosDeVisita(String os) throws SQLException{
        
        Visita visita = null;
        
        try {
                conexion = new Database();
                conexion.conectar("apalencia", "asd.123*-");
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
            
            pst = conexion.getCon().prepareStatement("select * from helpdesk_core.info_visita(?)");
            pst.setString(1, os);
           
 
            
            rs = pst.executeQuery();
            
            while ( rs.next() ) {
                    
                visita = new Visita(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                
            }
            
            rs.close();
            pst.close();
            this.conexion.getCon().close();
            return visita;

        
    }
    
    
    public int cantidadServicios(int op, String estado) {
        
        String query = "";
        int cantidad_dplas = 0;
        
        
        
        conexion = new Database();
        try {
            conexion.conectar("apalencia", "asd.123*-");
        } catch (SQLException ex) {
             ex.printStackTrace();
        } catch (IOException ex) {
             ex.printStackTrace();
        }
        
        switch(op) {
            
            case 1:// cantidad de servicios 
                
                query = "SELECT count (1)\n" +
"\n" +
"	   FROM helpdesk_core.gnr_solicitud, \n" +
"	        helpdesk_core.gnr_tipo_asunto tp ,\n" +
"			helpdesk_core.gnr_asignacion_solicitud asg, \n" +
"			helpdesk_core.grn_personal_tecnico tec,\n" +
"			helpdesk_core.gnr_tipo_servicio tiposerv \n" +
"			\n" +
"		WHERE fk_asunto = id_tipo_asunto \n" +
"			and asg.fk_solicitud = id_solicitud \n" +
"			and tec.id_personal_tecnico = asg.fk_tecnico\n" +
"			and asg.fk_tipo_servicio = tiposerv.id_tipo_servicio \n" +
"			and helpdesk_core.gnr_solicitud.estado = 'C' and asg.estado =? ";
            
                try {
                    pst = conexion.getCon().prepareStatement(query);
                     pst.setString(1, estado);
                } catch (SQLException ex) {
                    Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                        
                break;


            
        }
        
        
        try {
            rs = pst.executeQuery();
             while ( rs.next() ){
                
                cantidad_dplas= rs.getInt(1);
                
            }
        } catch (SQLException ex) {
             ex.printStackTrace();
            
            
        }
        
        
        return cantidad_dplas;
        
    }
    
    
    
}
