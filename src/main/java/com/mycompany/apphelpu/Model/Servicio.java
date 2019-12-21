/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Model;

import com.pusher.rest.Pusher;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collections;

/**
 *
 * @author usuario
 */
public class Servicio  {
   
    private  int    id;
    private  String num_servicio;
    private  int    fk_tipo_solicitante;
    private  String identificacion_solictante;
    private  String direccion_servicio;
    private  String sucursal;
    private  int    tipo_solicitud;
    private  int    tipo_recepcion;
    private  int    tipo_asunto;
    private  String punto_movil_fijo;
    private  String descripcion;
    private  java.sql.Date fechaser;
    private  String imagen;
    private  int    fk_usuario;
    private  String estado;
    private  java.sql.Date fecha_actualiza;
    private  int usuario_actualiza;
    private  String asunto;
    private  int ide_punto_venta;
    
    public Servicio(){}

    public int getIde_punto() {
        return ide_punto_venta;
    }

    public void setIde_punto(int id_punto) {
        this.ide_punto_venta = id_punto;
    }
    
    

   
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Servicio(String num_servicio, Date fechaser, String sucursal, String punto_movil_fijo,  String asunto) {
        this.num_servicio = num_servicio;
        this.fechaser = fechaser;
        this.sucursal = sucursal;
        this.punto_movil_fijo = punto_movil_fijo;
        
        this.asunto = asunto;
    }
    
    
    
    
    

    public Servicio(int id, String num_servicio, int fk_tipo_solicitante, String identificacion_solictante, String direccion_servicio, String sucursal, int tipo_solicitud, int tipo_recepcion ,int tipo_asunto, String punto_movil_fijo, String descripcion, Date fechaser, String imagen, int fk_usuario, String estado, Date fecha_actualiza, int usuario_actualiza) {
        this.id = id;
        this.num_servicio = num_servicio;
        this.fk_tipo_solicitante = fk_tipo_solicitante;
        this.identificacion_solictante = identificacion_solictante;
        this.direccion_servicio = direccion_servicio;
        this.sucursal = sucursal;
        this.tipo_solicitud = tipo_solicitud;
        this.tipo_recepcion = tipo_recepcion;
        this.tipo_asunto = tipo_asunto;
        
        this.punto_movil_fijo = punto_movil_fijo;
        this.descripcion = descripcion;
        this.fechaser = fechaser;
        this.imagen = imagen;
        this.fk_usuario = fk_usuario;
        this.estado = estado;
        this.fecha_actualiza = fecha_actualiza;
        this.usuario_actualiza = usuario_actualiza;
    }

    public Servicio(String num_servicio, int fk_tipo_solicitante, String identificacion_solictante, String direccion_servicio, String sucursal, int tipo_solicitud ,int tipo_asunto,String punto_movil_fijo, String descripcion, Date fechaser, String imagen, int fk_usuario, String estado, Date fecha_actualiza, int usuario_actualiza) {
        this.num_servicio = num_servicio;
        this.fk_tipo_solicitante = fk_tipo_solicitante;
        this.identificacion_solictante = identificacion_solictante;
        this.direccion_servicio = direccion_servicio;
        this.sucursal = sucursal;
        this.tipo_solicitud = tipo_solicitud;
        this.tipo_asunto = tipo_asunto;
        
        this.punto_movil_fijo = punto_movil_fijo;
        this.descripcion = descripcion;
        this.fechaser = fechaser;
        this.imagen = imagen;
        this.fk_usuario = fk_usuario;
        this.estado = estado;
        this.fecha_actualiza = fecha_actualiza;
        this.usuario_actualiza = usuario_actualiza;
    }

    public Servicio(String num_servicio, int fk_tipo_solicitante, String identificacion_solictante, String direccion_servicio, String sucursal, int tipo_solicitud, int tipo_recepcion ,int tipo_asunto, String punto_movil_fijo, String descripcion, Date fechaser, String imagen, int fk_usuario, String estado) {
        this.num_servicio = num_servicio;
        this.fk_tipo_solicitante = fk_tipo_solicitante;
        this.identificacion_solictante = identificacion_solictante;
        this.direccion_servicio = direccion_servicio;
        this.sucursal = sucursal;
        this.tipo_solicitud = tipo_solicitud;
        this.tipo_recepcion = tipo_recepcion;
        this.tipo_asunto = tipo_asunto;
        
        this.punto_movil_fijo = punto_movil_fijo;
        this.descripcion = descripcion;
        this.fechaser = fechaser;
        this.imagen = imagen;
        this.fk_usuario = fk_usuario;
        this.estado = estado;
    }
    
        public Servicio(int id_solicitud,String num_servicio, int fk_tipo_solicitante, String identificacion_solictante, String direccion_servicio, String sucursal, int tipo_solicitud, int tipo_recepcion ,String tipo_asunto, String punto_movil_fijo, String descripcion, Date fechaser, String imagen, int fk_usuario, String estado) {
        
        this.id = id_solicitud;
        this.num_servicio = num_servicio;
        this.fk_tipo_solicitante = fk_tipo_solicitante;
        this.identificacion_solictante = identificacion_solictante;
        this.direccion_servicio = direccion_servicio;
        this.sucursal = sucursal;
        this.tipo_solicitud = tipo_solicitud;
        this.tipo_recepcion = tipo_recepcion;
        this.asunto = tipo_asunto;
        
        this.punto_movil_fijo = punto_movil_fijo;
        this.descripcion = descripcion;
        this.fechaser = fechaser;
        this.imagen = imagen;
        this.fk_usuario = fk_usuario;
        this.estado = estado;
    }
        
        
        public Servicio(String num_servicio, int fk_tipo_solicitante, String identificacion_solictante, String direccion_servicio, String sucursal, int tipo_solicitud, int tipo_recepcion ,String tipo_asunto, String punto_movil_fijo, String descripcion, Date fechaser, String imagen, int fk_usuario, String estado) {
        
        
        this.num_servicio = num_servicio;
        this.fk_tipo_solicitante = fk_tipo_solicitante;
        this.identificacion_solictante = identificacion_solictante;
        this.direccion_servicio = direccion_servicio;
        this.sucursal = sucursal;
        this.tipo_solicitud = tipo_solicitud;
        this.tipo_recepcion = tipo_recepcion;
        this.asunto = tipo_asunto;
        
        this.punto_movil_fijo = punto_movil_fijo;
        this.descripcion = descripcion;
        this.fechaser = fechaser;
        this.imagen = imagen;
        this.fk_usuario = fk_usuario;
        this.estado = estado;
    }


    public Servicio(int id_solicitud,String num_servicio, Date fechaser, int ide_punto, String direccion, String estado) {
        this.id = id_solicitud;
        this.num_servicio = num_servicio;
        this.fechaser = fechaser;
        this.ide_punto_venta = ide_punto;
        this.direccion_servicio = direccion;
        this.estado = estado;
    }

    public Servicio(String num_servicio,  String asunto, String descripcion) {
        this.num_servicio = num_servicio;
        this.asunto = asunto;
      
        this.descripcion = descripcion;
        
    }
    
        public Servicio(String num_servicio,  String asunto, String direccion ,String descripcion) {
        this.num_servicio = num_servicio;
        this.asunto = asunto;
        this.direccion_servicio = direccion;
        this.descripcion = descripcion;
        
    }
     

        
    
    
    
        
        
        
        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum_servicio() {
        return num_servicio;
    }

    public void setNum_servicio(String num_servicio) {
        this.num_servicio = num_servicio;
    }

    public int getFk_tipo_solicitante() {
        return fk_tipo_solicitante;
    }

    public void setFk_tipo_solicitante(int fk_tipo_solicitante) {
        this.fk_tipo_solicitante = fk_tipo_solicitante;
    }

    public String getIdentificacion_solictante() {
        return identificacion_solictante;
    }

    public void setIdentificacion_solictante(String identificacion_solictante) {
        this.identificacion_solictante = identificacion_solictante;
    }

    public String getDireccion_servicio() {
        return direccion_servicio;
    }

    public void setDireccion_servicio(String direccion_servicio) {
        this.direccion_servicio = direccion_servicio;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public int getTipo_solicitud() {
        return tipo_solicitud;
    }

    public void setTipo_solicitud(int tipo_solicitud) {
        this.tipo_solicitud = tipo_solicitud;
    }

    public int getTipo_asunto() {
        return tipo_asunto;
    }

    public void setTipo_asunto(int tipo_asunto) {
        this.tipo_asunto = tipo_asunto;
    }


    public String getPunto_movil_fijo() {
        return punto_movil_fijo;
    }

    public void setPunto_movil_fijo(String punto_movil_fijo) {
        this.punto_movil_fijo = punto_movil_fijo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaser() {
        return fechaser;
    }

    public void setFechaser(Date fechaser) {
        this.fechaser = fechaser;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(int fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_actualiza() {
        return fecha_actualiza;
    }

    public void setFecha_actualiza(Date fecha_actualiza) {
        this.fecha_actualiza = fecha_actualiza;
    }

    public int getUsuario_actualiza() {
        return usuario_actualiza;
    }

    public void setUsuario_actualiza(int usuario_actualiza) {
        this.usuario_actualiza = usuario_actualiza;
    }

    public int getTipo_recepcion() {
        return tipo_recepcion;
    }

    public void setTipo_recepcion(int tipo_recepcion) {
        this.tipo_recepcion = tipo_recepcion;
    }
    
       
    
    
}
