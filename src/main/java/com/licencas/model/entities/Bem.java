/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Marcus
 */
@Entity
@Table(name = "bem")
public class Bem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue
    @Column(name = "bem_id")
    private Integer Id;
    
    @ManyToOne(optional = false)
    @JoinColumn
    private Equipamento equipamento;
    
    @ManyToOne(optional = false)
    @JoinColumn
    private Local local;
    
    @Column(name = "bem_pat",length = 6,unique = true,nullable = true)
    private String patrimonio;

    @Column(name = "bem_serie", length = 20)
    private String serial;
    
    @Column(name = "bem_nome_comput", length = 30, nullable = true)
    private String computador;
    
    
    
    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public String getComputador() {
        return computador;
    }

    public void setComputador(String computador) {
        this.computador = computador;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.Id != null ? this.Id.hashCode() : 0);
        hash = 59 * hash + (this.equipamento != null ? this.equipamento.hashCode() : 0);
        hash = 59 * hash + (this.local != null ? this.local.hashCode() : 0);
        hash = 59 * hash + (this.patrimonio != null ? this.patrimonio.hashCode() : 0);
        hash = 59 * hash + (this.serial != null ? this.serial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bem other = (Bem) obj;
        if (this.Id != other.Id && (this.Id == null || !this.Id.equals(other.Id))) {
            return false;
        }
        if (this.equipamento != other.equipamento && (this.equipamento == null || !this.equipamento.equals(other.equipamento))) {
            return false;
        }
        if (this.local != other.local && (this.local == null || !this.local.equals(other.local))) {
            return false;
        }
        if ((this.patrimonio == null) ? (other.patrimonio != null) : !this.patrimonio.equals(other.patrimonio)) {
            return false;
        }
        if ((this.serial == null) ? (other.serial != null) : !this.serial.equals(other.serial)) {
            return false;
        }
        return true;
    }

    
    
    
    
    
    
    
    
}
