/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.controlador;

import com.gestoreventos.controller.Gestor;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.publico.Articulo;
import com.gestoreventos.publico.EstadoArticulo;
import com.gestoreventos.publico.Marca;
import com.gestoreventos.publico.Proveedor;
import com.gestoreventos.publico.Ubicacion;
import com.gestoreventos.publico.dao.ArticuloDAO;
import com.gestoreventos.publico.dao.ProveedorDAO;
import com.gestoreventos.publico.dao.UbicacionDAO;
import java.util.Collection;


/**
 *
 * @author fjvc
 */
public class GestorArticulo extends Gestor {

    public GestorArticulo() throws Exception {
        super();
    }

    public void guardarArticulo(Articulo articulo) throws Exception {
         try {
            this.abrirConexion();
             ArticuloDAO articuloDAO = new ArticuloDAO(conexion);
             articuloDAO.guardarArticulo(articulo);
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarArticulo(Articulo articulo) {
        
        try {
            
            if(articulo.getDescripcion()==""){
                UtilMSG.addSuccessMsg("Digite Descripcion articulo");
            }
            if(articulo.getMarca()==null){
                UtilMSG.addSuccessMsg("Seleccione Marca");
            }            
            if(articulo.getDescripcion()==""){
                UtilMSG.addSuccessMsg("Digite Descripcion");
            }            
            if(articulo.getModelo()==""){
                UtilMSG.addSuccessMsg("Digite Modelo Articulo");
            }
            if(articulo.getSerial()==""){
                UtilMSG.addSuccessMsg("Digite Serial Articulo");
            }            
            if(articulo.getFechaCompra()== null){
                UtilMSG.addSuccessMsg("Seleccione Fecha de Compra Articulo");
            }
            if(articulo.getUltMantenimiento()==null){
                UtilMSG.addSuccessMsg("Seleccione Fecha Ultimo Mantenimiento Articulo");
            }
            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), e);
        }
        
    }


    public Collection<? extends Articulo> cargarArticulosList() throws Exception {
        try {
            this.abrirConexion();
            ArticuloDAO articuloDAO = new ArticuloDAO(conexion);
            return articuloDAO.cargarArticulos();
        } finally {
            this.cerrarConexion();
        }
    }

    public void eliminarArticulos(Articulo articulo) throws Exception {        
         try {
            this.abrirConexion();
            ArticuloDAO articuloDAO = new ArticuloDAO(conexion);
            articuloDAO.eliminarArticulo(articulo);
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends Marca> cargarMarcasList() throws Exception {
        try {
            this.abrirConexion();
            ArticuloDAO articuloDAO = new ArticuloDAO(conexion);
            return articuloDAO.cargarMarcasList();
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends EstadoArticulo> cargarEstadosArticuloList() throws Exception {
        try {
            this.abrirConexion();
            ArticuloDAO articuloDAO = new ArticuloDAO(conexion);
            return articuloDAO.cargarEstadoArticulos();
        } finally {
            this.cerrarConexion();
        }
    }

    
}
