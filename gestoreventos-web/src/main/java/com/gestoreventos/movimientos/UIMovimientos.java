/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.movimientos;

import com.gestoreventos.publico.*;
import com.gestoreventos.controller.GestorGeneral;

import com.gestoreventos.entity.UtilJSF;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.movimientos.controlador.GestorMovimiento;
import com.gestoreventos.publico.controlador.GestorArticulo;
import com.gestoreventos.publico.controlador.GestorUbicacion;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author fjvc
 */
@ManagedBean(name = "uiMovimientos")
@SessionScoped

public class UIMovimientos {    

    private boolean guardarActivo = false;
    private boolean nuevoActivo = true;
    private boolean eliminarActivo = false;
    private boolean cancelarActivo = false;
    private boolean consultarActivo = false;
    private boolean volverActivo = false;    
    
    
    private Articulo articulo;
    private Inventario inventario;
    private ArrayList<Articulo> articulosList=new ArrayList<>();
    private ArrayList<Inventario> inventariosList=new ArrayList<>();
    private ArrayList<Marca> marcasList=new ArrayList<>();
    private ArrayList<EstadoArticulo> estadoArticuloList=new ArrayList<>();
    private ArrayList<Ubicacion> ubicacionesList=new ArrayList<>();
    
    private GestorArticulo gestorArticulo;
    private GestorMovimiento gestorMovimiento;
    private GestorGeneral gestorGeneral;
    
    

    public void cancelar() {
    }
    
    @PostConstruct
    public void init() {
        this.cargarArticulosList();
        this.cargarMarcasList();
        this.cargarEstadosArticuloList();
        this.cargarUbicacionesList();   
        this.cargarInventariosList();
        articulo=new Articulo();
        inventario=new Inventario();
        inventario.setArticulo(new Articulo());
        inventario.getArticulo().setUbicacion(new Ubicacion());
        inventario.getArticulo().setEdoArticulo(new EstadoArticulo());
    }
    
    
    public UIMovimientos() {        
        articulo=new Articulo();
        inventario=new Inventario();
        inventario.setArticulo(new Articulo());
        inventario.getArticulo().setUbicacion(new Ubicacion());
        inventario.getArticulo().setEdoArticulo(new EstadoArticulo());
        this.cargarArticulosList();
        this.cargarEstadosArticuloList();
        this.cargarInventariosList();
               
        
    }
    
    public void guardarArticulo() throws Exception{        
        
        try {
            
            gestorMovimiento=new GestorMovimiento();
            gestorArticulo=new GestorArticulo();
            gestorGeneral=new GestorGeneral();                        
            
            gestorMovimiento.validarInventario(inventario);
            gestorMovimiento.guardarInventario(inventario);
            
            UtilMSG.addSuccessMsg("Guardado Con Exito");
            inventario=new Inventario();
            
            this.cargarInventariosList();
            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
    }
    
    public void subirInventario(){
        try {            
            inventario=(Inventario) UtilJSF.getBean("varInventario");    
            
            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void eliminarInventario(){
        try {
            
            gestorMovimiento=new GestorMovimiento();            
            
            inventario=(Inventario) UtilJSF.getBean("varInventario");
            gestorMovimiento.eliminarInventario(inventario);
            
            UtilMSG.addSuccessMsg("Eliminado Con Exito");
            inventario=new Inventario();            
            this.cargarInventariosList();
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    

    public void cargarInventariosList(){
        try {
            
            inventariosList=new ArrayList<>();
            gestorMovimiento=new GestorMovimiento();
            inventariosList.addAll(gestorMovimiento.cargarInventariosList());            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    
    public void cargarArticulosList(){
        try {
            articulosList=new ArrayList<>();
            gestorArticulo=new GestorArticulo();
            articulosList.addAll(gestorArticulo.cargarArticulosList());
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void cargarMarcasList(){
        try {
            
            marcasList=new ArrayList<>();
            gestorArticulo=new GestorArticulo();
            marcasList.addAll(gestorArticulo.cargarMarcasList());

        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
    }
    
    public void cargarEstadosArticuloList(){
        try {
            
            estadoArticuloList=new ArrayList<>();
            gestorArticulo=new GestorArticulo();
            estadoArticuloList.addAll(gestorArticulo.cargarEstadosArticuloList());

        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
    }
    
    public void cargarUbicacionesList(){
        try {
            
            ubicacionesList=new ArrayList<>();
            GestorUbicacion gestorUbicacion=new GestorUbicacion();
            ubicacionesList.addAll(gestorUbicacion.cargarUbicacionesList());

        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
    }

    public ArrayList<Inventario> getInventariosList() {
        return inventariosList;
    }

    public void setInventariosList(ArrayList<Inventario> inventariosList) {
        this.inventariosList = inventariosList;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public ArrayList<Marca> getMarcasList() {
        return marcasList;
    }

    public void setMarcasList(ArrayList<Marca> marcasList) {
        this.marcasList = marcasList;
    }

    public ArrayList<EstadoArticulo> getEstadoArticuloList() {
        return estadoArticuloList;
    }

    public void setEstadoArticuloList(ArrayList<EstadoArticulo> estadoArticuloList) {
        this.estadoArticuloList = estadoArticuloList;
    }

    public ArrayList<Ubicacion> getUbicacionesList() {
        return ubicacionesList;
    }

    public void setUbicacionesList(ArrayList<Ubicacion> ubicacionesList) {
        this.ubicacionesList = ubicacionesList;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public ArrayList<Articulo> getArticulosList() {
        return articulosList;
    }

    public void setArticulosList(ArrayList<Articulo> articulosList) {
        this.articulosList = articulosList;
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
