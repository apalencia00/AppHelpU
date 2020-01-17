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
public class OpcionesUsuario {
    
    private int id_menu_ppal ;
    private String descripcion_menu;
    private int id_menu_secundario;
    private String descripcion_menu_secun;
    private String estado;

    public OpcionesUsuario(int id_menu_ppal, String descripcion_menu, int id_menu_secundario, String descripcion_menu_secun, String estado) {
        this.id_menu_ppal = id_menu_ppal;
        this.descripcion_menu = descripcion_menu;
        this.id_menu_secundario = id_menu_secundario;
        this.descripcion_menu_secun = descripcion_menu_secun;
        this.estado = estado;
    }

    public int getId_menu_ppal() {
        return id_menu_ppal;
    }

    public void setId_menu_ppal(int id_menu_ppal) {
        this.id_menu_ppal = id_menu_ppal;
    }

    public String getDescripcion_menu() {
        return descripcion_menu;
    }

    public void setDescripcion_menu(String descripcion_menu) {
        this.descripcion_menu = descripcion_menu;
    }

    public int getId_menu_secundario() {
        return id_menu_secundario;
    }

    public void setId_menu_secundario(int id_menu_secundario) {
        this.id_menu_secundario = id_menu_secundario;
    }

    public String getDescripcion_menu_secun() {
        return descripcion_menu_secun;
    }

    public void setDescripcion_menu_secun(String descripcion_menu_secun) {
        this.descripcion_menu_secun = descripcion_menu_secun;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
