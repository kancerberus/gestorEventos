/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.controlador;

import com.gestoreventos.controller.Gestor;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.publico.Proveedor;
import com.gestoreventos.publico.Ubicacion;
import com.gestoreventos.publico.dao.ProveedorDAO;
import com.gestoreventos.publico.dao.UbicacionDAO;
import java.util.Collection;


/**
 *
 * @author fjvc
 */
public class GestorProveedor extends Gestor {

    public GestorProveedor() throws Exception {
        super();
    }

    public void guardarProveedor(Proveedor proveedor) throws Exception {
         try {
            this.abrirConexion();
             ProveedorDAO proveedorDAO = new ProveedorDAO(conexion);
             proveedorDAO.guardarProveedor(proveedor);
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarProveedor(Proveedor proveedor) {
        
        try {
            
            if(proveedor.getNombre()==""){
                UtilMSG.addSuccessMsg("Digite Nombre");
            }
            if(proveedor.getDireccion()==""){
                UtilMSG.addSuccessMsg("Digite Direccion");
            }            
            if(proveedor.getTelefono()==""){
                UtilMSG.addSuccessMsg("Digite Telefono");
            }            
            if(proveedor.getNomEncargado()==""){
                UtilMSG.addSuccessMsg("Digite nombre encargado");
            }
            if(proveedor.getTel_encargado()==""){
                UtilMSG.addSuccessMsg("Digite Telefono encargado");
            }
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), e);
        }
        
    }


    public Collection<? extends Proveedor> cargarProveedoresList() throws Exception {
        try {
            this.abrirConexion();
            ProveedorDAO proveedorDAO = new ProveedorDAO(conexion);
            return proveedorDAO.cargarProveedores();
        } finally {
            this.cerrarConexion();
        }
    }

    public void eliminarProveedor(Proveedor proveedor) throws Exception {        
         try {
            this.abrirConexion();
             ProveedorDAO proveedorDAO = new ProveedorDAO(conexion);
             proveedorDAO.eliminarProveedor(proveedor);
        } finally {
            this.cerrarConexion();
        }
    }

    
}
