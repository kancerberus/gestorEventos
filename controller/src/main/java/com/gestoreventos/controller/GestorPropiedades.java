/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.controller;

import java.util.Properties;
/**
 *
 * @author fjvc
 */
public class GestorPropiedades {

    public Properties cargarPropiedades() throws Exception {
        Properties p = new Properties();
        try {
//    p.setProperty("urlbd", "jdbc:postgresql://localhost:5432/gestor");
            p.setProperty("urlbd", "jdbc:postgresql://localhost:5432/gestorEventosDEV");

            //p.setProperty("controlador", "org.postgresql.Driver");
            //p.setProperty("usuario", "postgres");
//    p.setProperty("clave", "postgres");
           // p.setProperty("rutaAdjunto", "c:\\gestor\\archivos");
            //p.setProperty("rutaTemporal", "c:\\gestor\\tmp");
            //p.setProperty("rutaEliminado", "c:\\gestor\\eliminados");            
    //p.setProperty("guardarLogos", "D:\\gestor\\gestor-web\\target\\gestor-web-1.0\\resources\\imagenes\\establecimientos");
            p.setProperty("clave", "1234");

        } catch (Exception e) {
            throw e;
        }
        return p;
    }
}
