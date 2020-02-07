/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author franj
 */
@Entity
@Table(name = "submenucategoria")
@NamedQueries({
    @NamedQuery(name = "Submenucategoria.findAll", query = "SELECT s FROM Submenucategoria s")})
public class Submenucategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_menu")
    private String codMenu;
    @Column(name = "cod_submenu")
    private String codSubMenu;
    @Column(name = "cod_categoria")
    private String codCategoria;
    @Column(name = "nombre")
    private String nombre;   
    @Column(name = "direc_dialogo")
    private String dirDialogo; 

    public Submenucategoria() {
    }

    public Submenucategoria(String codMenu, String codSubMenu, String codCategoria, String nombre, String dirDialogo) {
        this.codMenu = codMenu;
        this.codSubMenu = codSubMenu;
        this.codCategoria = codCategoria;
        this.nombre = nombre;
        this.dirDialogo = dirDialogo;
    }
    
    

    
    public String getCodMenu() {
        return codMenu;
    }

    public void setCodMenu(String codMenu) {
        this.codMenu = codMenu;
    }

    public String getCodSubMenu() {
        return codSubMenu;
    }

    public void setCodSubMenu(String codSubMenu) {
        this.codSubMenu = codSubMenu;
    }

    public String getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(String codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getDirDialogo() {
        return dirDialogo;
    }

    public void setDirDialogo(String dirDialogo) {
        this.dirDialogo = dirDialogo;
    }
    
    

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }        
    
}
