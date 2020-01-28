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
@Table(name = "ubicaciones")
@NamedQueries({
    @NamedQuery(name = "Ubicaciones.findAll", query = "SELECT ubi FROM Ubicaciones ubi")})
public class Ubicacion implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_ubicacion")
    private Integer codUbicacion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "estado")
    private Boolean estado;
    
    private Municipios municipio;
       
    public Ubicacion() {        
    }

    public Ubicacion(Integer codUbicacion, String nombre, String direccion, String telefono, Boolean estado) {
        this.codUbicacion = codUbicacion;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
    }
    
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getCodUbicacion() {
        return codUbicacion;
    }

    public void setCodUbicacion(Integer codUbicacion) {
        this.codUbicacion = codUbicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUbicacion != null ? codUbicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubicacion)) {
            return false;
        }
        Ubicacion other = (Ubicacion) object;
        if ((this.codUbicacion == null && other.codUbicacion != null) || (this.codUbicacion != null && !this.codUbicacion.equals(other.codUbicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestoreventos.publico.Ubicacion[ cod_ubicacion=" + codUbicacion + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

}
