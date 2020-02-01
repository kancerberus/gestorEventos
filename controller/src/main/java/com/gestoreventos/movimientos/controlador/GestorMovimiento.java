/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.movimientos.controlador;

import com.gestoreventos.publico.controlador.*;
import com.gestoreventos.controller.Gestor;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.movimientos.HistoricoMovimientos;
import com.gestoreventos.movimientos.Inventario;
import com.gestoreventos.movimientos.RazonMovimiento;
import com.gestoreventos.movimientos.dao.MovimientoDAO;
import com.gestoreventos.publico.Articulo;
import com.gestoreventos.publico.EstadoArticulo;
import com.gestoreventos.publico.Marca;
import com.gestoreventos.publico.Menu;
import com.gestoreventos.publico.Proveedor;
import com.gestoreventos.publico.Submenu;
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

    /*public void guardarInventario(Inventario inv) throws Exception {
         try {
            this.abrirConexion();
             MovimientoDAO movimientoDAO=new MovimientoDAO(conexion);             
             if(inv.getHistoricoMovimiento().getCodMoviento()==null){
                movimientoDAO.ingresoPrimerHistorico(inv);
             }             
             movimientoDAO.guardarInventario(inv);
        } finally {
            this.cerrarConexion();
        }
    }*/

    public void validarInventario(Inventario inv) {
        
        try {
            
            if(inv.getArticulo()==null){
                UtilMSG.addSuccessMsg("Seleccione Articulo");
            }             
            if(inv.getArticulo().getEdoArticulo()==null){
                UtilMSG.addSuccessMsg("Seleccione Estado");
            }
            if(inv.getUltMantenimiento()==null){
                UtilMSG.addSuccessMsg("Seleccione Fecha Ultimo Mantenimiento");
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

    public Collection<? extends RazonMovimiento> cargarRazonesMovimiento() throws Exception {
        try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO = new MovimientoDAO(conexion);
            return movimientoDAO.cargarRazonMovimiento();
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarMantenimiento(HistoricoMovimientos hm) {
        
        try {
            
            if(hm.getArticulo()==null){
                UtilMSG.addSuccessMsg("Seleccione Articulo");
            }
            if(hm.getTipoMovimiento()==null){
                UtilMSG.addSuccessMsg("Seleccione Tipo Movimiento");
            }            
            if(hm.getFechaMovimiento()==null){
                UtilMSG.addSuccessMsg("Seleccione Fecha Movimiento");
            }
            if(hm.getArticulo().getEdoArticulo()==null){
                UtilMSG.addSuccessMsg("Seleccione Estado Articulo");
            }
            if(hm.getProveedor()==null){
                UtilMSG.addSuccessMsg("Seleccione Proveedor");
            }           
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }

    public void guardarMantenimiento(HistoricoMovimientos historicoMovimiento) throws Exception {
        
        try {
            this.abrirConexion();
             MovimientoDAO movimientoDAO=new MovimientoDAO(conexion);                          
             movimientoDAO.guardarMantnimiento(historicoMovimiento);
        } finally {
            this.cerrarConexion();
        }
        
    }

    public Collection<? extends HistoricoMovimientos> cargarMantenimientosList() throws Exception {
        try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO = new MovimientoDAO(conexion);
            return movimientoDAO.cargarMantenimientosList();
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends Menu> cargarItemsMenu() throws Exception {
        try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO = new MovimientoDAO(conexion);
            return movimientoDAO.cargaritemsMenuList();
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends Submenu> cargarSubmenu(Menu menu) throws Exception {
        try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO = new MovimientoDAO(conexion);
            return movimientoDAO.cargaritemsSubmenuList(menu);
        } finally {
            this.cerrarConexion();
        }        
    }

    public String cargarDireccionDialogo(Submenu submenu) throws Exception {
        try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO = new MovimientoDAO(conexion);
            return movimientoDAO.cargarDireccionDialogo(submenu);
        } finally {
            this.cerrarConexion();
        }
    }

    
}
