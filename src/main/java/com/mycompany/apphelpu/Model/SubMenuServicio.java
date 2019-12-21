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
public class SubMenuServicio {
    
    private int id_sbmenu;
    private String descripcion_sbmenu;
    private String acceso;
    private int fk_menu_servicio;
    private int fk_usuario;
    private boolean estado;
    private String icono;

    public SubMenuServicio(int id_sbmenu, String descripcion_sbmenu,int fk_menu_servicio, String acceso, String icono) {
        this.id_sbmenu = id_sbmenu;
        this.descripcion_sbmenu = descripcion_sbmenu;
        this.acceso = acceso;
        this.fk_menu_servicio = fk_menu_servicio;
        this.icono = icono;
    }
    
    
    public SubMenuServicio(String descripcion_sbmenu,int fk_menu_servicio, String acceso, String icono) {
        
        this.descripcion_sbmenu = descripcion_sbmenu;
        this.fk_menu_servicio = fk_menu_servicio;
        this.acceso = acceso;
        this.fk_menu_servicio = fk_menu_servicio;
        this.icono = icono;
    }
    

    
    

    public int getId_sbmenu() {
        return id_sbmenu;
    }

    public void setId_sbmenu(int id_sbmenu) {
        this.id_sbmenu = id_sbmenu;
    }

    public String getDescripcion_sbmenu() {
        return descripcion_sbmenu;
    }

    public void setDescripcion_sbmenu(String descripcion_sbmenu) {
        this.descripcion_sbmenu = descripcion_sbmenu;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public int getFk_menu_servicio() {
        return fk_menu_servicio;
    }

    public void setFk_menu_servicio(int fk_menu_servicio) {
        this.fk_menu_servicio = fk_menu_servicio;
    }

    public int getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(int fk_usuario) {
        this.fk_usuario = fk_usuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
    
    
    
}
