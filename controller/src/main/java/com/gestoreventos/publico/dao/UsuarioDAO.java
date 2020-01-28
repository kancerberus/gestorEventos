/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico.dao;

import com.gestoreventos.entity.App;
import com.gestoreventos.entity.UtilFecha;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.publico.Roles;
import com.gestoreventos.publico.Usuarios;
import conexion.Consulta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Julian
 */
public class UsuarioDAO {

    private Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean validarUsuario(String usuario, String clave) throws Exception {
        String claveIngresada;
        String claveActual;
        boolean usuarioValido = false;
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            String sql = "SELECT documento_usuario, nombre, apellido, usuario, clave, activo, correo"
                    + " FROM usuarios"
                    + " WHERE usuario='" + usuario.toUpperCase() + "'";
            rs = consulta.ejecutar(sql);
            if (rs.next()) {
                claveActual = rs.getString("clave");
            } else {
                throw new Exception("El usuario " + usuario + ", no existe por favor verifique.", UtilLog.TW_VALIDACION);
            }
            sql = "SELECT md5('" + clave + "') AS claveIngresada";
            rs = consulta.ejecutar(sql);
            rs.next();
            claveIngresada = rs.getString("claveIngresada");
            if (claveActual.equalsIgnoreCase(claveIngresada)) {
                usuarioValido = true;
            } else {
                throw new Exception("Por favor verifique la clave de acceso.", UtilLog.TW_VALIDACION);
            }

            return usuarioValido;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public ArrayList<Usuarios> cargarListaUsuarios() throws Exception {
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        ResultSet rs = null;
        Consulta consulta = null;

        try {
            consulta = new Consulta(this.conexion);
            String sql = "SELECT documento_usuario, nombre, apellido, usuario, clave, activo, correo, fecha_ingreso, fecha_retiro"
                    + " FROM usuarios";
            rs = consulta.ejecutar(sql);
            while (rs.next()) {
                Usuarios u = new Usuarios(rs.getString("documento_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setUsuario(rs.getString("usuario"));
                u.setClave(rs.getString("clave"));
                u.setActivo(rs.getBoolean("activo"));
                u.setCorreo(rs.getString("correo"));
                listaUsuarios.add(u);
            }
            return listaUsuarios;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Usuarios cargarDatosUsuario(Usuarios usuario, final String filtro) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT documento_usuario, nombre, apellido, usuario, clave, activo, correo"
                    + " FROM usuarios"                    
            );

            //  public static final String FILTRO_USUARIO = "USUARIO";
            //public static final String FILTRO_DOCUMENTO = "DOCUMENTO";
            switch (filtro) {
                case App.USUARIOS_FILTRO_USUARIO:
                    sql.append(" WHERE usuario='").append(usuario.getUsuario().toUpperCase()).append("'");
                    break;
                case App.USUARIOS_FILTRO_DOCUMENTO:
                    sql.append(" WHERE documento_usuario='").append(usuario.getUsuariosPK().getDocumentoUsuario()).append("'");
                    break;
                default:
                    throw new SQLException("Filtro Usuario Invalido.");
            }

            rs = consulta.ejecutar(sql);
            if (rs.next()) {
                usuario = new Usuarios(rs.getString("documento_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setActivo(rs.getBoolean("activo"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setClave(rs.getString("clave"));
                usuario.setClaveMd5(rs.getString("clave"));                
//                Establecimiento establecimientoUsuario = new Establecimiento(rs.getInt("codigo_establecimiento"));
//                usuario.setEstablecimiento(establecimientoUsuario);
            }
            return usuario;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }    
    
    public Usuarios cargarRolUsuario(Usuarios usuario) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT RU.codigo_rol"
                    + " FROM roles_usuarios RU "                    
                    + " WHERE documento_usuario = '" + usuario.getUsuariosPK().getDocumentoUsuario() + "'"
            );
            rs = consulta.ejecutar(sql);
            Roles rol = new Roles();
            if (rs.next()) {
                rol.setCodigoRol(rs.getInt("codigo_rol"));             
            }
            usuario.setRoles(rol);
            return usuario;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends String> autoCompletaUsuario( String query) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        List<String> resultados = new ArrayList<>();
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT documento_usuario, nombre, apellido,"
                    + " usuario, clave, activo, correo, fecha_ingreso, fecha_retiro"
                    + " FROM usuarios"
                    + " WHERE nombre || apellido ||usuario LIKE '%" + query.toUpperCase() + "%'"
            );
            rs = consulta.ejecutar(sql);
            while (rs.next()) {
                resultados.add(rs.getString("usuario") + " - " + rs.getString("nombre") + " " + rs.getString("apellido"));
            }
            return resultados;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public int actualizarUsuario( Usuarios usuario) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "UPDATE usuarios"
                    + " SET documento_usuario='" + usuario.getUsuariosPK().getDocumentoUsuario().trim() + "',"
                    + " nombre='" + usuario.getNombre().toUpperCase() + "', apellido='" + usuario.getApellido().toUpperCase() + "',"
                    + " usuario='" + usuario.getUsuario().toUpperCase() + "',"
                    + " clave=" + (usuario.getClave().equalsIgnoreCase(usuario.getClaveMd5()) ? "'" + usuario.getClaveMd5() + "'" : "md5('" + usuario.getClave() + "')") + ","
                    + " activo=" + usuario.getActivo() + ", correo='" + usuario.getCorreo() + "', "
                    + " fecha_retiro=" + UtilFecha.formatoFecha(usuario.getFechaRetiro(), null, UtilFecha.PATRON_FECHA_YYYYMMDD, UtilFecha.CARACTER_COMILLA)
                    + " WHERE documento_usuario='" + usuario.getUsuariosPK().getDocumentoUsuario() + "'"
            );
            return consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void insertarUsuario( Usuarios usuario) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO usuarios("
                    + " documento_usuario, nombre, apellido, "
                    + " usuario, clave, activo, correo, fecha_ingreso, fecha_retiro)"
                    + " VALUES ('" + usuario.getUsuariosPK().getDocumentoUsuario().trim() + "', '" + usuario.getNombre().toUpperCase() + "',"
                    + "  '" + usuario.getApellido().toUpperCase() + "', '" + usuario.getUsuario().toUpperCase() + "',"
                    + " MD5('" + usuario.getClave() + "')," + usuario.getActivo() + ",'" + usuario.getCorreo() + "',"
                    + " current_date," + UtilFecha.formatoFecha(usuario.getFechaRetiro(), null, UtilFecha.PATRON_FECHA_YYYYMMDD, UtilFecha.CARACTER_COMILLA) + ")"
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public List<Roles> cargarRoles() throws SQLException {

        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            List<Roles> listaRoles = new ArrayList<>();
            StringBuilder sql = new StringBuilder(
                    "SELECT codigo_rol, nombre"
                    + " FROM roles"
            );
            rs = consulta.ejecutar(sql);

            while (rs.next()) {
                Roles rol = new Roles();
                rol.setCodigoRol(rs.getInt("codigo_rol"));
                rol.setNombre(rs.getString("nombre"));
                listaRoles.add(rol);
            }

            return listaRoles;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void eliminarEstablecimientosUsuario(Usuarios usuario) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "DELETE FROM rel_usuarios_establecimiento"
                    + " WHERE documento_usuario='" + usuario.getUsuariosPK().getDocumentoUsuario() + "'"
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    

    public void eliminarRolesUsuario(Usuarios usuario) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "DELETE FROM roles_usuarios"
                    + " WHERE documento_usuario='" + usuario.getUsuariosPK().getDocumentoUsuario() + "'"
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void eliminarRolUsuario( Usuarios usuario) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    //                    "DELETE FROM roles_usuarios"
                    //                    + " WHERE codigo_establecimiento=" + establecimiento.getCodigoEstablecimiento() + " AND documento_usuario='" + usuario.getUsuariosPK().getDocumentoUsuario() + "'"
                    "DELETE FROM roles_usuarios"
                    + " WHERE documento_usuario='" + usuario.getUsuariosPK().getDocumentoUsuario() + "'"
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void agregarRolUsuario( Usuarios usuario) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    //                    "INSERT INTO roles_usuarios("
                    //                    + " codigo_establecimiento, documento_usuario, codigo_rol)"
                    //                    + " VALUES (" + establecimiento.getCodigoEstablecimiento() + ", '" + usuario.getUsuariosPK().getDocumentoUsuario() + "', " + usuario.getRoles().getCodigoRol() + ")"
                    "INSERT INTO roles_usuarios ( documento_usuario, codigo_rol) "
                    + " VALUES ('" + usuario.getUsuariosPK().getDocumentoUsuario().trim() + "', '" + usuario.getRoles().getCodigoRol() + "')"
                    
                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    

    public boolean existeUsuario(Usuarios usuario) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT count(1) > 0 as existe"
                    + " FROM usuarios"
                    + " WHERE documento_usuario='" + usuario.getUsuario() + "'"
            );
            rs = consulta.ejecutar(sql);
            rs.next();
            return rs.getBoolean("existe");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public boolean existeDocumentoUsuario(Usuarios usuario) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT count(1) > 0 as existe"
                    + " FROM usuarios"
                    + " WHERE documento_usuario='" + usuario.getUsuariosPK().getDocumentoUsuario() + "'"
            );
            rs = consulta.ejecutar(sql);
            rs.next();
            return rs.getBoolean("existe");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    

    public boolean usuarioAutorizadoExamen(String email, String codExamen) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT COUNT(1)>0 AS existe"
                    + " FROM test.examen_usuario_autorizado"
                    + " WHERE email='" + email + "' AND cod_examen='" + codExamen + "'"
            );
            rs = consulta.ejecutar(sql);
            rs.next();
            return rs.getBoolean("existe");
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
