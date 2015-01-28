/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Marcus
 */

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer codigo;
    
    @Column(nullable = false)
    private String nome;
    
    @org.hibernate.annotations.NaturalId
    @Column(nullable = false,unique = true)
    private String login;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String senha;
    
    @Column(columnDefinition = "BOOLEAN", nullable = false)
    private boolean ativo;
    
    @ElementCollection(targetClass = String.class)
    @JoinTable(
       name = "usuario_permissao",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario","permissao"})},
       joinColumns = @JoinColumn(name = "usuario"))
    @Column(name = "permissao",length = 50)
    private Set<String> permissao = new HashSet<String>();

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Set<String> getPermissao() {
        return permissao;
    }

    public void setPermissao(Set<String> permissao) {
        this.permissao = permissao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        hash = 61 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 61 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 61 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 61 * hash + (this.senha != null ? this.senha.hashCode() : 0);
        hash = 61 * hash + (this.ativo ? 1 : 0);
        hash = 61 * hash + (this.permissao != null ? this.permissao.hashCode() : 0);
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
        final Usuario other = (Usuario) obj;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.senha == null) ? (other.senha != null) : !this.senha.equals(other.senha)) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        if (this.permissao != other.permissao && (this.permissao == null || !this.permissao.equals(other.permissao))) {
            return false;
        }
        return true;
    }
      
    
    
    
    
    }
   
    
    
    

   
    

    
    
    
    
    

