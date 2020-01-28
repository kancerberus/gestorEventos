/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico;

import com.gestoreventos.entity.UtilJSF;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.publico.controlador.GestorCliente;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author fjvc
 */
@ManagedBean(name = "uiCliente")
@SessionScoped

public class UICliente {
    
    
    

    private boolean guardarActivo = false;
    private boolean nuevoActivo = true;
    private boolean eliminarActivo = false;
    private boolean cancelarActivo = false;
    private boolean consultarActivo = false;
    private boolean volverActivo = false;    
    
    
    private Cliente cliente;
    private GestorCliente gestorCliente;
    private ArrayList<Cliente> clientesList=new ArrayList<>();
    

    public void cancelar() {
    }
    
    @PostConstruct
    public void init() {
        this.cargarClientesList();
        
    }
    
    
    public UICliente() {          
        
        cliente=new Cliente();
        this.cargarClientesList();
        
               
        
    }
    
    public void guardarCliente() throws Exception{        
        
        try {
            
            gestorCliente=new GestorCliente();
            gestorCliente.validarCliente(cliente);
            gestorCliente.guardarCliente(cliente);
            
            UtilMSG.addSuccessMsg("Guardado Con Exito");
            cliente=new Cliente();
            
            this.cargarClientesList();
            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
        
        
        
    }
    
    public void subirCliente(){
        try {
            
            cliente=(Cliente) UtilJSF.getBean("varCliente");            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void eliminarCliente(){
        try {
            
            gestorCliente=new GestorCliente();
            
            cliente=(Cliente) UtilJSF.getBean("varCliente");                        
            gestorCliente.eliminarClientes(cliente);
            
            UtilMSG.addSuccessMsg("Eliminado Con Exito");
            cliente=new Cliente();
            this.cargarClientesList();
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void cargarClientesList(){
        try {
            clientesList=new ArrayList<>();
            gestorCliente=new GestorCliente();
            clientesList.addAll(gestorCliente.cargarClientesList());
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }

    public ArrayList<Cliente> getClientesList() {
        return clientesList;
    }

    public void setClientesList(ArrayList<Cliente> clientesList) {
        this.clientesList = clientesList;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    
    

}
