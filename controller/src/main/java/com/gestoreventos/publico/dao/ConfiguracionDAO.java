/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.dao;

import conexion.Consulta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import com.gestoreventos.publico.Configuracion;


/**
 *
 * @author juliano
 */
public class ConfiguracionDAO {

    private Connection conexion;

    public ConfiguracionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public HashMap cargarConfiguracion() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT cod_configuracion, nombre, valor"
                    + " FROM public.configuracion;"
            );
            rs = consulta.ejecutar(sql);
            HashMap c = new HashMap();
            while (rs.next()) {
                c.put(rs.getString("nombre"), rs.getString("valor"));
            }
            return c;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }
    
    public String cargarConfiguracioncorreo() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT cod_configuracion, nombre, valor, email_admin"
                    + " FROM public.configuracion;"
            );
            rs = consulta.ejecutar(sql);
            if (rs.next()) {
                return rs.getString("email_admin");
            }
            return null;
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
