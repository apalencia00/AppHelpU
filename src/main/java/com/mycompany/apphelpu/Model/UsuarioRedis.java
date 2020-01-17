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
public class UsuarioRedis {
    
       private String idusuarioredis ;
       private int perfil         ;
       private String session       ;
       private String usersystem;
       private String fecha_sesion  ;

    public UsuarioRedis(String idusuarioredis, String session, String usersystem ,String fecha_sesion,int perfil) {
        
        this.idusuarioredis = idusuarioredis;
        this.session = session;
        this.usersystem = usersystem;
        this.fecha_sesion = fecha_sesion;
        this.perfil = perfil;
    }

    public String getIdusuarioredis() {
        return idusuarioredis;
    }

    public void setIdusuarioredis(String idusuarioredis) {
        this.idusuarioredis = idusuarioredis;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getFecha_sesion() {
        return fecha_sesion;
    }

    public void setFecha_sesion(String fecha_sesion) {
        this.fecha_sesion = fecha_sesion;
    }

    public String getUsersystem() {
        return usersystem;
    }

    public void setUsersystem(String usersystem) {
        this.usersystem = usersystem;
    }
       
       
    
}
