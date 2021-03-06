/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.controller;

import com.licencas.model.dao.HibernateDAO;
import com.licencas.model.dao.InterfaceDAO;
import com.licencas.model.entities.Licencas;
import com.licencas.model.entities.Local;
import com.licencas.util.FacesContextUtil;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Marcus
 */
public class LicencasRN {
     
    
    private List<Licencas> licencas;
     
     
     
     private InterfaceDAO<Licencas> licencaDAO()
     {
         InterfaceDAO<Licencas> licencaDAO = new HibernateDAO<Licencas>(Licencas.class,FacesContextUtil.getRequestSession());
         return licencaDAO;
     }
     public String addLicencas(Licencas licenca)
   {
       
            if (licenca.getId() == null || licenca.getId() == 0 )
            {
                //Pesquisa para verificar os campos unicos
                if(!pesqcampounico(licenca))
                {
                    try
                    {
                         licenca.setBem(null);
                         licenca.setStatus("DESATIVADA");
                         licencaDAO().save(licenca);
                         return "Licenca salva com sucesso!";
                    }catch(Exception e)
                    {
                        return "Ocorreu um erro: " + e.getMessage();
                    }
                }else
                   {
                       return "Esta licenca já está cadastrada!";
                   }
                }
            else
            {
               try
               {

                licencaDAO().merge(licenca);
                return "Licenca atualizada com sucesso!";
               }catch(Exception e)
               {
                   return "Ocorreu um erro:" + e.getMessage();
               }

             }
       
     
   }
     //VERIFICA SE A LICENÃ‡A JA EXISTE
   private boolean pesqcampounico(Licencas licenca)
   {
       String hql = "Select l from Licencas l where l.lic_desc = :desc";
       Licencas campounico = licencaDAO().getCampoUnico(hql, licenca.getLic_desc());
       return campounico != null;
   }
   
   
   
   public String deleteLicencas(Licencas licenca)
   {
       try
       {
            licencaDAO().remove(licenca);
            return "Licença excluida com sucesso!";
       }catch(HibernateException e)
       {
           return "Ocorreu um erro:" + e.getMessage();
       }
   }
   public List<Licencas> todas()
   {
       return licencaDAO().getEntities();
   }
   public List<Licencas> listaDesativadas()
   {
       String query  = ("from Licencas as lic where lic.status = 'DESATIVADA'");
       return licencaDAO().getListByQuery(query);
   }
   
   public List<Licencas> listaAtivadas()
   {
       String query = ("from Licencas as lic where lic.status = 'ATIVADA'");
       return licencaDAO().getListByQuery(query);
   }
   public String Liberar(Licencas selecionada)
   {
       if (selecionada.getStatus().equals("ATIVADA"))
        {
            try
            {
                selecionada.setStatus("DESATIVADA");
                selecionada.setBem(null);
                licencaDAO().merge(selecionada);
                return "Licenca liberada com sucesso";
            }catch(Exception e)
            {
                return "Ocorreu um erro:" + e.getMessage();
            }
        }
       return "ERRO Licenca ja se encontra desativada!";
   }
           
   
}
