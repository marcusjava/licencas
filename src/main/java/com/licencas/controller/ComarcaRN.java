/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.controller;

import com.licencas.model.dao.HibernateDAO;
import com.licencas.model.dao.InterfaceDAO;
import com.licencas.model.entities.Comarca;
import com.licencas.util.*;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcus
 */
public class ComarcaRN {
   
   private List<Comarca> comarcas;
   
   private InterfaceDAO<Comarca> comarcaDAO()
   {
      InterfaceDAO<Comarca> comarcaDAO = new HibernateDAO<Comarca>(Comarca.class, FacesContextUtil.getRequestSession());
      return comarcaDAO;
   }
   public String Salvar(Comarca comarca)
   {
       
       
         if (comarca.getId() == null || comarca.getId() == 0 )
            {
                //pesquisar se campo unico descrição ja existe
                 if(!pesqcampounico(comarca))
                {
                    try
                    {
                       comarcaDAO().save(comarca);
                       return "Comarca salva com sucesso!";
                    }catch(HibernateException e)
                    {
                        return "Ocorreu um erro:" + e.getMessage();
                    }
                    catch(Exception e )
                    {
                        return "Ocorreu um erro:" + e.getMessage();
                    }
                }else
                    {
                        return "Esta comarca já está cadastrada!";
                    }
            }
            else
            {
                try
                {
                     comarcaDAO().update(comarca);
                     return "Comarca atualizada com sucesso!";
                }catch(HibernateException e)
                {
                    return "Ocorreu um erro:" + e.getMessage();
                }
            }
       
       
   }
   private boolean pesqcampounico(Comarca comarca)
   {
       String hql = "Select c from Comarca c where c.com_desc = :desc";
       Comarca campounico = comarcaDAO().getCampoUnico(hql, comarca.getCom_desc());
       return campounico != null;
   }
   public String deleteComarca(Comarca comarca)
   {
       try
       {
            comarcaDAO().remove(comarca);
            return "Comarca excluida com sucesso";
       }catch(HibernateException e)
       {
           return "Ocorreu um erro:" + e.getMessage();
       }
   }
   public List<Comarca> todas()
   {
       return comarcaDAO().getEntities();
   }
           
   
}

