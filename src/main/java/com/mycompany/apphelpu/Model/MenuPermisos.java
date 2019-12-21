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
public class MenuPermisos {
    
     private int id;
    private int usuario;
    private int menu_servicio;
    private int submenu_servicio;
    private String estado;
    private String fecha;

    public MenuPermisos(int id, int usuario, int menu_servicio, int submenu_servicio, String estado, String fecha) {
        this.id = id;
        this.usuario = usuario;
        this.menu_servicio = menu_servicio;
        this.submenu_servicio = submenu_servicio;
        this.estado = estado;
        this.fecha = fecha;
    }

    public MenuPermisos(int usuario, int menu_servicio, int submenu_servicio, String estado, String fecha) {
        this.usuario = usuario;
        this.menu_servicio = menu_servicio;
        this.submenu_servicio = submenu_servicio;
        this.estado = estado;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getMenu_servicio() {
        return menu_servicio;
    }

    public void setMenu_servicio(int menu_servicio) {
        this.menu_servicio = menu_servicio;
    }

    public int getSubmenu_servicio() {
        return submenu_servicio;
    }

    public void setSubmenu_servicio(int submenu_servicio) {
        this.submenu_servicio = submenu_servicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
    

    
}
