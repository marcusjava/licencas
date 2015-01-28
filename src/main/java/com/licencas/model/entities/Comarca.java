/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Marcus
 */
@Entity
@Table(name = "comarca")
public class Comarca implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name="com_id",nullable = false)
    private Integer id;
    
    @Column(nullable = false,unique = true)
    private String com_desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public String getCom_desc() {
        return com_desc;
    }

    public void setCom_desc(String com_desc) {
        this.com_desc = com_desc;
    }
    
    @OneToMany(mappedBy = "comarca", fetch = FetchType.LAZY)
    @ForeignKey(name="comarca_foro")
    private List<Foro> foros;

    public List<Foro> getForos() {
        return foros;
    }

    public void setForos(List<Foro> foros) {
        this.foros = foros;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.com_desc != null ? this.com_desc.hashCode() : 0);
        hash = 67 * hash + (this.foros != null ? this.foros.hashCode() : 0);
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
        final Comarca other = (Comarca) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.com_desc == null) ? (other.com_desc != null) : !this.com_desc.equals(other.com_desc)) {
            return false;
        }
        if (this.foros != other.foros && (this.foros == null || !this.foros.equals(other.foros))) {
            return false;
        }
        return true;
    }

    

    
    
    
    
}
