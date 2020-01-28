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
 * @author juliano
 */
@Entity
@Table(name = "marca")
@NamedQueries({
    @NamedQuery(name = "Marca.findAll", query = "SELECT mar FROM Marca mar")})
public class Marca implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_marca")
    private Integer codMarca;
    @Column(name = "nombre")
    private String nombre;   
       
    public Marca() {        
    }

    public Marca(Integer codMarca, String nombre) {
        this.codMarca = codMarca;
        this.nombre = nombre;
    }

    
    public Integer getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(Integer codMarca) {
        this.codMarca = codMarca;
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
        hash += (codMarca != null ? codMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marca)) {
            return false;
        }
        Marca other = (Marca) object;
        if ((this.codMarca == null && other.codMarca != null) || (this.codMarca != null && !this.codMarca.equals(other.codMarca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestoreventos.publico.Marca[ cod_marca=" + codMarca + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

}
