/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico;

import com.gestoreventos.controller.GestorGeneral;

import com.gestoreventos.entity.UtilJSF;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.publico.controlador.GestorProveedor;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author fjvc
 */
@ManagedBean(name = "uiProveedor")
@SessionScoped

public class UIProveedor {    

    private boolean guardarActivo = false;
    private boolean nuevoActivo = true;
    private boolean eliminarActivo = false;
    private boolean cancelarActivo = false;
    private boolean consultarActivo = false;
    private boolean volverActivo = false;    
    
    
    private Proveedor proveedor;
    private ArrayList<Proveedor> proveedoresList=new ArrayList<>();
    
    private GestorProveedor gestorProveedor;
    private GestorGeneral gestorGeneral;
    
    

    public void cancelar() {
    }
    
    @PostConstruct
    public void init() {
        this.cargarProveedoresList();
        
    }
    
    
    public UIProveedor() {          
        
        proveedor=new Proveedor();  
        this.cargarProveedoresList();
        
    }
    
    public void guardarProveedor() throws Exception{        
        
        try {
            
            gestorProveedor=new GestorProveedor();
            gestorGeneral=new GestorGeneral();            
            
            if(proveedor.getCodProveedor()==null){
                proveedor.setCodProveedor(gestorGeneral.nextval(GestorGeneral.UBICACION_COD_UBICACION_SEQ).intValue());
            }
            
            
            gestorProveedor.validarProveedor(proveedor);
            gestorProveedor.guardarProveedor(proveedor);
            
            
            UtilMSG.addSuccessMsg("Guardado Con Exito");
            proveedor=new Proveedor();
            
            this.cargarProveedoresList();
            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
    }
    
    public void subirProveedor(){
        try {
            
            proveedor=(Proveedor) UtilJSF.getBean("varProveedor");            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void eliminarProveedor(){
        try {
            
            gestorProveedor=new GestorProveedor();
            
            proveedor=(Proveedor) UtilJSF.getBean("varProveedor");                        
            gestorProveedor.eliminarProveedor(proveedor);
            
            UtilMSG.addSuccessMsg("Eliminado Con Exito");
            proveedor=new Proveedor();
            this.cargarProveedoresList();
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void cargarProveedoresList(){
        try {
            proveedoresList=new ArrayList<>();
            gestorProveedor=new GestorProveedor();
            proveedoresList.addAll(gestorProveedor.cargarProveedoresList());
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public ArrayList<Proveedor> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(ArrayList<Proveedor> proveedoresList) {
        this.proveedoresList = proveedoresList;
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
    

}
