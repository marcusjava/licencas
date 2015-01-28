/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.controller;

import com.licencas.model.dao.HibernateDAO;
import com.licencas.model.dao.InterfaceDAO;
import com.licencas.model.entities.Comarca;
import com.licencas.model.entities.Foro;
import com.licencas.util.FacesContextUtil;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcus
 */
public class ForoRN {
    
    private List<Foro> foros;
    private String mensagem;
    
    private InterfaceDAO<Foro> foroDAO()
    {
        InterfaceDAO<Foro> foroDAO = new HibernateDAO<Foro>(Foro.class,FacesContextUtil.getRequestSession());
        return foroDAO;
    }
    public String addForo(Foro foro)
   {
       if (foro.getId() == null || foro.getId() == 0)
       {
           try
           {
                foroDAO().save(foro);
                return "Foro salvo com sucesso!";
           }catch(HibernateException e)
           {
               return "Ocorreu um erro" + e.getMessage();
           }
       }
       else
       {
           try
           {
                foroDAO().update(foro);
                return "Foro atualizado com sucesso";
           }catch(HibernateException e)
           {
               return "Ocorreu um erro" + e.getMessage();
           }
       }
       
   }
  
   public String deleteForo(Foro foro)
   {
       try
       {
            foroDAO().remove(foro);
            return "Foro excluido com sucesso!";
       }catch(HibernateException e)
       {
           return "Ocorreu um erro" + e.getMessage();
       }
       
       
   }
   public List<Foro> todas()
   {
       return foroDAO().getEntities();
   }
    
}
