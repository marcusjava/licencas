/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.view;

import com.licencas.controller.UsuarioRN;
import com.licencas.model.entities.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.util.DigestUtils;

/**
 *
 * @author Marcus
 */
@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios;
    private List<Usuario> filtro_usuarios;
    private String confirmasenha;
    private String senha;
    private Set<String> papeis;
    private String senhacript;
    private String mensagem;

    
    @PostConstruct
    public void init()
    {
        UsuarioRN usuariorn = new UsuarioRN();
        this.usuarios = usuariorn.todos();
        this.filtro_usuarios = usuariorn.todos();
    }
    
    public void novo()
    {
        UsuarioRN usuariorn = new UsuarioRN();
        usuario = new Usuario();
        this.usuarios = usuariorn.todos();
        this.filtro_usuarios = usuariorn.todos();
        
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public String getConfirmasenha() {
        return confirmasenha;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setConfirmasenha(String confirmasenha) {
        this.confirmasenha = confirmasenha;
    }

    public String getSenhacript() {
        return senhacript;
    }

    public void setSenhacript(String senhacript) {
        this.senhacript = senhacript;
    }

    

    public Set<String> getPapeis() {
        return papeis;
    }

    public void setPapeis(Set<String> papeis) {
        this.papeis = papeis;
    }

    public List<Usuario> getFiltro_usuarios() {
        return filtro_usuarios;
    }

    public void setFiltro_usuarios(List<Usuario> filtro_usuarios) {
        this.filtro_usuarios = filtro_usuarios;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    public String Salvar()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        UsuarioRN usuariorn = new UsuarioRN();
                if(senha.equals(confirmasenha))
                {
                    if(!senha.isEmpty())
                    {
                        senhacript = DigestUtils.md5DigestAsHex(senha.getBytes());
                        this.usuario.setSenha(senhacript);
                    }
                    mensagem = usuariorn.Salvar(usuario, papeis);
                    FacesMessage msg = new FacesMessage(mensagem);
                    context.addMessage(null,msg );
                    novo();
                    return null;
                    

                }
                else
                {
                    if(papeis.isEmpty())
                    {

                        FacesMessage msg = new FacesMessage("Marque pelo menos uma permissão!");
                        context.addMessage(null, msg);
                    }
                    if(!usuario.getSenha().equals(confirmasenha))
                    {
                        FacesMessage msg = new FacesMessage("Senha não confere");
                        context.addMessage(null, msg);
                    }
                    
                }
        
        return null;
    }
     public String Excluir()
     {
         UsuarioRN usuariorn = new UsuarioRN();
         mensagem = usuariorn.Excluir(usuario);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,""));         
         novo();
         return null;
     }
    
     public String ativar()
     {
         UsuarioRN usuariorn = new UsuarioRN();
         if(usuario.isAtivo())
             this.usuario.setAtivo(false);
         else
             this.usuario.setAtivo(true);
         usuariorn.Atualizar(usuario);
         novo();
         return null;
     }
    
    
    
    
    
}
