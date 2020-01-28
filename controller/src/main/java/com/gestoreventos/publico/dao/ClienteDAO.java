/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.dao;

import com.gestoreventos.entity.App;
import com.gestoreventos.entity.UtilFecha;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.publico.Cargo;
import com.gestoreventos.publico.Cliente;
import com.gestoreventos.publico.Empleado;
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
public class ClienteDAO {

    private Connection conexion;

    public ClienteDAO(Connection conexion) {
        this.conexion = conexion;
    } 

    public void guardarCliente( Cliente cliente) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO cliente("
                    + " nit, nombre, direccion, telefono, correo, nom_contacto, tel_contacto)"
                    + " VALUES ('" + cliente.getNit()+"', '"+cliente.getNombre().toUpperCase()+"', '"+cliente.getDireccion().toUpperCase()+"','"+cliente.getTelefono()+"', "
                    + "'"+cliente.getCorreo()+"', '"+cliente.getNomContacto()+"', '"+cliente.getTelContacto()+"')"
                    + " ON CONFLICT (nit) DO UPDATE SET "
                    + " nombre = EXCLUDED.nombre , direccion=EXCLUDED.direccion, telefono=EXCLUDED.telefono, correo=EXCLUDED.correo, "
                    + " nom_contacto=EXCLUDED.nom_contacto, tel_contacto=EXCLUDED.tel_contacto "
                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Cliente> cargarClientes() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT nit, nombre , direccion, telefono, correo, nom_contacto, tel_contacto "
                    + " FROM public.cliente cl"                    
                    + " ORDER BY nombre"
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Cliente> clientes = new ArrayList<>();
            while (rs.next()) {
                Cliente cl = new Cliente(rs.getInt("nit"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"),rs.getString("nom_contacto") , rs.getString("tel_contacto"), rs.getString("correo"));                                
                clientes.add(cl);
            }
            return clientes;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void eliminarCliente(Cliente cliente) throws SQLException {
         Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "DELETE from cliente "
                    + "WHERE nit='"+cliente.getNit()+"'"                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }
    

    
}
