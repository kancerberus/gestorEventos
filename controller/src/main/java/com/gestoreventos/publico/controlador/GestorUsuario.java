/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.controlador;

import com.gestoreventos.controller.Gestor;
import com.gestoreventos.entity.UtilCorreo;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilTexto;
import com.gestoreventos.publico.Roles;
import com.gestoreventos.publico.Usuarios;
import com.gestoreventos.publico.dao.UsuarioDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Julian
 */
public class GestorUsuario extends Gestor {

    public GestorUsuario() throws Exception {
        super();
    }

    public boolean validarUsuario(String usuario, String clave) throws Exception {
        try {
            this.abrirConexion();
            return new UsuarioDAO(this.conexion).validarUsuario(usuario, clave);
        } finally {
            this.cerrarConexion();
        }
    }

    public ArrayList<Usuarios> cargarListaUsuarios() throws Exception {
        try {
            this.abrirConexion();
            return new UsuarioDAO(this.conexion).cargarListaUsuarios();
        } finally {
            this.cerrarConexion();
        }
    }

   

    public Usuarios cargarDatosUsuario(Usuarios usuario, final String filtro) throws Exception {
        try {
            this.abrirConexion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            usuario = usuarioDAO.cargarDatosUsuario(usuario, filtro);
            usuario = usuarioDAO.cargarRolUsuario( usuario);
            return usuario;
        } finally {
            this.cerrarConexion();
        }
    }
    

    public Collection<? extends String> autoCompletaUsuario(String query) throws Exception {
        try {
            this.abrirConexion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            return usuarioDAO.autoCompletaUsuario( query);
        } finally {
            this.cerrarConexion();
        }
    }

    public void almacenarUsuario( Usuarios usuario) throws Exception {
        try {
            this.abrirConexion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            int registrosActualizados = usuarioDAO.actualizarUsuario( usuario);
            if (registrosActualizados == 0) {
                usuario.setActivo(true);
                usuarioDAO.insertarUsuario(usuario);
            }
        } finally {
            this.cerrarConexion();
        }
    }

    public List<Roles> cargarRoles() throws Exception {
        try {
            this.abrirConexion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            return usuarioDAO.cargarRoles();
        } finally {
            this.cerrarConexion();
        }
    }
    
    public void almacenarRolUsuario( Usuarios usuario) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);            
            usuarioDAO.agregarRolUsuario(usuario);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public boolean existeDocumentoUsuario(Usuarios usuario) throws Exception {
        try {
            this.abrirConexion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            return usuarioDAO.existeDocumentoUsuario(usuario);
        } finally {
            this.cerrarConexion();
        }
    }
    
    public boolean existeUsuario(Usuarios usuario) throws Exception {
        try {
            this.abrirConexion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            return usuarioDAO.existeUsuario(usuario);
        } finally {
            this.cerrarConexion();
        }
    }

    public boolean usuarioAutorizadoExamen(String email, String codExamen) throws Exception {
        try {
            this.abrirConexion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            return usuarioDAO.usuarioAutorizadoExamen(email, codExamen);
        } finally {
            this.cerrarConexion();
        }
    }

    public Usuarios validarUsuarioNuevo(Usuarios usuario) throws Exception {
        if (usuario == null || usuario.getUsuariosPK() == null || usuario.getUsuariosPK().getDocumentoUsuario() == null
                || usuario.getUsuariosPK().getDocumentoUsuario().equalsIgnoreCase("")
                || usuario.getUsuariosPK().getDocumentoUsuario().equalsIgnoreCase("0")) {
            throw new Exception("Ingresa el documento del usuario a crear", UtilLog.TW_VALIDACION);
        }
        if (usuario.getNombre() == null || usuario.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el nombre del usuario", UtilLog.TW_VALIDACION);
        }
        if (usuario.getApellido() == null || usuario.getApellido().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el apellido del usuario", UtilLog.TW_VALIDACION);
        }
        if (usuario.getCorreo() == null
                || usuario.getCorreo().equalsIgnoreCase("")
                || !UtilCorreo.validarCorreo(usuario.getCorreo())) {
            throw new Exception("Ingresa un correo valido", UtilLog.TW_VALIDACION);
        }
        if (usuario.getUsuario() == null || usuario.getUsuario().equalsIgnoreCase("")) {
            throw new Exception("Ingresa el usuario a crear", UtilLog.TW_VALIDACION);
        }

        if (usuario.getClave() == null || usuario.getClave().equalsIgnoreCase("")) {
            throw new Exception("Ingresa una clave valida", UtilLog.TW_VALIDACION);
        }

        usuario.setNombre(usuario.getNombre().trim().toUpperCase());
        usuario.setApellido(usuario.getApellido().trim().toUpperCase());
        usuario.setUsuario(usuario.getUsuario().trim().toUpperCase());
        usuario.setActivo(Boolean.TRUE);
        usuario.setFechaIngreso(new Date());
        usuario.setClave(usuario.getClave().trim());
        usuario.setCorreo(usuario.getCorreo().trim().toLowerCase());
        return usuario;
    }
}
