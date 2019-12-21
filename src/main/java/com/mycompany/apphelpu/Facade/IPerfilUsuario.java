/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Facade;


import com.mycompany.apphelpu.Model.PerfilUsuario;
import com.mycompany.apphelpu.Model.Usuario;
import java.util.List;

/**
 *
 * @author usuario
 * @param <T>
 */
public interface IPerfilUsuario<T> {
    
    PerfilUsuario addPerfilOpcion(PerfilUsuario perfil);
    void deletePerfilOpcionId(int i);
    Usuario loginUsuario(String user, String pass);
    
    List<PerfilUsuario> getAllPerfilOpcion(T param);
    
}
