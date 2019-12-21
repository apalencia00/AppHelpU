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
public class MenuServicio {
    
    private int id;
    private String descripcion;
    private String acceso;
    private int usuario;
    private String estilo;
    private String icono;

    public MenuServicio(int id, String descripcion, String acceso, int usuario, String estilo, String icono) {
        this.id = id;
        this.descripcion = descripcion;
        this.acceso = acceso;
        this.usuario = usuario;
        this.estilo = estilo;
        this.icono = icono;
    }

    public MenuServicio(String descripcion, String acceso, int usuario, String estilo, String icono) {
        this.descripcion = descripcion;
        this.acceso = acceso;
        this.usuario = usuario;
        this.estilo = estilo;
        this.icono = icono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
    
    
    
}
