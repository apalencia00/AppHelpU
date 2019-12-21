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
public class PerfilUsuario {
    
     private int id;
    private String descripcion;
    private String icon;
    private String estado;
    private int fk_usuario;
    private String acceso;

    public PerfilUsuario(int id, String descripcion, String icon, String estado, int fk_usuario, String acceso) {
        this.id = id;
        this.descripcion = descripcion;
        this.icon = icon;
        this.estado = estado;
        this.fk_usuario = fk_usuario;
        this.acceso = acceso;
    }

    public PerfilUsuario(String descripcion, String icon, String estado, int fk_usuario, String acceso) {
        this.descripcion = descripcion;
        this.icon = icon;
        this.estado = estado;
        this.fk_usuario = fk_usuario;
        this.acceso = acceso;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(int fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }
    
    
    
}
