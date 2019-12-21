/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Facade;

import com.mycompany.apphelpu.Model.Tecnico;



/**
 *
 * @author usuario
 */
public interface ITecnicos {
    
    
    public Tecnico crearTecnico();
    public int borrarTecnico();
    public Tecnico actualizarTecnico();
    public java.util.List<Tecnico> listarTecnicos();
    
}
