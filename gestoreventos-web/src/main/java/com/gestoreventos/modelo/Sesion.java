package com.gestoreventos.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.gestoreventos.controller.GestorGeneral;



import com.gestoreventos.publico.Usuarios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author fjvc
 */
@ManagedBean
@SessionScoped
public class Sesion {

    private Usuarios usuarios;

    private boolean logueado;
    private HashMap configuracion = new HashMap();
    private HashMap<Integer, Boolean> permisos = new HashMap<>();    

    
    /**
     * @return the usuarios
     */
    public Usuarios getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the logueado
     */
    public boolean isLogueado() {
        return logueado;
    }

    /**
     * @param logueado the logueado to set
     */
    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }

    /**
     * @return the permisos
     */
    public HashMap<Integer, Boolean> getPermisos() {
        return permisos;
    }

    /**
     * @param permisos the permisos to set
     */
    public void setPermisos(HashMap<Integer, Boolean> permisos) {
        this.permisos = permisos;
    }

    

    /**
     * @return the configuracion
     */
    public HashMap getConfiguracion() {
        return configuracion;
    }

    /**
     * @param configuracion the configuracion to set
     */
    public void setConfiguracion(HashMap configuracion) {
        this.configuracion = configuracion;
    }    
    /**
     * @return the establecimientoList
     */


}
