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
import com.gestoreventos.movimientos.Prestamo;
import com.gestoreventos.movimientos.RazonMovimiento;
import com.gestoreventos.movimientos.dao.MovimientoDAO;
import com.gestoreventos.publico.Articulo;
import com.gestoreventos.publico.EstadoArticulo;
import com.gestoreventos.publico.Marca;
import com.gestoreventos.publico.Menu;
import com.gestoreventos.publico.Proveedor;
import com.gestoreventos.publico.Submenu;
import com.gestoreventos.publico.Submenucategoria;
import com.gestoreventos.publico.Ubicacion;
import com.gestoreventos.publico.dao.ArticuloDAO;
import com.gestoreventos.publico.dao.ProveedorDAO;
import com.gestoreventos.publico.dao.UbicacionDAO;
import java.util.Collection;
import java.util.List;


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
            if(inv.getArticulo().getEdoArticulo()==null){
                UtilMSG.addSuccessMsg("Seleccione Estado");
            }
            if(inv.getSerial()=="" || inv.getSerial()==null){
                UtilMSG.addSuccessMsg("Ingrese Serial");
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
    
    public Collection<? extends Inventario> cargarInventariosUbicacionList(Integer codUbicacion) throws Exception {
        try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO = new MovimientoDAO(conexion);
            return movimientoDAO.cargarInventariosUbicacionList(codUbicacion);
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

    public Collection<? extends Submenucategoria> cargaSubmenuCategoria(Submenu submenu) throws Exception {
        try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO = new MovimientoDAO(conexion);
            return movimientoDAO.cargarSubmenucategoria(submenu);
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarPrestamo(Prestamo p) {
        try {
            
            if(p.getInventario().getSerial()==null || p.getInventario().getSerial()==""){
                UtilMSG.addSuccessMsg("Seleccione Articulo Del Inventario");
            }
            if(p.getProveedor()==null){
                UtilMSG.addSuccessMsg("Seleccione proveedor");
            }
            if(p.getFechaDevolucion()==null){
                UtilMSG.addSuccessMsg("Seleccione Fecha De Devolucion");
            }                   
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }

    public void guardarPrestamo(Prestamo prestamo) throws Exception {
        try {
            this.abrirConexion();
             MovimientoDAO movimientoDAO=new MovimientoDAO(conexion);                           
             movimientoDAO.guardarPrestamo(prestamo);
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends Prestamo> cargarPrestamosSalidaList() throws Exception {
        try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO = new MovimientoDAO(conexion);
            return movimientoDAO.cargarPrestamoSalidaList();
        } finally {
            this.cerrarConexion();
        }        
    }
    
    public Collection<? extends Prestamo> cargarPrestamosIngresoList() throws Exception {
        try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO = new MovimientoDAO(conexion);
            return movimientoDAO.cargarPrestamoIngresoList();
        } finally {
            this.cerrarConexion();
        }        
    }

    public Collection<? extends Prestamo> cargarPrestamosTotalList() throws Exception {
        try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO = new MovimientoDAO(conexion);
            return movimientoDAO.cargarPrestamoTotales();
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends Prestamo> cargarPrestamos(String condicionesConsulta) throws Exception {
        try {
            this.abrirConexion();
            MovimientoDAO movimientoDAO = new MovimientoDAO(conexion);
            return movimientoDAO.cargarPrestamos(condicionesConsulta);
        } finally {
            this.cerrarConexion();
        }
    }
    

    
}
