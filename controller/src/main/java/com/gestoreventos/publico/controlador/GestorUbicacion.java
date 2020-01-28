/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.controlador;

import com.gestoreventos.controller.Gestor;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.publico.Municipios;
import com.gestoreventos.publico.Ubicacion;
import com.gestoreventos.publico.dao.UbicacionDAO;
import java.util.Collection;


/**
 *
 * @author fjvc
 */
public class GestorUbicacion extends Gestor {

    public GestorUbicacion() throws Exception {
        super();
    }

    public void guardarUbicacion(Ubicacion ubicacion) throws Exception {
         try {
            this.abrirConexion();
             UbicacionDAO ubicacionDAO = new UbicacionDAO(conexion);
             ubicacionDAO.guardarUbicacion(ubicacion);
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarUbicacion(Ubicacion ubicacion) {
        
        try {
            
            if(ubicacion.getNombre()==""){
                UtilMSG.addSuccessMsg("Digite Nombre");
            }
            if(ubicacion.getDireccion()==""){
                UtilMSG.addSuccessMsg("Digite Direccion");
            }            
            if(ubicacion.getTelefono()==""){
                UtilMSG.addSuccessMsg("Digite Telefono");
            }            
            if(ubicacion.getEstado()==null){
                UtilMSG.addSuccessMsg("Seleccione Estado");
            }   
            if(ubicacion.getMunicipio()==null){
                UtilMSG.addSuccessMsg("Seleccione Ciudad");
            }
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), e);
        }
        
    }


    public Collection<? extends Ubicacion> cargarUbicacionesList() throws Exception {
        try {
            this.abrirConexion();
            UbicacionDAO ubicacionDAO = new UbicacionDAO(conexion);
            return ubicacionDAO.cargarUbicaciones();
        } finally {
            this.cerrarConexion();
        }
    }

    public void eliminarUbicacion(Ubicacion ubicacion) throws Exception {        
         try {
            this.abrirConexion();
             UbicacionDAO ubicacionDAO = new UbicacionDAO(conexion);
             ubicacionDAO.eliminarUbicacion(ubicacion);
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends Municipios> cargarMunicipiosList() throws Exception {
        try {
            this.abrirConexion();
            UbicacionDAO ubicacionDAO = new UbicacionDAO(conexion);
            return ubicacionDAO.cargarMunicipioslist();
        } finally {
            this.cerrarConexion();
        }
    }

    
}
