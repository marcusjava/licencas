/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.controller;

import com.licencas.model.dao.HibernateDAO;
import com.licencas.model.dao.InterfaceDAO;
import com.licencas.model.entities.Foro;
import com.licencas.model.entities.Local;
import com.licencas.util.FacesContextUtil;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcus
 */
public class LocalRN {
    
    
    
    private List<Local> locais;
    
    private InterfaceDAO<Local> LocalDAO()
    {
        InterfaceDAO<Local> localDAO = new HibernateDAO<Local>(Local.class,FacesContextUtil.getRequestSession());
        return localDAO;
    }
    
    
    public void LiberarLicenca(Local local)
    {
        
    }
            
            
    public String addLocal(Local local)
   {
       if (local.getId() == null || local.getId() == 0 )
       {
           if(!pesqcampounico(local))
           {
                try
                {
                   LocalDAO().save(local);
                   return "Local salvo com sucesso!";
                }catch(Exception e)
                {
                    return "Ocorreu um erro:" + e.getMessage();
                }
                
           }
           else
           {
               return "Local já cadastrado!";
           }
       }
       else
       {
           try
           {
                LocalDAO().merge(local);
                return "Local atualizado com sucesso!";
           }catch(Exception e)
           {
               return "Ocorreu um erro:" + e.getMessage();
           }
           
       }
   }
    private boolean pesqcampounico(Local local)
   {
       String hql = "Select l from Local l where l.loc_desc = :desc";
       Local campounico = LocalDAO().getCampoUnico(hql, local.getLoc_desc());
       return campounico != null;
   }
  
   public String deleteLocal(Local local)
   {
       try
       {
            LocalDAO().remove(local);
            return "Local excluido com sucesso!";
       }catch(HibernateException e)
       {
           return "Ocorreu um erro:"+ e.getMessage();
       }
   }
   public List<Local> todas()
   {
       return LocalDAO().getEntities();
   }
   public List<Foro> buscaporforo(Integer parametro)
   {
       String query = "FROM Foro F WHERE F.com_id = :id";
       return LocalDAO().getListComarcaForo(parametro);
    }
   
   public List<Local> getListForoLocal(Integer parametro)
   {
       return LocalDAO().getListForoLocal(parametro);
   }
    
}
