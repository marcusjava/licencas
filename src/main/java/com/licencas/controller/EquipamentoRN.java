/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.controller;

import com.licencas.model.dao.HibernateDAO;
import com.licencas.model.dao.InterfaceDAO;
import com.licencas.model.entities.Equipamento;
import com.licencas.util.FacesContextUtil;
import java.util.List;

/**
 *
 * @author Marcus
 */
public class EquipamentoRN {
    
    
    private InterfaceDAO<Equipamento> equipamentoDAO()
    {
        InterfaceDAO<Equipamento> equipamentoDAO = new HibernateDAO<Equipamento>(Equipamento.class,FacesContextUtil.getRequestSession());
        return equipamentoDAO;
    }
    
    public String Salvar(Equipamento equipamento)
    {
       
            
            if(equipamento.getId() == null || equipamento.getId() == 0)
            {
                 if(!pesqcampounico(equipamento))
                {
                        try
                        {
                            equipamentoDAO().save(equipamento);
                            return "Equipamento salvo com sucesso";
                        }catch(Exception e)
                        {
                            return "Ocorreu um erro" + e.getMessage();
                        }
                }
                 else
                {
                    return "Este equipamento ja esta cadastrado";
                }
            }else
            {
                try
                {
                    equipamentoDAO().update(equipamento);
                    return "Equipamento atualizado com sucesso";
                }catch(Exception e)
                {
                    return "Ocorreu um erro" + e.getMessage();
                }
        }
        
    }
    
    
    private Boolean pesqcampounico(Equipamento equipamento)
    {
        String hql = "Select e from Equipamento e where e.descricao = :desc";
        Equipamento unico = equipamentoDAO().getCampoUnico(hql, equipamento.getDescricao());
        return unico!=null;
    }
    public String Deletar(Equipamento equipamento)
    {
        try
        {
            equipamentoDAO().remove(equipamento);
            return "Equipamento excluido com sucesso!";
        }catch(Exception e)
        {
            return "Ocorreu um erro" + e.getMessage();
        }
    }
    
    public List<Equipamento> todos()
    {
        return equipamentoDAO().getEntities();
    }
    
    
}
