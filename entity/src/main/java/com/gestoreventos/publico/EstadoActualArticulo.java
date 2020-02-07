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
 * @author franj
 */
@Entity
@Table(name = "estado_actual_articulo")
@NamedQueries({
    @NamedQuery(name = "EstadoActualArticulo.findAll", query = "SELECT e FROM EstadoActualArticulo e")})
public class EstadoActualArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_estado_actual")
    private Integer codEstadoActual;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "sigla")
    private String sigla;

    public EstadoActualArticulo() {
    }

    public EstadoActualArticulo(Integer codEstadoActual, String nombre, String sigla) {
        this.codEstadoActual = codEstadoActual;
        this.nombre = nombre;
        this.sigla = sigla;
    }
    
    

    public EstadoActualArticulo(Integer codEstadoActual) {
        this.codEstadoActual = codEstadoActual;
    }

    public Integer getCodEstadoActual() {
        return codEstadoActual;
    }

    public void setCodEstadoActual(Integer codEstadoActual) {
        this.codEstadoActual = codEstadoActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEstadoActual != null ? codEstadoActual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoActualArticulo)) {
            return false;
        }
        EstadoActualArticulo other = (EstadoActualArticulo) object;
        if ((this.codEstadoActual == null && other.codEstadoActual != null) || (this.codEstadoActual != null && !this.codEstadoActual.equals(other.codEstadoActual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestoreventos.publico.EstadoActualArticulo[ codEstadoActual=" + codEstadoActual + " ]";
    }
    
}
