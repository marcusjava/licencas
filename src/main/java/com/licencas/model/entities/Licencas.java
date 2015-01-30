/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.model.entities;


import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author Marcus
 */
@Entity
@Table(name ="licenca")
public class Licencas implements Serializable{
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue
    @Column(name = "lic_id")
    private Integer id;
    
    @Column(nullable = false,unique = true,length = 16)
    private String lic_desc;
    
    @Column(name = "lic_status",nullable = false)
    private String status;
    
    @OneToOne(mappedBy = "licenca",cascade = CascadeType.MERGE)
    private Local local;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public String getLic_desc() {
        return lic_desc;
    }

    public void setLic_desc(String lic_desc) {
        this.lic_desc = lic_desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.lic_desc != null ? this.lic_desc.hashCode() : 0);
        hash = 29 * hash + (this.status != null ? this.status.hashCode() : 0);
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
        final Licencas other = (Licencas) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.lic_desc == null) ? (other.lic_desc != null) : !this.lic_desc.equals(other.lic_desc)) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        return true;
    }

    
    
    
    
    
}
