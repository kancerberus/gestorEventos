/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.movimientos.dao;


import com.gestoreventos.controller.GestorGeneral;
import com.gestoreventos.movimientos.HistoricoMovimientos;
import com.gestoreventos.movimientos.Inventario;
import com.gestoreventos.movimientos.Prestamo;
import com.gestoreventos.movimientos.RazonMovimiento;
import com.gestoreventos.publico.dao.*;
import com.gestoreventos.publico.Articulo;
import com.gestoreventos.publico.EstadoActualArticulo;
import com.gestoreventos.publico.EstadoArticulo;
import com.gestoreventos.publico.Marca;
import com.gestoreventos.publico.Menu;
import com.gestoreventos.publico.Proveedor;
import com.gestoreventos.publico.Submenu;
import com.gestoreventos.publico.Submenucategoria;
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

    public void guardarInventario(Inventario inv) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO movimientos.inventario("
                    + " serial, cod_ubicacion, cod_estado_articulo,observacion,cod_estado_actual, fecha_compra, cod_articulo)"
                    + " VALUES ('" + inv.getSerial()+"','"+inv.getUbicacion().getCodUbicacion()+"','"+inv.getEdoArticulo().getCodEstadoArticulo()+"', "
                    + "'"+inv.getObservacion()+"','2','"+inv.getFechaCompra()+"','"+inv.getArticulo().getCodArticulo()+"')"
                    + " ON CONFLICT (serial) DO UPDATE SET "
                    + " cod_ubicacion = EXCLUDED.cod_ubicacion , cod_estado_actual=EXCLUDED.cod_estado_actual, cod_estado_articulo=EXCLUDED.cod_estado_articulo,"
                    + " observacion=EXCLUDED.observacion, fecha_compra=EXCLUDED.fecha_compra, cod_articulo=EXCLUDED.cod_articulo "
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Inventario> cargarInventarios() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " select inv.serial serial, ar.cod_articulo codar,ar.descripcion descart, ar.modelo modeloart, marca.cod_marca codmarca, marca.nombre nommarca, ub.cod_ubicacion codub, ub.nombre nomub, " +
                    " edoart.cod_estado_articulo codedoart, edoart.nombre nomedoart, inv.observacion obs,inv.fecha_compra fechCompra,"+
                    " eaa.cod_estado_actual codeaa, eaa.nombre nomeaa, eaa.sigla eaasigla" +                    
                    " from movimientos.inventario inv "+
                    " join estado_actual_articulo eaa using (cod_estado_actual)" +
                    " join articulo ar using(cod_articulo) "+
                    " join marca marca using (cod_marca) " +
                    " join ubicaciones ub using (cod_ubicacion) " +
                    " join estado_articulo edoart using (cod_estado_articulo)"
                    
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Inventario> inventarios = new ArrayList<>();
            while (rs.next()) {
                Inventario inv= new Inventario();
                inv.setSerial(rs.getString("serial"));
                inv.setArticulo(new Articulo(rs.getInt("codar"), rs.getString("descart"), rs.getString("modeloart"), null));
                inv.getArticulo().setMarca(new Marca(rs.getInt("codmarca"), rs.getString("nommarca")));
                inv.setUbicacion(new Ubicacion(rs.getInt("codub"), rs.getString("nomub"), null, null, null));
                inv.setEdoArticulo(new EstadoArticulo(rs.getInt("codedoart"), rs.getString("nomedoart")));
                inv.setObservacion(rs.getString("obs"));                
                inv.setFechaCompra(rs.getDate("fechCompra"));
                inv.setEdoActual(new EstadoActualArticulo(rs.getInt("codeaa"), rs.getString("nomeaa"), rs.getString("eaasigla")));
                
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
                    " select cod_menu, cod_submenu, nombre, direc_dialogo "
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
                su.setDirDialogo(rs.getString("direc_dialogo"));
                
                
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



    public Collection<? extends Submenucategoria> cargarSubmenucategoria(Submenu submenu) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " select cod_menu, cod_submenu, cod_categoria, nombre, direc_dialogo "
                    + " from submenucategoria "
                    + " where cod_menu='"+submenu.getCodMenu()+"' and cod_submenu='"+submenu.getCodSubMenu()+"' "
                    + " ORDER BY cod_categoria asc"
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Submenucategoria> submenucategoriaList = new ArrayList<>();
            while (rs.next()) {
                Submenucategoria suc=new Submenucategoria();
                suc.setCodSubMenu(rs.getString("cod_submenu"));
                suc.setNombre(rs.getString("nombre"));
                suc.setCodMenu(rs.getString("cod_menu"));
                suc.setCodCategoria(rs.getString("cod_categoria"));
                suc.setDirDialogo(rs.getString("direc_dialogo"));
                submenucategoriaList.add(suc);                
            }
            return submenucategoriaList;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }   

    public Collection<? extends Inventario> cargarInventariosUbicacionList(Integer codUbicacion) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " select inv.serial serial, ar.cod_articulo codar,ar.descripcion descart, ar.modelo modeloart, marca.cod_marca codmarca, marca.nombre nommarca, ub.cod_ubicacion codub, ub.nombre nomub, " +
                    " edoart.cod_estado_articulo codedoart, edoart.nombre nomedoart, inv.observacion obs,inv.fecha_compra fechCompra,"+
                    " eaa.cod_estado_actual codeaa, eaa.nombre nomeaa, eaa.sigla eaasigla" +                    
                    " from movimientos.inventario inv "+
                    " join estado_actual_articulo eaa using (cod_estado_actual)" +
                    " join articulo ar using(cod_articulo) "+
                    " join marca marca using (cod_marca) " +
                    " join ubicaciones ub using (cod_ubicacion) " +
                    " join estado_articulo edoart using (cod_estado_articulo)"+
                    " where cod_ubicacion='"+codUbicacion+"' and cod_estado_actual='2'"
                    
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Inventario> inventarios = new ArrayList<>();            
            while (rs.next()) {
                Inventario inv= new Inventario();
                inv.setSerial(rs.getString("serial"));
                inv.setArticulo(new Articulo(rs.getInt("codar"), rs.getString("descart"), rs.getString("modeloart"), null));
                inv.getArticulo().setMarca(new Marca(rs.getInt("codmarca"), rs.getString("nommarca")));
                inv.setUbicacion(new Ubicacion(rs.getInt("codub"), rs.getString("nomub"), null, null, null));
                inv.setEdoArticulo(new EstadoArticulo(rs.getInt("codedoart"), rs.getString("nomedoart")));
                inv.setObservacion(rs.getString("obs"));                
                inv.setFechaCompra(rs.getDate("fechCompra"));
                inv.setEdoActual(new EstadoActualArticulo(rs.getInt("codeaa"), rs.getString("nomeaa"), rs.getString("eaasigla")));
                
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

    public void guardarPrestamo(Prestamo pr) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO movimientos.prestamo("
                    + " cod_prestamo, cod_proveedor, salida_ingreso,en_prestamo, fecha_devolucion,serial, observacion)"
                    + " VALUES ('" + pr.getCodPrestamo()+"','"+pr.getProveedor().getCodProveedor()+"' ,'"+pr.getIngreso()+"','"+pr.getEnPrestamo()+"', "
                    + " '"+pr.getFechaDevolucion()+"','"+pr.getInventario().getSerial()+"','"+pr.getObservacion()+"')"
                    + " ON CONFLICT (cod_prestamo, serial) DO UPDATE SET "
                    + " cod_prestamo = EXCLUDED.cod_prestamo , cod_proveedor=EXCLUDED.cod_proveedor, salida_ingreso=EXCLUDED.salida_ingreso,"
                    + " en_prestamo=EXCLUDED.en_prestamo, fecha_devolucion=EXCLUDED.fecha_devolucion, serial=EXCLUDED.serial, observacion=EXCLUDED.observacion "
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Prestamo> cargarPrestamoSalidaList() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " SELECT pre.cod_prestamo codpre, pro.cod_proveedor codpro, pro.nombre nompro, pre.en_prestamo enprestamo, pre.fecha_devolucion fechdev, " +
                    " inv.serial serial, inv.cod_estado_articulo codedoart, edoart.nombre nomedoart,  art.cod_articulo codart, art.descripcion descart, " +
                    " pre.observacion obspre " +
                    " FROM movimientos.prestamo pre " +
                    " join movimientos.inventario inv using(serial) " +
                    " join articulo art using(cod_articulo) " +
                    " join estado_articulo edoart on (edoart.cod_estado_articulo=inv.cod_estado_articulo) " +
                    " join proveedor pro using (cod_proveedor)"+
                    " where salida_ingreso='false'"
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Prestamo> prestamosList = new ArrayList<>();
            while (rs.next()) {
                Prestamo p=new Prestamo();
                p.setCodPrestamo(rs.getInt("codpre"));
                p.setProveedor(new Proveedor(rs.getInt("codpro"), rs.getString("nompro"), null, null, null, null));
                p.setEnPrestamo(rs.getBoolean("enprestamo"));
                p.setFechaDevolucion(rs.getDate("fechdev"));
                p.setInventario(new Inventario(rs.getString("serial"), null, null, null));
                p.getInventario().setArticulo(new Articulo(rs.getInt("codart"), rs.getString("descart"), null, null));
                p.getInventario().getArticulo().setEdoArticulo(new EstadoArticulo(rs.getInt("codedoart"), rs.getString("nomedoart")));
                p.setObservacion(rs.getString("obspre"));                
                
                prestamosList.add(p);                
            }
            return prestamosList;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }
    
    public Collection<? extends Prestamo> cargarPrestamoIngresoList() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " SELECT pre.cod_prestamo codpre, pro.cod_proveedor codpro, pro.nombre nompro, pre.en_prestamo enprestamo, pre.fecha_devolucion fechdev, " +
                    " inv.serial serial, inv.cod_estado_articulo codedoart, edoart.nombre nomedoart,  art.cod_articulo codart, art.descripcion descart, " +
                    " pre.observacion obspre " +
                    " FROM movimientos.prestamo pre " +
                    " join movimientos.inventario inv using(serial) " +
                    " join articulo art using(cod_articulo) " +
                    " join estado_articulo edoart on (edoart.cod_estado_articulo=inv.cod_estado_articulo) " +
                    " join proveedor pro using (cod_proveedor)"+
                    " where salida_ingreso='true'"
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Prestamo> prestamosList = new ArrayList<>();
            while (rs.next()) {
                Prestamo p=new Prestamo();
                p.setCodPrestamo(rs.getInt("codpre"));
                p.setProveedor(new Proveedor(rs.getInt("codpro"), rs.getString("nompro"), null, null, null, null));
                p.setEnPrestamo(rs.getBoolean("enprestamo"));
                p.setFechaDevolucion(rs.getDate("fechdev"));
                p.setInventario(new Inventario(rs.getString("serial"), null, null, null));
                p.getInventario().setArticulo(new Articulo(rs.getInt("codart"), rs.getString("descart"), null, null));
                p.getInventario().getArticulo().setEdoArticulo(new EstadoArticulo(rs.getInt("codedoart"), rs.getString("nomedoart")));
                p.setObservacion(rs.getString("obspre"));                
                
                prestamosList.add(p);                
            }
            return prestamosList;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Prestamo> cargarPrestamoTotales() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " SELECT pre.cod_prestamo codpre, pro.cod_proveedor codpro, pro.nombre nompro, pre.en_prestamo enprestamo, pre.fecha_devolucion fechdev, " +
                    " inv.serial serial, pre.salida_ingreso salingpre, inv.cod_estado_articulo codedoart, edoart.nombre nomedoart,  art.cod_articulo codart, art.descripcion descart, " +
                    " pre.observacion obspre " +
                    " FROM movimientos.prestamo pre " +
                    " join movimientos.inventario inv using(serial) " +
                    " join articulo art using(cod_articulo) " +
                    " join estado_articulo edoart on (edoart.cod_estado_articulo=inv.cod_estado_articulo) " +
                    " join proveedor pro using (cod_proveedor)"                    
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Prestamo> prestamosList = new ArrayList<>();
            while (rs.next()) {
                Prestamo p=new Prestamo();
                p.setCodPrestamo(rs.getInt("codpre"));
                p.setProveedor(new Proveedor(rs.getInt("codpro"), rs.getString("nompro"), null, null, null, null));
                p.setEnPrestamo(rs.getBoolean("enprestamo"));
                p.setFechaDevolucion(rs.getDate("fechdev"));
                p.setInventario(new Inventario(rs.getString("serial"), null, null, null));
                p.getInventario().setArticulo(new Articulo(rs.getInt("codart"), rs.getString("descart"), null, null));
                p.getInventario().getArticulo().setEdoArticulo(new EstadoArticulo(rs.getInt("codedoart"), rs.getString("nomedoart")));
                p.setIngreso(rs.getBoolean("salingpre"));
                p.setObservacion(rs.getString("obspre"));                
                
                prestamosList.add(p);                
            }
            return prestamosList;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Prestamo> cargarPrestamos(String condicion) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    " SELECT pre.cod_prestamo codpre, pro.cod_proveedor codpro, pro.nombre nompro, pre.en_prestamo enprestamo, pre.fecha_devolucion fechdev, " +
                    " inv.serial serial, pre.salida_ingreso salingpre, inv.cod_estado_articulo codedoart, edoart.nombre nomedoart,  art.cod_articulo codart, art.descripcion descart, " +
                    " pre.observacion obspre " +
                    " FROM movimientos.prestamo pre " +
                    " join movimientos.inventario inv using(serial) " +
                    " join articulo art using(cod_articulo) " +
                    " join estado_articulo edoart on (edoart.cod_estado_articulo=inv.cod_estado_articulo) " +
                    " join proveedor pro using (cod_proveedor) "+
                    condicion
                            
            );
            rs = consulta.ejecutar(sql);
            ArrayList<Prestamo> prestamosList = new ArrayList<>();
            while (rs.next()) {
                Prestamo p=new Prestamo();
                p.setCodPrestamo(rs.getInt("codpre"));
                p.setProveedor(new Proveedor(rs.getInt("codpro"), rs.getString("nompro"), null, null, null, null));
                p.setEnPrestamo(rs.getBoolean("enprestamo"));
                p.setFechaDevolucion(rs.getDate("fechdev"));
                p.setInventario(new Inventario(rs.getString("serial"), null, null, null));
                p.getInventario().setArticulo(new Articulo(rs.getInt("codart"), rs.getString("descart"), null, null));
                p.getInventario().getArticulo().setEdoArticulo(new EstadoArticulo(rs.getInt("codedoart"), rs.getString("nomedoart")));
                p.setIngreso(rs.getBoolean("salingpre"));
                p.setObservacion(rs.getString("obspre"));                
                
                prestamosList.add(p);                
            }
            return prestamosList;
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
