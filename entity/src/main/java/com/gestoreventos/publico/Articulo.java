/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "articulo")
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT art FROM Articulo art")})
public class Articulo implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_articulo")
    private Integer codArticulo;
    @Column(name = "descripcion")
    private String descripcion;   
    @Column(name = "modelo")
    private String modelo;  
    @Column(name = "serial")
    private String serial;   
    @Column(name = "fecha_compra")
    private Date fechaCompra;   
    @Column(name = "observaciones")
    private String observaciones;   
    @Column(name = "ult_mantenimiento")
    private Date ultMantenimiento; 
    
    private Marca marca=new Marca();
    private EstadoArticulo edoArticulo=new EstadoArticulo();
    private Ubicacion ubicacion=new Ubicacion();
    
    public Articulo() {        
        edoArticulo=new EstadoArticulo();
        ubicacion=new Ubicacion();
    }

    public Articulo(Integer codArticulo, String descripcion, String modelo, String serial, Date fechaCompra, String observaciones, Date ultMantenimiento) {
        this.codArticulo = codArticulo;
        this.descripcion = descripcion;
        this.modelo = modelo;
        this.serial = serial;
        this.fechaCompra = fechaCompra;
        this.observaciones = observaciones;
        this.ultMantenimiento = ultMantenimiento;
    }

    public EstadoArticulo getEdoArticulo() {
        return edoArticulo;
    }

    public void setEdoArticulo(EstadoArticulo edoArticulo) {
        this.edoArticulo = edoArticulo;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }    
    
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }    

    public Integer getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(Integer codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getUltMantenimiento() {
        return ultMantenimiento;
    }

    public void setUltMantenimiento(Date ultMantenimiento) {
        this.ultMantenimiento = ultMantenimiento;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codArticulo != null ? codArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.codArticulo == null && other.codArticulo != null) || (this.codArticulo != null && !this.codArticulo.equals(other.codArticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestoreventos.publico.Articulo[ cod_articulo=" + codArticulo + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

}
