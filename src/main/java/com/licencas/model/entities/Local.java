/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.model.entities;

/**
 *
 * @author Marcus
 */

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name ="local")
public class Local implements Serializable{
    
    private static final long serialVersionUID = 1L;
      
    @Id
    @GeneratedValue
    @Column(name = "loc_id",nullable = false)
    private Integer id;
    
    @Column(nullable = false)
    private String loc_desc;
    
    @Column(unique = true)
    private String loc_nome_comp;
    
    @Column(unique = true)
    private String loc_pat_comp;
    
    @ManyToOne
    @JoinColumn(name = "for_id",unique = true)
    private Foro foro;
    
    @OneToOne(optional = true,cascade = CascadeType.MERGE)
    @JoinColumn(name = "lic_id",unique = true)
    private Licencas licenca;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoc_desc() {
        return loc_desc;
    }

    public void setLoc_desc(String loc_desc) {
        this.loc_desc = loc_desc;
    }

    public String getLoc_nome_comp() {
        return loc_nome_comp;
    }

    public void setLoc_nome_comp(String loc_nome_comp) {
        this.loc_nome_comp = loc_nome_comp;
    }

    public String getLoc_pat_comp() {
        return loc_pat_comp;
    }

    public void setLoc_pat_comp(String loc_pat_comp) {
        this.loc_pat_comp = loc_pat_comp;
    }

    public Foro getForo() {
        return foro;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }

    public Licencas getLicenca() {
        return licenca;
    }

    public void setLicenca(Licencas licenca) {
        this.licenca = licenca;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 47 * hash + (this.loc_desc != null ? this.loc_desc.hashCode() : 0);
        hash = 47 * hash + (this.loc_nome_comp != null ? this.loc_nome_comp.hashCode() : 0);
        hash = 47 * hash + (this.loc_pat_comp != null ? this.loc_pat_comp.hashCode() : 0);
        hash = 47 * hash + (this.foro != null ? this.foro.hashCode() : 0);
        hash = 47 * hash + (this.licenca != null ? this.licenca.hashCode() : 0);
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
        final Local other = (Local) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.loc_desc == null) ? (other.loc_desc != null) : !this.loc_desc.equals(other.loc_desc)) {
            return false;
        }
        if ((this.loc_nome_comp == null) ? (other.loc_nome_comp != null) : !this.loc_nome_comp.equals(other.loc_nome_comp)) {
            return false;
        }
        if ((this.loc_pat_comp == null) ? (other.loc_pat_comp != null) : !this.loc_pat_comp.equals(other.loc_pat_comp)) {
            return false;
        }
        if (this.foro != other.foro && (this.foro == null || !this.foro.equals(other.foro))) {
            return false;
        }
        if (this.licenca != other.licenca && (this.licenca == null || !this.licenca.equals(other.licenca))) {
            return false;
        }
        return true;
    }

    
    
    
    
  
    
    
}
