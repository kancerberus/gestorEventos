/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.dao;


import com.gestoreventos.publico.Proveedor;

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
public class ProveedorDAO {

    private Connection conexion;

    public ProveedorDAO(Connection conexion) {
        this.conexion = conexion;
    } 

    public void guardarProveedor( Proveedor proveedor) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO proveedor("
                    + " cod_proveedor, nombre, direccion, telefono, nom_encargado, tel_encargado)"
                    + " VALUES ('" + proveedor.getCodProveedor()+"', '"+proveedor.getNombre().toUpperCase()+"', '"+proveedor.getDireccion().toUpperCase()+"','"+proveedor.getTelefono()+"', "
                    + "'"+proveedor.getNomEncargado()+"','"+proveedor.getTel_encargado()+"')"
                    + " ON CONFLICT (cod_proveedor) DO UPDATE SET "
                    + " nombre = EXCLUDED.nombre , direccion=EXCLUDED.direccion, telefono=EXCLUDED.telefono, nom_encargado=EXCLUDED.nom_encargado, tel_encargado=EXCLUDED.tel_encargado "
                    
                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Proveedor> cargarProveedores() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT cod_proveedor, nombre , direccion, telefono, nom_encargado, tel_encargado"
                    + " FROM public.proveedor prov"                    
                    + " ORDER BY nombre"
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Proveedor> proveedores = new ArrayList<>();
            while (rs.next()) {
                Proveedor p = new Proveedor(rs.getInt("cod_proveedor"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("nom_encargado"), rs.getString("tel_encargado"));
                proveedores.add(p);
            }
            return proveedores;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void eliminarProveedor(Proveedor proveedor) throws SQLException {
         Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "DELETE from proveedor "
                    + "WHERE cod_proveedor='"+proveedor.getCodProveedor()+"'"                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }
    

    
}
