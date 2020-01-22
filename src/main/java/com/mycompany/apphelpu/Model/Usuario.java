/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Model;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class Usuario {
    
     private int id;
    private String tipo_doc;
    private String documento;
    private String usuarioacc;
    private String nombre;
    private String apellido;
    private Date fecha_creacion;
    private char sexo;
    private String telefono;
    private int tipo_perfil;
    private String estado;
    private String sucursal;
    private String direccion;
    private int punto_venta;

    public int getPunto_venta() {
        return punto_venta;
    }

    public void setPunto_venta(int punto_venta) {
        this.punto_venta = punto_venta;
    }
    
    

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
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
    
    

    public Usuario(int id, String tipo_doc, String documento, String usuarioacc, String nombre, String apellido, Date fecha_creacion, char sexo, String telefono, int tipo_perfil, String estado) {
        this.id = id;
        this.tipo_doc = tipo_doc;
        this.documento = documento;
        this.usuarioacc = usuarioacc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_creacion = fecha_creacion;
        this.sexo = sexo;
        this.telefono = telefono;
        this.tipo_perfil = tipo_perfil;
        this.estado = estado;
    }

    public Usuario(String tipo_doc, String documento, String usuarioacc, String nombre, String apellido, int tipo_perfil, String estado) {
        this.tipo_doc = tipo_doc;
        this.documento = documento;
        this.usuarioacc = usuarioacc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_perfil = tipo_perfil;
        this.estado = estado;
    }
    
    
        public Usuario(int id,String tipo_doc, String documento, String usuarioacc, String nombre, String apellido, int tipo_perfil, String estado) {
        
        this.id = id;
        this.tipo_doc = tipo_doc;
        this.documento = documento;
        this.usuarioacc = usuarioacc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_perfil = tipo_perfil;
        this.estado = estado;
    }
    
     public Usuario(String documento, String nombre, String sucursal, String direccion_servicio, int punto) {
       
        this.documento = documento;
        this.nombre = nombre;
        this.sucursal = sucursal;
        this.direccion = direccion_servicio;
        this.punto_venta = punto;
        
    }
    
        public Usuario(int id,String tipo_doc, String documento, String usuarioacc, String nombre, String apellido, String estado,int tipo_perfil) {
        
        this.id = id;
        this.tipo_doc = tipo_doc;
        this.documento = documento;
        this.usuarioacc = usuarioacc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
        this.tipo_perfil = tipo_perfil;
        
    }
        
       public Usuario(int id,String tipo_doc, String documento, String usuarioacc, String nombre, String apellido, String estado,int tipo_perfil, String sucursal) {
        
        this.id = id;
        this.tipo_doc = tipo_doc;
        this.documento = documento;
        this.usuarioacc = usuarioacc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
        this.tipo_perfil = tipo_perfil;
        this.sucursal = sucursal;
        
    }

    public Usuario() {
    }
        
        
    
    
   
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(int String) {
        this.tipo_doc = tipo_doc;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getUsuarioacc() {
        return usuarioacc;
    }

    public void setUsuarioacc(String usuarioacc) {
        this.usuarioacc = usuarioacc;
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

    public Date getFecha_Creacion() {
        return fecha_creacion;
    }

    public void setFecha_Creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getTipo_perfil() {
        return tipo_perfil;
    }

    public void setTipo_perfil(int tipo_perfil) {
        this.tipo_perfil = tipo_perfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
