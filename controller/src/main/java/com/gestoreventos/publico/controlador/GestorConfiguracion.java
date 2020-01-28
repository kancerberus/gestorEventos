/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.controlador;

import com.gestoreventos.controller.Gestor;
import com.gestoreventos.publico.dao.ConfiguracionDAO;
import com.gestoreventos.publico.Configuracion;
import java.util.HashMap;

/**
 *
 * @author juliano
 */
public class GestorConfiguracion extends Gestor {

    public GestorConfiguracion() throws Exception {
        super();
    }

    public HashMap cargarConfiguracion() throws Exception {
        try {
            this.abrirConexion();
            ConfiguracionDAO configuracionDAO = new ConfiguracionDAO(conexion);
            return configuracionDAO.cargarConfiguracion();
        } finally {
            this.cerrarConexion();
        }
    }
    public String cargarConfiguracioncorreo() throws Exception {
        try {
            this.abrirConexion();
            ConfiguracionDAO configuracionDAO = new ConfiguracionDAO(conexion);
            return configuracionDAO.cargarConfiguracioncorreo();
        } finally {
            this.cerrarConexion();
        }
    }
}
