/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.dao;


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
public class ArticuloDAO {

    private Connection conexion;

    public ArticuloDAO(Connection conexion) {
        this.conexion = conexion;
    } 

    public void guardarArticulo( Articulo articulo) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO articulo("
                    + " cod_articulo, cod_marca, descripcion, modelo, periodo_mantenimiento)"
                    + " VALUES ('" + articulo.getCodArticulo()+"','"+articulo.getMarca().getCodMarca()+"', '"+articulo.getDescripcion().toUpperCase()+"', '"+articulo.getModelo()+"', "
                    + "'"+articulo.getPeriMantenimiento()+"')"
                    + " ON CONFLICT (cod_articulo) DO UPDATE SET "
                    + " cod_marca = EXCLUDED.cod_marca , descripcion=EXCLUDED.descripcion, modelo=EXCLUDED.modelo, periodo_mantenimiento=EXCLUDED.periodo_mantenimiento "
                    
                    
                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Articulo> cargarArticulos() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " SELECT art.cod_articulo codart, ma.cod_marca codmar,ma.nombre nommar, art.descripcion descart,  " +
                    " art.modelo artmodelo, art.periodo_mantenimiento artpm " +
                    " FROM public.articulo art " +
                    " join marca ma using (cod_marca) " +                                        
                    " ORDER BY art.cod_articulo "
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Articulo> articulos = new ArrayList<>();
            while (rs.next()) {
                Articulo art= new Articulo(rs.getInt("codart"), rs.getString("descart"), rs.getString("artmodelo"), rs.getInt("artpm"));
                art.setMarca(new Marca(rs.getInt("codmar"), rs.getString("nommar")));                 
                articulos.add(art);                
            }
            return articulos;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void eliminarArticulo(Articulo articulo) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "DELETE from articulo "
                    + "WHERE cod_articulo='"+articulo.getCodArticulo()+"'"                    
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

    

    
}
