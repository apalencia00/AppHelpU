/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Facade;

import com.mycompany.apphelpu.Model.Tecnico;
import com.mycompany.apphelpu.Util.Resultado;
import org.apache.poi.ss.formula.functions.T;



/**
 *
 * @author usuario
 */
public interface ITecnicos<T,E> {
    
    
     Resultado crearTecnico(Tecnico tec);
     int borrarTecnico();
     Tecnico actualizarTecnico();
     java.util.List<Tecnico> listarTecnicos(String idserv);
    
}
