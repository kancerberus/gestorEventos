/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.controller;

import com.gestoreventos.dao.GeneralDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fjvc
 */
public class GestorGeneral extends Gestor implements Serializable {

//    public static String USUARIO_MENUS_COD_MENU_SEQ = "usuario.menus_cod_menu_seq";
    public static String GESTOR_EVALUACION_COD_EVALUACION_SEQ = "gestor.evaluacion_cod_evaluacion_seq";
    public static String UBICACION_COD_UBICACION_SEQ = "ubicaciones_cod_ubicacion_seq";
    public static String PROVEEDOR_COD_PROVEEDOR_SEQ = "proveedor_cod_proveedor_seq";
    public static String ARTICULO_COD_ARTICULO_SEQ = "articulo_cod_articulo_seq";
    
    public static boolean VALIDA_PK = true;
    public static boolean NO_VALIDA_PK = false;
    

    public GestorGeneral() throws Exception {
        super();
    }

    public Long nextval(String secuencia) throws Exception {
        try {
            this.abrirConexion();
            return new GeneralDAO(this.conexion).nextval(secuencia);
        } finally {
            this.cerrarConexion();
        }
    }

    public int siguienteCodigoEntidad(String campo, String entidad) throws Exception {
        try {
            this.abrirConexion();
            GeneralDAO generalDAO = new GeneralDAO(this.conexion);
            return generalDAO.siguienteCodigoEntidad(campo, entidad);
        } finally {
            this.cerrarConexion();
        }
    }
    
    public Date cargarFechaActualizo(String usuario) throws Exception{
        try {
            this.abrirConexion();
            GeneralDAO generalDAO = new GeneralDAO(this.conexion);
            return generalDAO.cargarFechaActualizo(usuario);
        } finally {
            this.cerrarConexion();
        }
    }

    public Date now() throws Exception {
        try {
            this.abrirConexion();
            GeneralDAO generalDAO = new GeneralDAO(this.conexion);
            return generalDAO.now();
        } finally {
            this.cerrarConexion();
        }
    }

}
