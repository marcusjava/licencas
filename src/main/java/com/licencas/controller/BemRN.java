/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.controller;

import com.licencas.model.dao.HibernateDAO;
import com.licencas.model.dao.InterfaceDAO;
import com.licencas.model.entities.Bem;
import com.licencas.model.entities.Local;
import com.licencas.util.FacesContextUtil;
import java.util.List;

/**
 *
 * @author Marcus
 */
public class BemRN {
    
    
    private InterfaceDAO<Bem> bemDAO()
    {
        InterfaceDAO<Bem> bemDAO = new HibernateDAO<Bem>(Bem.class,FacesContextUtil.getRequestSession());
        return bemDAO;
    }
    public String Salvar(Bem bem)
    {
       
            
            if(bem.getId() == null || bem.getId() == 0)
            {
                 if(!pesqcampounico(bem))
                {
                        try
                        {
                            bemDAO().save(bem);
                            return "Bem salvo com sucesso";
                        }catch(Exception e)
                        {
                            return "Ocorreu um erro" + e.getMessage();
                        }
                }
                 else
                {
                    return "Este bem ja esta cadastrado";
                }
            }else
            {
                try
                {
                    bemDAO().merge(bem);
                    return "Bem atualizado com sucesso";
                }catch(Exception e)
                {
                    return "Ocorreu um erro" + e.getMessage();
                }
        }
        
    }
    
    
    private Boolean pesqcampounico(Bem bem)
    {
        String hql = "Select b from Bem b where b.patrimonio = :desc";
        Bem unico = bemDAO().getCampoUnico(hql, bem.getPatrimonio());
        return unico!=null;
    }
    
    public List<Bem> pesqcomputador()
    {
        String hql = ("Select b from Bem b where b.computador != ''");
        return bemDAO().getListByQuery(hql);
    }
    public String Deletar(Bem bem)
    {
        try
        {
            bemDAO().remove(bem);
            return "Bem excluido com sucesso!";
        }catch(Exception e)
        {
            return "Ocorreu um erro" + e.getMessage();
        }
    }
    
    public List<Bem> todos()
    {
        return bemDAO().getEntities();
    }
    
    public Bem BuscarPatrimonio(String patrimonio)
    {
        String hql = "Select b from Bem b where b.patrimonio = :desc";
        return bemDAO().getCampoUnico(hql, patrimonio);
    }
    public List<Bem> ListaPatrimonio(String patrimonio)
    {
        String hql = "Select b from Bem b where b.patrimonio = :patrimonio";
        return bemDAO().getLisByPatrimonio(hql, patrimonio);
    }
    public List<Bem> ListaBensLocal(Local local)
    {
        String hql = "Select b from Bem where b.local.loc_desc = :local";
        return bemDAO().getListByLocal(hql, local);
    }
    
}
