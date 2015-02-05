/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.controller;

import com.licencas.model.dao.HibernateDAO;
import com.licencas.model.dao.InterfaceDAO;
import com.licencas.model.entities.Foro;
import com.licencas.util.FacesContextUtil;
import java.util.List;
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
           if(!pesqcampounico(foro))
            {
                try
                {
                     foroDAO().save(foro);
                     return "Foro salvo com sucesso!";
                }catch(HibernateException e)
                {
                    return "Ocorreu um erro" + e.getMessage();
                }
            }else
            {
                return "Este foro já está cadastrado!";
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
    private boolean pesqcampounico(Foro foro)
   {
       String hql = "Select f from Foro f where f.for_desc = :desc";
       Foro campounico = foroDAO().getCampoUnico(hql, foro.getFor_desc());
       return campounico != null;
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
