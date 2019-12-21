/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Facade;


import com.mycompany.apphelpu.Model.MenuServicio;
import java.util.List;

/**
 *
 * @author usuario
 * @param <E>
 */
public interface IMenuServicio<E> {
    
     MenuServicio addMenuServicio(MenuServicio perfil);
    void deleteMenuServicioId(int i);
    MenuServicio updateMenuServicioById(E param);
    
    List<MenuServicio> getAllMenuServicio(int user);
    List<MenuServicio> getAllMenuServicio_Submenu(int user, int idmenu);
    
}
