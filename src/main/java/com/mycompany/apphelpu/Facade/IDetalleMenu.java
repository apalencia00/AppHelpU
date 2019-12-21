/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Facade;


import com.mycompany.apphelpu.Model.MenuPermisos;
import java.util.List;

/**
 *
 * @author usuario
 * @param <T>
 * @param <E>
 */
public interface IDetalleMenu <T,E> {
    
        void deleteDetalle_Menu_PermisosById(int i);
        MenuPermisos updateClienteById(E param);
        List<MenuPermisos> getAllDetalle_Menu_Permisos(E param);
    
}
