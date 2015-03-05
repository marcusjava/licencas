/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.controller;

import com.licencas.model.dao.HibernateDAO;
import com.licencas.model.dao.InterfaceDAO;
import com.licencas.model.entities.Usuario;
import com.licencas.util.FacesContextUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcus
 */
public class UsuarioRN {
    
    private InterfaceDAO<Usuario> usuarioDAO()
    {
      InterfaceDAO<Usuario> usuarioDAO = new HibernateDAO<Usuario>(Usuario.class,FacesContextUtil.getRequestSession());
      return usuarioDAO;
    }
    
    public String Salvar(Usuario usuario,Set<String> papeis) throws HibernateException
    {
        usuario.setPermissao(new HashSet(papeis));
        
        if (usuario.getCodigo() == null || usuario.getCodigo()==0)
        {
            try{
                usuario.setAtivo(true);
                usuarioDAO().save(usuario);
                return "Usuario salvo com sucesso";
            }catch(HibernateException e)
            {
                return "Ocorreu um erro"+ e.getMessage();
            }
            
        }
        else
        {
            try
            {
                usuarioDAO().update(usuario);
                return "Usuario atualizado com sucesso";
            }catch(Exception e)
            {
                return "Ocorreu erro ao atualizar" + e.getMessage();
            }
        }
    }
    
    public String Excluir(Usuario usuario)
    {
        try
        {
        usuarioDAO().remove(usuario);
        return "Usuario excluido com sucesso!";
        }catch(Exception e )
        {
            return "Ocorreu erro ao excluir" + e.getMessage();
        }
    }
    
    public List<Usuario> todos()
    {
        return usuarioDAO().getEntities();
    }
    
    public void Atualizar(Usuario usuario)
    {
        usuarioDAO().update(usuario);
    }
    public Usuario buscaporlogin(String login)
    {
        return usuarioDAO().buscaporlogin(login);
    }
    
}
