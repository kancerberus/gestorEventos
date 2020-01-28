/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.controlador;

import com.gestoreventos.controller.Gestor;
import com.gestoreventos.publico.Municipios;
import com.gestoreventos.publico.dao.MunicipiosDAO;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author juliano
 */
public class GestorMunicipios extends Gestor implements Serializable {

    public GestorMunicipios() throws Exception {
        super();
    }

    public Collection<? extends Municipios> cargarMunicipios() throws Exception {
        try {
            this.abrirConexion();
            MunicipiosDAO municipiosDAO = new MunicipiosDAO(conexion);
            return municipiosDAO.cargarMunicipios();
        } finally {
            this.cerrarConexion();
        }
    }

}
