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
import com.gestoreventos.publico.Empleado;
import com.gestoreventos.publico.Roles;
import com.gestoreventos.publico.Usuarios;
import com.gestoreventos.publico.dao.CargoDAO;
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
public class GestorEmpleado extends Gestor {

    public GestorEmpleado() throws Exception {
        super();
    }

    public void guardarEmpleado(Empleado empleado) throws Exception {
         try {
            this.abrirConexion();
             EmpleadoDAO empleadoDAO = new EmpleadoDAO(conexion);
             empleadoDAO.guardarEmpleado(empleado);
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarEmpleado(Empleado empleado) {
        
        try {
            
            if(empleado.getCedula()==null){
                UtilMSG.addSuccessMsg("Digite Cedula");
            }
            if(empleado.getNombre()==""){
                UtilMSG.addSuccessMsg("Digite Nombre Empleado");
            }            
            if(empleado.getApellido()==""){
                UtilMSG.addSuccessMsg("Digite Apellido Empleado");
            }            
            if(empleado.getCargo()==null){
                UtilMSG.addSuccessMsg("Seleccione Cargo Empleado");
            }
            if(empleado.getCorreo()==""){
                UtilMSG.addSuccessMsg("Digite Correo Empleado");
            }
            if(empleado.getTelefono()==""){
                UtilMSG.addSuccessMsg("Digite Telefono Empleado");
            }
            if(empleado.getFechaNac()==null){
                UtilMSG.addSuccessMsg("Digite Fecha Nacimiento Empleado");
            }
            if(empleado.getGenero()==""){
                UtilMSG.addSuccessMsg("Seleccione Genero Empleado");
            }            
                
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
                UtilLog.generarLog(this.getClass(), e);
        }
        
    }

    public ArrayList<Cargo> cargarCargos() throws Exception {
        try {
            this.abrirConexion();
            CargoDAO cargoDAO = new CargoDAO(conexion);
            return cargoDAO.cargarCargos();
        } finally {
            this.cerrarConexion();
        }
    }

    public Collection<? extends Empleado> cargarEmpleadosList() throws Exception {
        try {
            this.abrirConexion();
            EmpleadoDAO empleadoDAO = new EmpleadoDAO(conexion);
            return empleadoDAO.cargarEmpleados();
        } finally {
            this.cerrarConexion();
        }
    }

    public void eliminarEmpleado(Empleado empleado) throws Exception {        
         try {
            this.abrirConexion();
             EmpleadoDAO empleadoDAO = new EmpleadoDAO(conexion);
             empleadoDAO.eliminarEmpleado(empleado);
        } finally {
            this.cerrarConexion();
        }
    }

    
}
