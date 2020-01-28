/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico;

import com.gestoreventos.controller.GestorGeneral;
import com.gestoreventos.entity.UtilJSF;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.publico.controlador.GestorUbicacion;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author fjvc
 */
@ManagedBean(name = "uiUbicacion")
@SessionScoped

public class UIUbicacion {    

    private boolean guardarActivo = false;
    private boolean nuevoActivo = true;
    private boolean eliminarActivo = false;
    private boolean cancelarActivo = false;
    private boolean consultarActivo = false;
    private boolean volverActivo = false;    
    
    
    private Ubicacion ubicacion;
    private ArrayList<Ubicacion> ubicacionesList=new ArrayList<>();
    private ArrayList<Municipios> municipiosList=new ArrayList<>();
    
    private GestorUbicacion gestorUbicacion;
    private GestorGeneral gestorGeneral;
    
    

    public void cancelar() {
    }
    
    @PostConstruct
    public void init() {
        this.cargarUbicacionesList();
        this.cargarMunicipios();
        
    }
    
    
    public UIUbicacion() {          
        
        ubicacion=new Ubicacion();
        this.cargarUbicacionesList();
        this.cargarMunicipios();
               
        
    }
    
    public void cargarMunicipios(){
        try {
            
            gestorUbicacion=new GestorUbicacion();
            
            municipiosList.addAll(gestorUbicacion.cargarMunicipiosList());
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void guardarUbicacion() throws Exception{        
        
        try {
            
            gestorUbicacion=new GestorUbicacion();
            gestorGeneral=new GestorGeneral();            
            
            if(ubicacion.getCodUbicacion()==null){
                ubicacion.setCodUbicacion(gestorGeneral.nextval(GestorGeneral.UBICACION_COD_UBICACION_SEQ).intValue());
            }
            
            
            gestorUbicacion.validarUbicacion(ubicacion);
            gestorUbicacion.guardarUbicacion(ubicacion);
            
            UtilMSG.addSuccessMsg("Guardado Con Exito");
            ubicacion=new Ubicacion();
            
            this.cargarUbicacionesList();
            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
    }
    
    public void subirUbicacion(){
        try {
            
            ubicacion=(Ubicacion) UtilJSF.getBean("varUbicacion");            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void eliminarUbicacion(){
        try {
            
            gestorUbicacion=new GestorUbicacion();
            
            ubicacion=(Ubicacion) UtilJSF.getBean("varUbicacion");                        
            gestorUbicacion.eliminarUbicacion(ubicacion);
            
            UtilMSG.addSuccessMsg("Eliminado Con Exito");
            ubicacion=new Ubicacion();
            this.cargarUbicacionesList();
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void cargarUbicacionesList(){
        try {
            ubicacionesList=new ArrayList<>();
            gestorUbicacion=new GestorUbicacion();
            ubicacionesList.addAll(gestorUbicacion.cargarUbicacionesList());
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }

    public ArrayList<Municipios> getMunicipiosList() {
        return municipiosList;
    }

    public void setMunicipiosList(ArrayList<Municipios> municipiosList) {
        this.municipiosList = municipiosList;
    }
    
    

    public ArrayList<Ubicacion> getUbicacionesList() {
        return ubicacionesList;
    }

    public void setUbicacionesList(ArrayList<Ubicacion> ubicacionesList) {
        this.ubicacionesList = ubicacionesList;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
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
