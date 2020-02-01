/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.movimientos.dao;


import com.gestoreventos.controller.GestorGeneral;
import com.gestoreventos.movimientos.HistoricoMovimientos;
import com.gestoreventos.movimientos.Inventario;
import com.gestoreventos.movimientos.RazonMovimiento;
import com.gestoreventos.publico.dao.*;
import com.gestoreventos.publico.Articulo;
import com.gestoreventos.publico.EstadoArticulo;
import com.gestoreventos.publico.Marca;
import com.gestoreventos.publico.Menu;
import com.gestoreventos.publico.Proveedor;
import com.gestoreventos.publico.Submenu;
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
public class MovimientoDAO {

    private Connection conexion;

    public MovimientoDAO(Connection conexion) {
        this.conexion = conexion;
    } 

    /*public void guardarInventario(Inventario inv) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO movimientos.inventario("
                    + " serial, cod_ubicacion, cod_estado_articulo,observacion, estado, ult_mantenimiento)"
                    + " VALUES ('" + inv.getArticulo().getSerial()+"','"+inv.getUbicacion().getCodUbicacion()+"','"+inv.getEdoArticulo().getCodEstadoArticulo()+"', "
                            + "'"+inv.getObservacion()+"','"+inv.getEstado()+"','"+inv.getUltMantenimiento()+"')"
                    + " ON CONFLICT (serial) DO UPDATE SET "
                    + " cod_ubicacion = EXCLUDED.cod_ubicacion , cod_estado_articulo=EXCLUDED.cod_estado_articulo, observacion=EXCLUDED.observacion, "
                    + " ult_mantenimiento=EXCLUDED.ult_mantenimiento, estado=EXCLUDED.estado "                    
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }*/

    public Collection<? extends Inventario> cargarInventarios() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " select inv.serial serial, ar.cod_articulo codar,ar.descripcion descart, ub.cod_ubicacion codub, ub.nombre nomub, " +
                    " edoart.cod_estado_articulo codedoart, edoart.nombre nomedoart, inv.observacion obs, inv.ult_mantenimiento ultm, " +
                    " inv.estado edoinv " +
                    " from movimientos.inventario inv " +
                    " join articulo ar using(cod_articulo) " +
                    " join ubicaciones ub using (cod_ubicacion) " +
                    " join estado_articulo edoart using (cod_estado_articulo)"
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Inventario> inventarios = new ArrayList<>();
            while (rs.next()) {
                Inventario inv= new Inventario();
                inv.setSerial(rs.getString("serial"));
                inv.setArticulo(new Articulo(rs.getInt("codar"), rs.getString("descart"), null, null));
                inv.setUbicacion(new Ubicacion(rs.getInt("codub"), rs.getString("nomub"), null, null, null));
                inv.setEdoArticulo(new EstadoArticulo(rs.getInt("codedoart"), rs.getString("nomedoart")));
                inv.setObservacion(rs.getString("obs"));
                inv.setEstado(rs.getBoolean("edoinv"));
                inv.setUltMantenimiento(rs.getDate("ultm"));
                
                inventarios.add(inv);                
            }
            return inventarios;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void eliminarInventario(Inventario inv) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "DELETE from movimientos.inventario "
                    + "WHERE serial='"+inv.getSerial()+"'"                    
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

    public Collection<? extends RazonMovimiento> cargarRazonMovimiento() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " select cod_razon, nombre "
                    + " from movimientos.razon_movimiento "
                    + " order by cod_razon "
            );
            rs = consulta.ejecutar(sql);
            ArrayList<RazonMovimiento> razonMovimientos = new ArrayList<>();
            while (rs.next()) {
                RazonMovimiento rM=new RazonMovimiento(rs.getInt("cod_razon"), rs.getString("nombre"));                
                razonMovimientos.add(rM);                
            }
            return razonMovimientos;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    /*public void ingresoPrimerHistorico(Inventario inv) throws SQLException, Exception {
        Consulta consulta = null;
        GestorGeneral gestorGeneral=new GestorGeneral();
        
        try {
            
            inv.getHistoricoMovimiento().setCodMoviento(gestorGeneral.nextval(GestorGeneral.MOVIMIENTOS_HISTORICO_MOVIMIENTOS_COD_MOVIMIENTO_SEQ).intValue());
            
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO movimientos.historico_movimientos("
                    + " cod_movimiento, tipo_movimiento, fecha_movimiento,cod_razon, serial, cod_estado_articulo)"
                    + " VALUES ('" + inv.getHistoricoMovimiento().getCodMoviento()+"','true', NOW(),4, "
                    + "'"+inv.getArticulo().getSerial()+"','"+inv.getEdoArticulo().getCodEstadoArticulo()+"')"
                     
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }*/

    /*public void guardarMantnimiento(HistoricoMovimientos hm) throws SQLException {
        Consulta consulta = null;        
        
        try {
            
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO movimientos.historico_movimientos("
                    + " cod_movimiento, tipo_movimiento, fecha_movimiento,cod_razon, serial, cod_estado_articulo, cod_proveedor)"
                    + " VALUES ('"+hm.getCodMoviento()+"','"+hm.getTipoMovimiento()+"','"+hm.getFechaMovimiento()+"','2','"+hm.getArticulo().getSerial()+"', "
                    + "'"+hm.getArticulo().getEdoArticulo().getCodEstadoArticulo()+"','"+hm.getProveedor().getCodProveedor()+"')"
                    + " ON CONFLICT (cod_movimiento) DO UPDATE SET "
                    + " tipo_movimiento = EXCLUDED.tipo_movimiento , fecha_movimiento=EXCLUDED.fecha_movimiento, cod_razon=EXCLUDED.cod_razon, "
                    + " serial=EXCLUDED.serial, cod_estado_articulo=EXCLUDED.cod_estado_articulo, cod_proveedor=EXCLUDED.cod_proveedor "                    
                     
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }*/

    /*public Collection<? extends HistoricoMovimientos> cargarMantenimientosList() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " select hm.cod_movimiento codm, hm.tipo_movimiento tm, hm.fecha_movimiento fechm, rm.cod_razon codrm, rm.nombre nomrm, " +
                    " hm.serial artserial, ea.cod_estado_articulo codea,pr.cod_proveedor codpr, pr.nombre nompr, ea.nombre eanom " +
                    " from movimientos.historico_movimientos hm " +
                    " join movimientos.razon_movimiento rm using (cod_razon) " +
                    " join estado_articulo ea using (cod_estado_articulo) " +
                    " join proveedor pr using(cod_proveedor) " +
                    " where cod_razon=2 "
            );
            rs = consulta.ejecutar(sql);
            ArrayList<HistoricoMovimientos> historicoMovimientos = new ArrayList<>();
            while (rs.next()) {
                HistoricoMovimientos hm=new HistoricoMovimientos();
                hm.setArticulo(new Articulo(rs.getString("artserial"), null, null, null));
                hm.setProveedor(new Proveedor(rs.getInt("codpr"), rs.getString("nompr"), null, null, null, null));
                hm.setCodMoviento(rs.getInt("codm"));
                hm.setTipoMovimiento(rs.getBoolean("tm"));
                hm.setFechaMovimiento(rs.getDate("fechm"));
                hm.setRazonMovimiento(new RazonMovimiento(rs.getInt("codrm"), rs.getString("nomrm")));
                hm.getArticulo().setEdoArticulo(new EstadoArticulo(rs.getInt("codea"), rs.getString("eanom")));
                historicoMovimientos.add(hm);                
                            
            }
            return historicoMovimientos;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }*/

    public Collection<? extends Menu> cargaritemsMenuList() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " select cod_menu, nombre "
                    + " from menu "                    
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Menu> menuList = new ArrayList<>();
            while (rs.next()) {
                Menu m=new Menu();
                m.setCodMenu(rs.getString("cod_menu"));
                m.setNombre(rs.getString("nombre"));                
                menuList.add(m);                
            }
            return menuList;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Submenu> cargaritemsSubmenuList(Menu menu) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " select cod_menu, cod_submenu, nombre "
                    + " from submenu "
                    + " where cod_menu='"+menu.getCodMenu()+"' "                    
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Submenu> submenuList = new ArrayList<>();
            while (rs.next()) {
                Submenu su=new Submenu();
                su.setCodSubMenu(rs.getString("cod_submenu"));
                su.setNombre(rs.getString("nombre"));
                su.setCodMenu(rs.getString("cod_menu"));
                
                submenuList.add(su);                
            }
            return submenuList;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public String cargarDireccionDialogo(Submenu submenu) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        String direcDialogo="";
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " select direc_dialogo "
                    + " from submenu "
                    + " where cod_menu='"+submenu.getCodMenu()+"' and cod_submenu='"+submenu.getCodSubMenu()+"' "                    
            );
            rs = consulta.ejecutar(sql);
            if(rs.next()){
                direcDialogo=rs.getString("direc_dialogo");
                if(direcDialogo==null){
                    direcDialogo="dialogos/nuevo.xhtml";
                }
            }            
            
            return direcDialogo;
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
