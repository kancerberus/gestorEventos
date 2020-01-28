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
@Table(name = "proveedor")
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT prov FROM Proveedor prov")})
public class Proveedor implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_proveedor")
    private Integer codProveedor;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "nom_encargado")
    private String nomEncargado;
    @Column(name = "tel_encargado")
    private String tel_encargado;
       
    public Proveedor() {        
    }

    public Proveedor(Integer codProveedor, String nombre, String direccion, String telefono, String nomEncargado, String tel_encargado) {
        this.codProveedor = codProveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nomEncargado = nomEncargado;
        this.tel_encargado = tel_encargado;
    }

    public Integer getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(Integer codProveedor) {
        this.codProveedor = codProveedor;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNomEncargado() {
        return nomEncargado;
    }

    public void setNomEncargado(String nomEncargado) {
        this.nomEncargado = nomEncargado;
    }

    public String getTel_encargado() {
        return tel_encargado;
    }

    public void setTel_encargado(String tel_encargado) {
        this.tel_encargado = tel_encargado;
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codProveedor != null ? codProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.codProveedor == null && other.codProveedor != null) || (this.codProveedor != null && !this.codProveedor.equals(other.codProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestoreventos.publico.Proveedor[ cod_proveedor=" + codProveedor + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

}
