/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestoreventos.publico;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author juliano
 */
@Entity
@Table(name = "privilegios")
@NamedQueries({
    @NamedQuery(name = "Privilegios.findAll", query = "SELECT p FROM Privilegios p")})
public class Privilegios implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrivilegiosPK privilegiosPK;
    @JoinColumn(name = "cod_operacion", referencedColumnName = "cod_operacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Operaciones operaciones;
    @JoinColumn(name = "cod_rol", referencedColumnName = "codigo_rol", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Roles roles;



    public Privilegios() {
    }

    public Privilegios(PrivilegiosPK privilegiosPK) {
        this.privilegiosPK = privilegiosPK;
    }

    public Privilegios(long codTransaccion, int codOperacion, long codRol) {
        this.privilegiosPK = new PrivilegiosPK(codTransaccion, codOperacion, codRol);
    }

    public PrivilegiosPK getPrivilegiosPK() {
        return privilegiosPK;
    }

    public void setPrivilegiosPK(PrivilegiosPK privilegiosPK) {
        this.privilegiosPK = privilegiosPK;
    }

    public Operaciones getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(Operaciones operaciones) {
        this.operaciones = operaciones;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (privilegiosPK != null ? privilegiosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilegios)) {
            return false;
        }
        Privilegios other = (Privilegios) object;
        if ((this.privilegiosPK == null && other.privilegiosPK != null) || (this.privilegiosPK != null && !this.privilegiosPK.equals(other.privilegiosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestor.publico.Privilegios[ privilegiosPK=" + privilegiosPK + " ]";
    }
    
}
