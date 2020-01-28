/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.dao;

import com.gestoreventos.publico.Cargo;
import com.gestoreventos.publico.Municipios;
import conexion.Consulta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author fjvc
 */
public class CargoDAO {

    private Connection conexion;

    public CargoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public ArrayList<Cargo> cargarCargos() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT cod_cargo, nombre"
                    + " FROM public.cargo"
                    + " ORDER BY nombre"
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Cargo> cargos = new ArrayList<>();
            while (rs.next()) {
                Cargo c = new Cargo(rs.getInt("cod_cargo"), rs.getString("nombre"));                
                cargos.add(c);                
            }
            return cargos;
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
