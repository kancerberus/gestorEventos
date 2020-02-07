/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.movimientos;

import com.gestoreventos.publico.*;
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
@Table(name = "inventario")
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT inv FROM Inventario inv")})
public class Inventario implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "serial")
    private String serial;            
    @Column(name = "cod_articulo")
    private Integer codArticulo;            
    @Column(name = "observacion")
    private String observacion;        
    @Column(name = "fecha_compra")
    private Date fechaCompra;    
    
    
    private EstadoActualArticulo edoActual=new EstadoActualArticulo();
    private Articulo articulo=new Articulo();
    private Marca marca=new Marca();
    private Ubicacion ubicacion=new Ubicacion();
    private EstadoArticulo edoArticulo=new EstadoArticulo();
    private HistoricoMovimientos historicoMovimiento=new HistoricoMovimientos();
    
    
    public Inventario() {        
        
    }

    public Inventario(String serial, Integer codArticulo, String observacion, Date fechaCompra) {
        this.serial = serial;
        this.codArticulo = codArticulo;
        this.observacion = observacion;
        this.fechaCompra = fechaCompra;
    }
    
    

    public EstadoActualArticulo getEdoActual() {
        return edoActual;
    }

    public void setEdoActual(EstadoActualArticulo edoActual) {
        this.edoActual = edoActual;
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
    
    public HistoricoMovimientos getHistoricoMovimiento() {
        return historicoMovimiento;
    }

    public void setHistoricoMovimiento(HistoricoMovimientos historicoMovimiento) {
        this.historicoMovimiento = historicoMovimiento;
    }



    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EstadoArticulo getEdoArticulo() {
        return edoArticulo;
    }

    public void setEdoArticulo(EstadoArticulo edoArticulo) {
        this.edoArticulo = edoArticulo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codArticulo != null ? codArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.codArticulo == null && other.codArticulo != null) || (this.codArticulo != null && !this.codArticulo.equals(other.codArticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestoreventos.movimientos.inventario[ cod_articulo=" + codArticulo + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

}
