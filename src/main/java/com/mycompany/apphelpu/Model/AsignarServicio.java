/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Model;

import java.sql.Date;

/**
 *
 * @author usuario
 */
public class AsignarServicio {
    
    private int id;
    private int tipo_urgencia;
    private String tecnico_responsable;
    private String obs;
    private String fecha_apertura;
    private String fecha_recepcion;
    private String fecha_asignacion;
    private int fk_solicitud;
    private int fk_usuario;
    private int fk_tipo_servicio;
    private String num_solicitud;

    public AsignarServicio() {
    }

    public AsignarServicio(int id, int tipo_urgencia, String tecnico_responsable, String obs, String fecha_apertura, String fecha_recepcion, String fecha_asignacion, int fk_solicitud, int fk_usuario) {
        this.id = id;
        this.tipo_urgencia = tipo_urgencia;
        this.tecnico_responsable = tecnico_responsable;
        this.obs = obs;
        this.fecha_apertura = fecha_apertura;
        this.fecha_recepcion = fecha_recepcion;
        this.fecha_asignacion = fecha_asignacion;
        this.fk_solicitud = fk_solicitud;
        this.fk_usuario = fk_usuario;
    }

    public AsignarServicio(int tipo_urgencia, String tecnico_responsable, String obs, String fecha_apertura, String fecha_recepcion, String fecha_asignacion, String num_solicitud, int fk_usuario, int fk_tipo_servicio) {
        this.tipo_urgencia = tipo_urgencia;
        this.tecnico_responsable = tecnico_responsable;
        this.obs = obs;
        this.fecha_apertura = fecha_apertura;
        this.fecha_recepcion = fecha_recepcion;
        this.fecha_asignacion = fecha_asignacion;
        this.num_solicitud = num_solicitud;
        this.fk_usuario = fk_usuario;
        this.fk_tipo_servicio = fk_tipo_servicio;
    }

    public String getNum_solicitud() {
        return num_solicitud;
    }

    public void setNum_solicitud(String num_solicitud) {
        this.num_solicitud = num_solicitud;
    }
    
    

    public int getFk_tipo_servicio() {
        return fk_tipo_servicio;
    }

    public void setFk_tipo_servicio(int fk_tipo_servicio) {
        this.fk_tipo_servicio = fk_tipo_servicio;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo_urgencia() {
        return tipo_urgencia;
    }

    public void setTipo_urgencia(int tipo_urgencia) {
        this.tipo_urgencia = tipo_urgencia;
    }

    public String getTecnico_responsable() {
        return tecnico_responsable;
    }

    public void setTecnico_responsable(String tecnico_responsable) {
        this.tecnico_responsable = tecnico_responsable;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(String fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public String getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(String fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public String getFecha_asignacion() {
        return fecha_asignacion;
    }

    public void setFecha_asignacion(String fecha_asignacion) {
        this.fecha_asignacion = fecha_asignacion;
    }

    public int getFk_solicitud() {
        return fk_solicitud;
    }

    public void setFk_solicitud(int fk_solicitud) {
        this.fk_solicitud = fk_solicitud;
    }

    public int getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(int fk_usuario) {
        this.fk_usuario = fk_usuario;
    }
    
    
    
}
