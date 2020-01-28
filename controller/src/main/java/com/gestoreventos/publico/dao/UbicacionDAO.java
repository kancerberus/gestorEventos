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
import com.gestoreventos.publico.Municipios;
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
public class UbicacionDAO {

    private Connection conexion;

    public UbicacionDAO(Connection conexion) {
        this.conexion = conexion;
    } 

    public void guardarUbicacion( Ubicacion ubicacion) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO ubicaciones("
                    + " cod_ubicacion, nombre, direccion, telefono, estado, codigo_municipio)"
                    + " VALUES ('" + ubicacion.getCodUbicacion()+"', '"+ubicacion.getNombre().toUpperCase()+"', '"+ubicacion.getDireccion().toUpperCase()+"','"+ubicacion.getTelefono()+"', "
                    + "'"+ubicacion.getEstado()+"','"+ubicacion.getMunicipio().getCodigoMunicipio()+"')"
                    + " ON CONFLICT (cod_ubicacion) DO UPDATE SET "
                    + " nombre = EXCLUDED.nombre , direccion=EXCLUDED.direccion, telefono=EXCLUDED.telefono, estado=EXCLUDED.estado, codigo_municipio=EXCLUDED.cod_municipio "
                    
                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Ubicacion> cargarUbicaciones() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT cod_ubicacion, ub.nombre nomub , direccion, telefono, estado, mu.codigo_municipio codmun, mu.nombre munom"
                    + " FROM public.ubicaciones ub"
                    + " JOIN public.municipios mu using(codigo_municipio)"                                               
                    + " ORDER BY ub.nombre"
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Ubicacion> ubicaciones = new ArrayList<>();
            while (rs.next()) {
                Ubicacion u = new Ubicacion(rs.getInt("cod_ubicacion"), rs.getString("nomub"), rs.getString("direccion"), rs.getString("telefono"), rs.getBoolean("estado"));
                u.setMunicipio(new Municipios(rs.getString("codmun"), rs.getString("munom")));
                ubicaciones.add(u);
            }
            return ubicaciones;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void eliminarUbicacion(Ubicacion ubicacion) throws SQLException {
         Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "DELETE from ubicaciones "
                    + "WHERE cod_ubicacion='"+ubicacion.getCodUbicacion()+"'"                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Municipios> cargarMunicipioslist() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT codigo_municipio, codigo_departamento, nombre "
                    + " FROM public.municipios "                    
                    + " ORDER BY nombre"
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Municipios> municipios = new ArrayList<>();
            while (rs.next()) {
                Municipios m = new Municipios(rs.getString("codigo_municipio"), rs.getString("nombre"));
                municipios.add(m);
            }
            return municipios;
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
