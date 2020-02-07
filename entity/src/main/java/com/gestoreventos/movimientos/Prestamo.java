/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.movimientos;

import com.gestoreventos.publico.Proveedor;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author franj
 */
@Entity
@Table(name = "prestamo")
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p")})
public class Prestamo implements Serializable {
    
    public static String TIPO_PRESTAMO_INGRESO="SALIDA_INGRESO = (?)";
    public static String PROVEEDOR_COD_PROVEEDOR="PRO.COD_PROVEEDOR = (?)";

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_prestamo")
    private Integer codPrestamo;
    @Column(name = "cod_proveedor")
    private Integer codProveedor;
    @Column(name = "ingreso")
    private Boolean ingreso;
    @Column(name = "cod_estado_actual")
    private Integer codEstadoActual;
    @Column(name = "en_prestamo")
    private Boolean enPrestamo;
    @Column(name = "fecha_devolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
    @Column(name = "serial")
    private String serial;
    @Column(name = "observacion")
    private String observacion;
    
    private Inventario inventario=new Inventario();
    private Proveedor proveedor=new Proveedor();

    public Prestamo() {
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }    
    
    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Prestamo(Integer codPrestamo) {
        this.codPrestamo = codPrestamo;
    }

    public Integer getCodPrestamo() {
        return codPrestamo;
    }

    public void setCodPrestamo(Integer codPrestamo) {
        this.codPrestamo = codPrestamo;
    }

    public Integer getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(Integer codProveedor) {
        this.codProveedor = codProveedor;
    }

    public Boolean getIngreso() {
        return ingreso;
    }

    public void setIngreso(Boolean ingreso) {
        this.ingreso = ingreso;
    }

    public Integer getCodEstadoActual() {
        return codEstadoActual;
    }

    public void setCodEstadoActual(Integer codEstadoActual) {
        this.codEstadoActual = codEstadoActual;
    }

    public Boolean getEnPrestamo() {
        return enPrestamo;
    }

    public void setEnPrestamo(Boolean enPrestamo) {
        this.enPrestamo = enPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPrestamo != null ? codPrestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.codPrestamo == null && other.codPrestamo != null) || (this.codPrestamo != null && !this.codPrestamo.equals(other.codPrestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestoreventos.movimientos.Prestamo[ codPrestamo=" + codPrestamo + " ]";
    }
    
}
