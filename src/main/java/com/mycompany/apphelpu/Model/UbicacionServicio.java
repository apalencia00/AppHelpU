/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Model;

/**
 *
 * @author usuario
 */
public class UbicacionServicio {
    
    private int id;
    private int punto;
    private String nombre;
    private String direccion;
    private String tipo_negocio;
    private String recurso;
    private String latitud;
    private String longitud;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo_negocio() {
        return tipo_negocio;
    }

    public void setTipo_negocio(String tipo_negocio) {
        this.tipo_negocio = tipo_negocio;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    
    

    public UbicacionServicio() {
    }

    public UbicacionServicio(int id, int punto, String nombre, String direccion, String tipo_negocio, String recurso, String latitud, String longitud) {
        this.id = id;
        this.punto = punto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipo_negocio = tipo_negocio;
        this.recurso = recurso;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public UbicacionServicio(String nombre,String recurso, String latitud, String longitud, String direccion) {
        this.nombre = nombre;
        this.recurso = recurso;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
    }

    public UbicacionServicio(int punto, String nombre, String direccion, String recurso, String latitud) {
        this.punto = punto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.recurso = recurso;
        this.latitud = latitud;
    }
    
    
    
    
    
}
