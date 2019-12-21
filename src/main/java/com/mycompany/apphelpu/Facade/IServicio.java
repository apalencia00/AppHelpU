/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apphelpu.Facade;


import com.mycompany.apphelpu.Model.AsignarServicio;
import com.mycompany.apphelpu.Model.Servicio;
import com.mycompany.apphelpu.Util.Resultado;
import java.util.List;

/**
 *
 * @author usuario
 * @param <T>
 * @param <E>
 */
public interface IServicio<T,E> {
    
    Resultado  addServicio(Servicio s);
    Resultado  asignarServicio(AsignarServicio asg);
    Resultado  deleteServicio(T id);
    Servicio updateServicio(T serv);
    List listServicios();
    List listServiciosByCriteria(T id);
    int  lastServicio();
    int  obtenerIdServicio(T id);
    List listAsuntos();
    Servicio getServicio(int os);
    
}
