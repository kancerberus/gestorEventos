/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.controlador;

import com.gestoreventos.controller.Gestor;
import com.gestoreventos.entity.UtilCorreo;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.entity.UtilTexto;
import com.gestoreventos.publico.Cargo;
import com.gestoreventos.publico.Cliente;
import com.gestoreventos.publico.Empleado;
import com.gestoreventos.publico.Roles;
import com.gestoreventos.publico.Usuarios;
import com.gestoreventos.publico.dao.CargoDAO;
import com.gestoreventos.publico.dao.ClienteDAO;
import com.gestoreventos.publico.dao.EmpleadoDAO;
import com.gestoreventos.publico.dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fjvc
 */
public class GestorCliente extends Gestor {

    public GestorCliente() throws Exception {
        super();
    }

    public void guardarCliente(Cliente cliente) throws Exception {
         try {
            this.abrirConexion();
             ClienteDAO clienteDAO = new ClienteDAO(conexion);
             clienteDAO.guardarCliente(cliente);
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarCliente(Cliente cliente) {
        
        try {
            
            if(cliente.getNit()==null){
                UtilMSG.addSuccessMsg("Digite Nit");
            }
            if(cliente.getNombre()==""){
                UtilMSG.addSuccessMsg("Digite Nombre Empleado");
            }            
            if(cliente.getDireccion()==""){
                UtilMSG.addSuccessMsg("Digite Direccion Empresa");
            }            
            if(cliente.getTelefono()==""){
                UtilMSG.addSuccessMsg("Digite Telefono Empresa");
            }
            if(cliente.getCorreo()==""){
                UtilMSG.addSuccessMsg("Digite Correo Empresa");
            }
            if(cliente.getNomContacto()==""){
                UtilMSG.addSuccessMsg("Digite Nombre del Contacto");
            }
            if(cliente.getTelContacto()==""){
                UtilMSG.addSuccessMsg("Digite Telefono del Contacto");
            }
            
                
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), e);
        }
        
    }


    public Collection<? extends Cliente> cargarClientesList() throws Exception {
        try {
            this.abrirConexion();
            ClienteDAO clienteDAO = new ClienteDAO(conexion);
            return clienteDAO.cargarClientes();
        } finally {
            this.cerrarConexion();
        }
    }

    public void eliminarClientes(Cliente cliente) throws Exception {        
         try {
            this.abrirConexion();
             ClienteDAO clienteDAO = new ClienteDAO(conexion);
             clienteDAO.eliminarCliente(cliente);
        } finally {
            this.cerrarConexion();
        }
    }

    
}
