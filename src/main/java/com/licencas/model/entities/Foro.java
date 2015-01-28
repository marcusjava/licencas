/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.model.entities;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.ForeignKey;
/**
 *
 * @author Marcus
 */
@Entity
@Table(name = "foro")
public class Foro implements Serializable{

    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue
    @Column(name = "for_id", nullable = false)
    private Integer id;
    
    @Column(nullable = false,length = 45,unique = true)
    private String for_desc;
    
    @ManyToOne(optional = false)
    @ForeignKey(name = "foro_comarca")
    @JoinColumn(name = "com_id",referencedColumnName = "com_id")
    private Comarca comarca;
    
    @OneToMany(mappedBy = "foro", fetch = FetchType.LAZY)
    @ForeignKey(name="foro_local")
    private List<Local> locais;
    
    public Foro()
    {
        this.comarca = new Comarca();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }

    
    
    public String getFor_desc() {
        return for_desc;
    }

    public void setFor_desc(String for_desc) {
        this.for_desc = for_desc;
    }

    public Comarca getComarca() {
        return comarca;
    }

    public void setComarca(Comarca comarca) {
        this.comarca = comarca;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 73 * hash + (this.for_desc != null ? this.for_desc.hashCode() : 0);
        hash = 73 * hash + (this.comarca != null ? this.comarca.hashCode() : 0);
        hash = 73 * hash + (this.locais != null ? this.locais.hashCode() : 0);
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
        final Foro other = (Foro) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.for_desc == null) ? (other.for_desc != null) : !this.for_desc.equals(other.for_desc)) {
            return false;
        }
        if (this.comarca != other.comarca && (this.comarca == null || !this.comarca.equals(other.comarca))) {
            return false;
        }
        if (this.locais != other.locais && (this.locais == null || !this.locais.equals(other.locais))) {
            return false;
        }
        return true;
    }

    

    
   

    
    
    
    
}
