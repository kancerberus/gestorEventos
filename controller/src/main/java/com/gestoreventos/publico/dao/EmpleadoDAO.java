/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.dao;

import com.gestoreventos.entity.App;
import com.gestoreventos.entity.UtilFecha;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.publico.Cargo;
import com.gestoreventos.publico.Empleado;
import com.gestoreventos.publico.Roles;
import com.gestoreventos.publico.Usuarios;
import conexion.Consulta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author fjvc
 */
public class EmpleadoDAO {

    private Connection conexion;

    public EmpleadoDAO(Connection conexion) {
        this.conexion = conexion;
    } 

    public void guardarEmpleado( Empleado empleado) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO empleado("
                    + " cedula, nombre, apellido, cod_cargo, fecha_nac, genero, telefono, correo)"
                    + " VALUES ('" + empleado.getCedula()+"', '"+empleado.getNombre().toUpperCase()+"', '"+empleado.getApellido().toUpperCase()+"','"+empleado.getCargo().getCodCargo()+"', "
                    + "'"+empleado.getFechaNac()+"', '"+empleado.getGenero()+"', '"+empleado.getTelefono()+"', '"+empleado.getCorreo()+"')"
                    + " ON CONFLICT (cedula) DO UPDATE SET "
                    + " nombre = EXCLUDED.nombre , apellido=EXCLUDED.apellido, cod_cargo=EXCLUDED.cod_cargo, fecha_nac=EXCLUDED.fecha_nac, "
                    + " genero=EXCLUDED.genero, correo=EXCLUDED.correo, telefono=EXCLUDED.telefono"
                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Empleado> cargarEmpleados() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT cedula, e.nombre nombre, apellido, telefono, correo,genero, c.cod_cargo codcargo, c.nombre nomcargo, fecha_nac "
                    + " FROM public.empleado e"
                    + " JOIN cargo c on (c.cod_cargo=e.cod_cargo)"
                    + " ORDER BY nombre"
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Empleado> empleados = new ArrayList<>();
            while (rs.next()) {
                Empleado e = new Empleado(rs.getInt("cedula"), rs.getString("nombre"), rs.getString("apellido"),
                        rs.getString("correo"), rs.getString("telefono"), rs.getDate("fecha_nac"),rs.getString("genero"));
                e.setCargo(new Cargo(rs.getInt("codcargo"), rs.getString("nomcargo")));
                empleados.add(e);
            }
            return empleados;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void eliminarEmpleado(Empleado empleado) throws SQLException {
         Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "DELETE from empleado "
                    + "WHERE cedula='"+empleado.getCedula()+"'"                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }
    

    
}
