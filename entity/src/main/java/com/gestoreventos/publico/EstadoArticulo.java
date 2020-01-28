/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico;

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
 * @author fjvc
 */
@Entity
@Table(name = "estado_articulo")
@NamedQueries({
    @NamedQuery(name = "EstadoArticulo.findAll", query = "SELECT earti FROM estado_articulo earti")})
public class EstadoArticulo implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_estado_articulo")
    private Integer codEstadoArticulo;
    @Column(name = "nombre")
    private String nombre;   
       
    public EstadoArticulo() {        
    }

    public EstadoArticulo(Integer codEstadoArticulo, String nombre) {
        this.codEstadoArticulo = codEstadoArticulo;
        this.nombre = nombre;
    }    
    
    public Integer getCodEstadoArticulo() {
        return codEstadoArticulo;
    }

    public void setCodEstadoArticulo(Integer codEstadoArticulo) {
        this.codEstadoArticulo = codEstadoArticulo;
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
        hash += (codEstadoArticulo != null ? codEstadoArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoArticulo)) {
            return false;
        }
        EstadoArticulo other = (EstadoArticulo) object;
        if ((this.codEstadoArticulo == null && other.codEstadoArticulo != null) || (this.codEstadoArticulo != null && !this.codEstadoArticulo.equals(other.codEstadoArticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestoreventos.publico.EstadoArticulo[ cod_estado_articulo=" + codEstadoArticulo + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

}
