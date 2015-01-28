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
   private String mensagem;
   
   private InterfaceDAO<Comarca> comarcaDAO()
   {
      InterfaceDAO<Comarca> comarcaDAO = new HibernateDAO<Comarca>(Comarca.class, FacesContextUtil.getRequestSession());
      return comarcaDAO;
   }
   public String Salvar(Comarca comarca)
   {
       if (comarca.getId() == null || comarca.getId() == 0 )
       {
           try
           {
              comarcaDAO().save(comarca);
              mensagem = "Usuario salvo com sucesso!";
           }catch(HibernateException e)
           {
               mensagem = "Ocorreu um erro:" + e.getMessage();
           }
           catch(Exception e )
           {
               mensagem = "Ocorreu um erro:" + e.getMessage();
           }
       }
       else
       {
           try
           {
                comarcaDAO().update(comarca);
                mensagem = "Usuario atualizado com sucesso!";
           }catch(HibernateException e)
           {
               mensagem = "Ocorreu um erro:" + e.getMessage();
           }
       }
       return mensagem;
   }
      
   public String deleteComarca(Comarca comarca)
   {
       try
       {
            comarcaDAO().remove(comarca);
            mensagem = "Comarca excluida com sucesso";
       }catch(HibernateException e)
       {
           mensagem = "Ocorreu um erro:" + e.getMessage();
       }
       return mensagem;
   }
   public List<Comarca> todas()
   {
       return comarcaDAO().getEntities();
   }
           
   
}

