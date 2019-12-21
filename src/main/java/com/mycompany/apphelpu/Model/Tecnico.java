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
public class Tecnico {
    
    private int id;
    private int tipo_identificacion;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String celular;
    private String personal;
    private String ext;
    private int tipo_cargo;

    public Tecnico(int id, int tipo_identificacion, String identificacion, String nombre, String apellido, String celular, String personal, String ext, int tipo_cargo) {
        this.id = id;
        this.tipo_identificacion = tipo_identificacion;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.personal = personal;
        this.ext = ext;
        this.tipo_cargo = tipo_cargo;
    }

    public Tecnico(int tipo_identificacion, String identificacion, String nombre, String apellido, String celular, String personal, String ext, int tipo_cargo) {
        this.tipo_identificacion = tipo_identificacion;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.personal = personal;
        this.ext = ext;
        this.tipo_cargo = tipo_cargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(int tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getTipo_cargo() {
        return tipo_cargo;
    }

    public void setTipo_cargo(int tipo_cargo) {
        this.tipo_cargo = tipo_cargo;
    }
    
    
    
    
}
