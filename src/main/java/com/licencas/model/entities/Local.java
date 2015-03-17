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
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.ForeignKey;


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
    
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @ForeignKey(name = "foro_local")
    @JoinColumn(name = "for_id", referencedColumnName = "for_id")
    private Foro foro;
    
    @OneToMany(mappedBy = "local",fetch = FetchType.LAZY)
    private List<Bem> bens;

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

    public Foro getForo() {
        return foro;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }

    

    public List<Bem> getBens() {
        return bens;
    }

    public void setBens(List<Bem> bens) {
        this.bens = bens;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 89 * hash + (this.loc_desc != null ? this.loc_desc.hashCode() : 0);
        hash = 89 * hash + (this.foro != null ? this.foro.hashCode() : 0);
        hash = 89 * hash + (this.bens != null ? this.bens.hashCode() : 0);
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
        if (this.foro != other.foro && (this.foro == null || !this.foro.equals(other.foro))) {
            return false;
        }
        if (this.bens != other.bens && (this.bens == null || !this.bens.equals(other.bens))) {
            return false;
        }
        return true;
    }

    
    

}
