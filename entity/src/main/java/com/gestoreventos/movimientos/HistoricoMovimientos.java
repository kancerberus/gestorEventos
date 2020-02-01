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
@Table(name = "historico_movimientos")
@NamedQueries({
    @NamedQuery(name = "HistoricoMovimientos.findAll", query = "SELECT hm FROM HistoricoMovimientos hm")})
public class HistoricoMovimientos implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_movimiento")
    private Integer codMoviento;
    private Articulo articulo=new Articulo();   
    @Column(name = "tipo_movimiento")
    private Boolean tipoMovimiento;
    @Column(name = "fecha_movimiento")
    private Date fechaMovimiento;
    @Column(name = "cod_razon")
    private RazonMovimiento razonMovimiento=new RazonMovimiento();  
    private Proveedor proveedor=new Proveedor();
    
    public HistoricoMovimientos() {        
        
    }    

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Integer getCodMoviento() {
        return codMoviento;
    }

    public void setCodMoviento(Integer codMoviento) {
        this.codMoviento = codMoviento;
    }

    public Boolean getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Boolean tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }   

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public RazonMovimiento getRazonMovimiento() {
        return razonMovimiento;
    }

    public void setRazonMovimiento(RazonMovimiento razonMovimiento) {
        this.razonMovimiento = razonMovimiento;
    }

   
    

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codMoviento != null ? codMoviento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoMovimientos)) {
            return false;
        }
        HistoricoMovimientos other = (HistoricoMovimientos) object;
        if ((this.codMoviento == null && other.codMoviento != null) || (this.codMoviento != null && !this.codMoviento.equals(other.codMoviento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestoreventos.movimientos.historico_movimiento[ cod_movimiento=" + codMoviento + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

}
