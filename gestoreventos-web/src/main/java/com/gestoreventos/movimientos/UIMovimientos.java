/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.movimientos;

import com.gestoreventos.publico.*;
import com.gestoreventos.controller.GestorGeneral;
import com.gestoreventos.entity.Dialogo;

import com.gestoreventos.entity.UtilJSF;
import com.gestoreventos.entity.UtilLog;
import com.gestoreventos.entity.UtilMSG;
import com.gestoreventos.movimientos.controlador.GestorMovimiento;
import com.gestoreventos.publico.controlador.GestorArticulo;
import com.gestoreventos.publico.controlador.GestorUbicacion;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author fjvc
 */
@ManagedBean(name = "uiMovimientos")
@SessionScoped

public class UIMovimientos {    

    private boolean guardarActivo = false;
    private boolean nuevoActivo = true;
    private boolean eliminarActivo = false;
    private boolean cancelarActivo = false;
    private boolean consultarActivo = false;
    private boolean volverActivo = false;    
    
    
    private Articulo articulo;
    private Inventario inventario;
    private HistoricoMovimientos historicoMovimiento=new HistoricoMovimientos();
    private ArrayList<Articulo> articulosList=new ArrayList<>();
    private ArrayList<Inventario> inventariosList=new ArrayList<>();
    private ArrayList<HistoricoMovimientos> mantenimientosList=new ArrayList<>();
    private ArrayList<Marca> marcasList=new ArrayList<>();
    private ArrayList<EstadoArticulo> estadoArticuloList=new ArrayList<>();
    private ArrayList<Ubicacion> ubicacionesList=new ArrayList<>();    
    private ArrayList<RazonMovimiento> razonesMovimientoList=new ArrayList<>();    
    
    private GestorArticulo gestorArticulo;
    private GestorMovimiento gestorMovimiento;
    private GestorGeneral gestorGeneral;
    private Menu menu;
    private Submenu submenu;
    
    private ArrayList<Menu> itemsMenu=new ArrayList<>();
    private ArrayList<Submenu> itemsSubmenu=new ArrayList<>();
    

    public void cancelar() {
    }
    
    @PostConstruct
    public void init() {
        this.cargarArticulosList();
        this.cargarMarcasList();
        this.cargarEstadosArticuloList();
        this.cargarUbicacionesList();   
        this.cargarInventariosList();
        this.cargarRazonMovimientosList();
        this.cargarMantenimientosList();       
        this.cargarListaMenu();
        
        articulo=new Articulo();
        inventario=new Inventario();
        inventario.setArticulo(new Articulo());
        submenu=new Submenu();
        inventario.getArticulo().setEdoArticulo(new EstadoArticulo());
        historicoMovimiento=new HistoricoMovimientos();
    }
    
    
    public UIMovimientos() {            
        submenu=new Submenu();
        articulo=new Articulo();
        inventario=new Inventario();
        inventario.setArticulo(new Articulo());
        
        inventario.getArticulo().setEdoArticulo(new EstadoArticulo());
        historicoMovimiento=new HistoricoMovimientos();
        this.cargarListaMenu();
        this.cargarArticulosList();
        this.cargarEstadosArticuloList();
        this.cargarInventariosList();
        this.cargarRazonMovimientosList();
        this.cargarMantenimientosList();
    }
    
    public void guardarInventario() throws Exception{        
        
        try {
            
            gestorMovimiento=new GestorMovimiento();
            gestorArticulo=new GestorArticulo();
            gestorGeneral=new GestorGeneral();  
            
            gestorMovimiento.validarInventario(inventario);
//            gestorMovimiento.guardarInventario(inventario);
            
            UtilMSG.addSuccessMsg("Guardado Con Exito");
            inventario=new Inventario();
            
            this.cargarInventariosList();
            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
    }
    
    public void cargarListaMenu(){
        try {
            
            gestorMovimiento=new GestorMovimiento();
            itemsMenu.clear();
            
            itemsMenu.addAll(gestorMovimiento.cargarItemsMenu());
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
            
            
    }
    
    public void guardarMantenimiento(){
        try {
            gestorGeneral=new GestorGeneral();
            gestorMovimiento=new GestorMovimiento();
            
            if(historicoMovimiento.getCodMoviento()==null){
                historicoMovimiento.setCodMoviento(gestorGeneral.nextval(GestorGeneral.MOVIMIENTOS_HISTORICO_MOVIMIENTOS_COD_MOVIMIENTO_SEQ).intValue());
            }
            
            gestorMovimiento.validarMantenimiento(historicoMovimiento);
            gestorMovimiento.guardarMantenimiento(historicoMovimiento);
            UtilMSG.addSuccessMsg("Guardado Con Exito");
            
            historicoMovimiento=new HistoricoMovimientos();            
            this.cargarMantenimientosList();
            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    
    public void subirMantenimiento(){
        try {            
            historicoMovimiento=(HistoricoMovimientos) UtilJSF.getBean("varMantenimiento");                
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void subirInventario(){
        try {            
            inventario=(Inventario) UtilJSF.getBean("varInventario");                
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void eliminarInventario(){
        try {
            
            gestorMovimiento=new GestorMovimiento();            
            
            inventario=(Inventario) UtilJSF.getBean("varInventario");
            gestorMovimiento.eliminarInventario(inventario);
            
            UtilMSG.addSuccessMsg("Eliminado Con Exito");
            inventario=new Inventario();            
            this.cargarInventariosList();
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    

    public void cargarInventariosList(){
        try {
            
            inventariosList=new ArrayList<>();
            gestorMovimiento=new GestorMovimiento();
            inventariosList.addAll(gestorMovimiento.cargarInventariosList());            
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    
    public void cargarArticulosList(){
        try {
            articulosList=new ArrayList<>();
            gestorArticulo=new GestorArticulo();
            articulosList.addAll(gestorArticulo.cargarArticulosList());
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void cargarMarcasList(){
        try {
            
            marcasList=new ArrayList<>();
            gestorArticulo=new GestorArticulo();
            marcasList.addAll(gestorArticulo.cargarMarcasList());

        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
    }
    
    public void cargarTipoMovimientoList(){
        try {
            
            estadoArticuloList=new ArrayList<>();
            gestorArticulo=new GestorArticulo();
            estadoArticuloList.addAll(gestorArticulo.cargarEstadosArticuloList());

        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
    }
    
    public void cargarEstadosArticuloList(){
        try {
            
            estadoArticuloList=new ArrayList<>();
            gestorArticulo=new GestorArticulo();
            estadoArticuloList.addAll(gestorArticulo.cargarEstadosArticuloList());

        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
    }
    
    public void cargarUbicacionesList(){
        try {
            
            ubicacionesList=new ArrayList<>();
            GestorUbicacion gestorUbicacion=new GestorUbicacion();
            ubicacionesList.addAll(gestorUbicacion.cargarUbicacionesList());

        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        
    }
    
    public void cargarRazonMovimientosList(){
        try {
            
            razonesMovimientoList=new ArrayList<>();
            gestorMovimiento=new GestorMovimiento();
            razonesMovimientoList.addAll(gestorMovimiento.cargarRazonesMovimiento());
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public void cargarMantenimientosList(){
        try {
            
            mantenimientosList=new ArrayList<>();
            gestorMovimiento=new GestorMovimiento();
            mantenimientosList.addAll(gestorMovimiento.cargarMantenimientosList());
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
    }
    
    public String cargarSubmenuList(){
        try {
            itemsSubmenu.clear();
            gestorMovimiento=new GestorMovimiento();
            
            menu=(Menu) UtilJSF.getBean("varMenu");
            
            itemsSubmenu.addAll(gestorMovimiento.cargarSubmenu(menu));
            
            return ("/movimientos/submenu-eventos.xhtml?faces-redirect=true");
            
        } catch (Exception e) {
            UtilMSG.addSupportMsg();
            UtilLog.generarLog(this.getClass(), e);
        }
        return ("/movimientos/submenu-eventos.xhtml?faces-redirect=true");
    }
    
    public String muestraDialogo(){
        try {
            gestorMovimiento=new GestorMovimiento();
            
            submenu=(Submenu) UtilJSF.getBean("varSubmenu");
            
            String dirDialogo=gestorMovimiento.cargarDireccionDialogo(submenu);
            
            
            
            
            Dialogo dialogo = new Dialogo(dirDialogo, submenu.getNombre(), "clip", Dialogo.WIDTH_AUTO);
            UtilJSF.setBean("dialogo", dialogo, UtilJSF.SESSION_SCOPE);
            UtilJSF.execute("PF('dialog').show();");
            this.cargarArticulosList();            
            this.cargarEstadosArticuloList();
            this.cargarUbicacionesList();   
            this.cargarInventariosList();                        
        } catch (Exception e) {
            UtilMSG.addSuccessMsg("En construccion");
        }
        return null;
    }

    public ArrayList<Submenu> getItemsSubmenu() {
        return itemsSubmenu;
    }

    public void setItemsSubmenu(ArrayList<Submenu> itemsSubmenu) {
        this.itemsSubmenu = itemsSubmenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public ArrayList<Menu> getItemsMenu() {
        return itemsMenu;
    }

    public void setItemsMenu(ArrayList<Menu> itemsMenu) {
        this.itemsMenu = itemsMenu;
    }


    
    public ArrayList<HistoricoMovimientos> getMantenimientosList() {
        return mantenimientosList;
    }

    public void setMantenimientosList(ArrayList<HistoricoMovimientos> mantenimientosList) {
        this.mantenimientosList = mantenimientosList;
    }

    public ArrayList<RazonMovimiento> getRazonesMovimientoList() {
        return razonesMovimientoList;
    }

    public void setRazonesMovimientoList(ArrayList<RazonMovimiento> razonesMovimientoList) {
        this.razonesMovimientoList = razonesMovimientoList;
    }

    public HistoricoMovimientos getHistoricoMovimiento() {
        return historicoMovimiento;
    }

    public void setHistoricoMovimiento(HistoricoMovimientos historicoMovimiento) {
        this.historicoMovimiento = historicoMovimiento;
    }

    public ArrayList<Inventario> getInventariosList() {
        return inventariosList;
    }

    public void setInventariosList(ArrayList<Inventario> inventariosList) {
        this.inventariosList = inventariosList;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public ArrayList<Marca> getMarcasList() {
        return marcasList;
    }

    public void setMarcasList(ArrayList<Marca> marcasList) {
        this.marcasList = marcasList;
    }

    public ArrayList<EstadoArticulo> getEstadoArticuloList() {
        return estadoArticuloList;
    }

    public void setEstadoArticuloList(ArrayList<EstadoArticulo> estadoArticuloList) {
        this.estadoArticuloList = estadoArticuloList;
    }

    public ArrayList<Ubicacion> getUbicacionesList() {
        return ubicacionesList;
    }

    public void setUbicacionesList(ArrayList<Ubicacion> ubicacionesList) {
        this.ubicacionesList = ubicacionesList;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public ArrayList<Articulo> getArticulosList() {
        return articulosList;
    }

    public void setArticulosList(ArrayList<Articulo> articulosList) {
        this.articulosList = articulosList;
    }    

    public boolean isGuardarActivo() {
        return guardarActivo;
    }

    public void setGuardarActivo(boolean guardarActivo) {
        this.guardarActivo = guardarActivo;
    }

    public boolean isNuevoActivo() {
        return nuevoActivo;
    }

    public void setNuevoActivo(boolean nuevoActivo) {
        this.nuevoActivo = nuevoActivo;
    }

    public boolean isEliminarActivo() {
        return eliminarActivo;
    }

    public void setEliminarActivo(boolean eliminarActivo) {
        this.eliminarActivo = eliminarActivo;
    }

    public boolean isCancelarActivo() {
        return cancelarActivo;
    }

    public void setCancelarActivo(boolean cancelarActivo) {
        this.cancelarActivo = cancelarActivo;
    }

    public boolean isConsultarActivo() {
        return consultarActivo;
    }

    public void setConsultarActivo(boolean consultarActivo) {
        this.consultarActivo = consultarActivo;
    }

    public boolean isVolverActivo() {
        return volverActivo;
    }

    public void setVolverActivo(boolean volverActivo) {
        this.volverActivo = volverActivo;
    }   
    

}
