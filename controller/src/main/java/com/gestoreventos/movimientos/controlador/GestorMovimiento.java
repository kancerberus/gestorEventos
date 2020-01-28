/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.movimientos.controlador;

import com.gestoreventos.publico.controlador.*;
import com.gestoreventos.controller.Gestor;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.movimientos.Inventario;
import com.gestoreventos.movimientos.dao.MovimientoDAO;
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
public class GestorMovimiento extends Gestor {

    public GestorMovimiento() throws Exception {
        super();
    }

    public void guardarInventario(Inventario inv) throws Exception {
         try {
            this.abrirConexion();
             MovimientoDAO movimientoDAO=new MovimientoDAO(conexion);
             movimientoDAO.guardarInventario(inv);
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarInventario(Inventario inv) {
        
        try {
            
            if(inv.getArticulo()==null){
                UtilMSG.addSuccessMsg("Seleccione Articulo");
            }
            if(inv.getArticulo().getUbicacion()==null){
                UtilMSG.addSuccessMsg("Seleccione Ubicacion");
            }            
            if(inv.getArticulo().getEdoArticulo()==null){
                UtilMSG.addSuccessMsg("Seleccione Estado");
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

    public void eliminarInventario(Inventario inventario) throws Exception {        
         try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO=new MovimientoDAO(conexion);
            movimientoDAO.eliminarInventario(inventario);
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

    public Collection<? extends Inventario> cargarInventariosList() throws Exception {
        try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO = new MovimientoDAO(conexion);
            return movimientoDAO.cargarInventarios();
        } finally {
            this.cerrarConexion();
        }
    }

    
}
