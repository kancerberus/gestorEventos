/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico;

import com.gestoreventos.entity.UtilJSF;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.publico.controlador.GestorEmpleado;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author fjvc
 */
@ManagedBean(name = "uiEmpleado")
@SessionScoped

public class UIEmpleado {
    
    
    GestorEmpleado gestorEmpleado;

    private boolean guardarActivo = false;
    private boolean nuevoActivo = true;
    private boolean eliminarActivo = false;
    private boolean cancelarActivo = false;
    private boolean consultarActivo = false;
    private boolean volverActivo = false;
    
    private ArrayList<Cargo> cargosList=new ArrayList<>();
    private ArrayList<Empleado> empleadosList=new ArrayList<>();
    
    private Empleado empleado;
    

    public void cancelar() {
    }
    
    @PostConstruct
    public void init() {
        this.cargarEmpleadosList();
    }
    
    
    public UIEmpleado() {          
        
        empleado=new Empleado();
        cargarCargosList();
        cargarEmpleadosList();
        
    }
    
    public void guardarEmpleado() throws Exception{        
        
        try {
            
            gestorEmpleado=new GestorEmpleado();
            gestorEmpleado.validarEmpleado(empleado);
            gestorEmpleado.guardarEmpleado(empleado);
            
            UtilMSG.addSuccessMsg("Guardado Con Exito");
            empleado=new Empleado();
            
            this.cargarEmpleadosList();
            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
        
        
        
    }
    
    public void subirEmpleado(){
        try {
            
            empleado=(Empleado) UtilJSF.getBean("varEmpleado");            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void eliminarEmpleado(){
        try {
            
            gestorEmpleado=new GestorEmpleado();
            
            empleado=(Empleado) UtilJSF.getBean("varEmpleado");                        
            gestorEmpleado.eliminarEmpleado(empleado);
            
            UtilMSG.addSuccessMsg("Eliminado Con Exito");
            empleado=new Empleado();
            this.cargarEmpleadosList();
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    
    public void cargarCargosList(){
        try {
            
            gestorEmpleado=new GestorEmpleado();
            cargosList=gestorEmpleado.cargarCargos();
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void cargarEmpleadosList(){
        try {
            empleadosList=new ArrayList<>();
            gestorEmpleado=new GestorEmpleado();
            empleadosList.addAll(gestorEmpleado.cargarEmpleadosList());
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }

    public ArrayList<Empleado> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(ArrayList<Empleado> empleadosList) {
        this.empleadosList = empleadosList;
    }

    public ArrayList<Cargo> getCargosList() {
        return cargosList;
    }

    public void setCargosList(ArrayList<Cargo> cargosList) {
        this.cargosList = cargosList;
    }
    

    public boolean isGuardarActivo() {
        return guardarActivo;
    }

    public void setGuardarActivo(boolean guardarActivo) {
        this.guardarActivo = guardarActivo;
    }

    public boolean isNuevoActivo() {
        return nuevoActivo;
    }

    public void setNuevoActivo(boolean nuevoActivo) {
        this.nuevoActivo = nuevoActivo;
    }

    public boolean isEliminarActivo() {
        return eliminarActivo;
    }

    public void setEliminarActivo(boolean eliminarActivo) {
        this.eliminarActivo = eliminarActivo;
    }

    public boolean isCancelarActivo() {
        return cancelarActivo;
    }

    public void setCancelarActivo(boolean cancelarActivo) {
        this.cancelarActivo = cancelarActivo;
    }

    public boolean isConsultarActivo() {
        return consultarActivo;
    }

    public void setConsultarActivo(boolean consultarActivo) {
        this.consultarActivo = consultarActivo;
    }

    public boolean isVolverActivo() {
        return volverActivo;
    }

    public void setVolverActivo(boolean volverActivo) {
        this.volverActivo = volverActivo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    

}
