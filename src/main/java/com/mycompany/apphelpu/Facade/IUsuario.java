/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Facade;


import com.mycompany.apphelpu.Model.Usuario;
import com.mycompany.apphelpu.Util.Resultado;
import java.util.List;
import javax.ws.rs.core.Response;

/**
 *
 * @author usuario
 * @param <T>
 * @param <E>
 */
public interface IUsuario<T,E> {
    
    
    
    void deleteUsuarioById(int i);
    Usuario updateUsuarioById(E param);
    Usuario getUsuarioByCriteria(T param);
    List<Usuario> getAllUsuarios();
    Resultado addUsuario(int tipodoc,String documento,String usuario,String nombre, String apellido,int perfil,String estado);
    
}
