/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.movimientos.dao;


import com.gestoreventos.movimientos.Inventario;
import com.gestoreventos.publico.dao.*;
import com.gestoreventos.publico.Articulo;
import com.gestoreventos.publico.EstadoArticulo;
import com.gestoreventos.publico.Marca;
import com.gestoreventos.publico.Proveedor;
import com.gestoreventos.publico.Ubicacion;

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
public class MovimientoDAO {

    private Connection conexion;

    public MovimientoDAO(Connection conexion) {
        this.conexion = conexion;
    } 

    public void guardarInventario(Inventario inv) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO movimientos.inventario("
                    + " cod_articulo, cod_ubicacion, cod_estado_articulo,observacion, estado)"
                    + " VALUES ('" + inv.getArticulo().getCodArticulo()+"','"+inv.getUbicacion().getCodUbicacion()+"','"+inv.getEdoArticulo().getCodEstadoArticulo()+"', "
                            + "'"+inv.getObservacion()+"','"+inv.getEstado()+"')"
                    + " ON CONFLICT (cod_articulo) DO UPDATE SET "
                    + " cod_ubicacion = EXCLUDED.cod_ubicacion , cod_estado_articulo=EXCLUDED.cod_estado_articulo, observacion=EXCLUDED.observacion, "
                    + " estado=EXCLUDED.estado "                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Inventario> cargarInventarios() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " select inv.cod_articulo codart, ar.descripcion descart, ub.cod_ubicacion codub, ub.nombre nomub, " +
                    " edoart.cod_estado_articulo codedoart, edoart.nombre nomedoart, inv.observacion obs, " +
                    " inv.estado edoinv " +
                    " from movimientos.inventario inv " +
                    " join articulo ar using(cod_articulo) " +
                    " join ubicaciones ub using (cod_ubicacion) " +
                    " join estado_articulo edoart using (cod_estado_articulo)"
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Inventario> inventarios = new ArrayList<>();
            while (rs.next()) {
                Inventario inv= new Inventario();
                inv.setArticulo(new Articulo(rs.getInt("codart"), rs.getString("descart"), null, null, null, null, null));
                inv.setUbicacion(new Ubicacion(rs.getInt("codub"), rs.getString("nomub"), null, null, null));
                inv.setEdoArticulo(new EstadoArticulo(rs.getInt("codedoart"), rs.getString("nomedoart")));
                inv.setObservacion(rs.getString("obs"));
                inv.setEstado(rs.getBoolean("edoinv"));
                
                inventarios.add(inv);                
            }
            return inventarios;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void eliminarInventario(Inventario inv) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "DELETE from movimientos.inventario "
                    + "WHERE cod_articulo='"+inv.getArticulo().getCodArticulo()+"'"                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Marca> cargarMarcasList() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " select cod_marca, nombre "
                    + " from marca "
                    + " order by nombre "
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Marca> marcas = new ArrayList<>();
            while (rs.next()) {
                Marca m=new Marca(rs.getInt("cod_marca"), rs.getString("nombre"));
                marcas.add(m);                
            }
            return marcas;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends EstadoArticulo> cargarEstadoArticulos() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " select cod_estado_articulo, nombre "
                    + " from estado_articulo "
                    + " order by cod_estado_articulo "
            );
            rs = consulta.ejecutar(sql);
            ArrayList<EstadoArticulo> edoArticulos = new ArrayList<>();
            while (rs.next()) {
                EstadoArticulo edoArticulo=new EstadoArticulo(rs.getInt("cod_estado_articulo"), rs.getString("nombre"));
                edoArticulos.add(edoArticulo);
            }
            return edoArticulos;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Inventario> cargarInventariosList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
}
