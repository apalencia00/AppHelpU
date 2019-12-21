/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Model;

/**
 *
 * @author apalencia
 */
public class Visita {
    
    private int id_visita;
    private String num_servicio;
    private String fecha_apertura;
    private String identificacion_tecnico;
    private String nombre_tecnico;
    private String sucursal;
    private String direccion;
    private String asunto;
    private String obs;

    public int getId_visita() {
        return id_visita;
    }

    public void setId_visita(int id_visita) {
        this.id_visita = id_visita;
    }

    public String getNum_servicio() {
        return num_servicio;
    }

    public void setNum_servicio(String num_servicio) {
        this.num_servicio = num_servicio;
    }

    public String getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(String fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public String getIdentificacion_tecnico() {
        return identificacion_tecnico;
    }

    public void setIdentificacion_tecnico(String identificacion_tecnico) {
        this.identificacion_tecnico = identificacion_tecnico;
    }

    public String getNombre_tecnico() {
        return nombre_tecnico;
    }

    public void setNombre_tecnico(String nombre_tecnico) {
        this.nombre_tecnico = nombre_tecnico;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Visita(String num_servicio, String fecha_apertura, String identificacion_tecnico, String nombre_tecnico, String sucursal, String direccion, String asunto, String obs) {
        this.num_servicio = num_servicio;
        this.fecha_apertura = fecha_apertura;
        this.identificacion_tecnico = identificacion_tecnico;
        this.nombre_tecnico = nombre_tecnico;
        this.sucursal = sucursal;
        this.direccion = direccion;
        this.asunto = asunto;
        this.obs = obs;
    }
    
    
    
}
