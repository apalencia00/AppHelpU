/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Model;

/**
 *
 * @author Monitoreo
 */
public class Notificacion {
    
    private int id_noti;
    private String descripcion;
    private String texto;
    private String estado;

    public Notificacion(int id_noti, String descripcion, String texto, String estado) {
        this.id_noti = id_noti;
        this.descripcion = descripcion;
        this.texto = texto;
        this.estado = estado;
    }

    public Notificacion(String descripcion, String texto, String estado) {
        this.descripcion = descripcion;
        this.texto = texto;
        this.estado = estado;
    }

    public int getId_noti() {
        return id_noti;
    }

    public void setId_noti(int id_noti) {
        this.id_noti = id_noti;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
