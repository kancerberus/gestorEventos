/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.movimientos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;

/**
 *
 * @author juliano
 */
@Entity
@Table(name = "razon_movimiento")
@NamedQueries({
    @NamedQuery(name = "RazonMovimiento.findAll", query = "SELECT rm FROM RazonMovimiento rm")})
public class RazonMovimiento implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_razon")
    private Integer codRazon;
    @Column(name = "nombre")
    private String nombre;   
       
    public RazonMovimiento() {        
    }

    public RazonMovimiento(Integer codRazon, String nombre) {
        this.codRazon = codRazon;
        this.nombre = nombre;
    }

    public Integer getCodRazon() {
        return codRazon;
    }

    public void setCodRazon(Integer codRazon) {
        this.codRazon = codRazon;
    }   
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codRazon != null ? codRazon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RazonMovimiento)) {
            return false;
        }
        RazonMovimiento other = (RazonMovimiento) object;
        if ((this.codRazon == null && other.codRazon != null) || (this.codRazon != null && !this.codRazon.equals(other.codRazon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestoreventos.movimientos.RazonMovimiento[ cod_razon=" + codRazon + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

}
