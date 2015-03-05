/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.view;

import com.licencas.controller.EquipamentoRN;
import com.licencas.model.entities.Equipamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Marcus
 */
@ManagedBean
@RequestScoped
public class EquipamentoMB implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Equipamento equipamento = new Equipamento();
    private List<Equipamento> equipamentos;
    private List<Equipamento> filtro_equipamentos;
    private String mensagem;
    
    
    @PostConstruct
    public void init()
    {
        EquipamentoRN equipamentorn = new EquipamentoRN();
        equipamentos = equipamentorn.todos();
        filtro_equipamentos = equipamentorn.todos();
    }
    
    //AutoComplete
     public List<Equipamento> complete(String name)
     {
         List<Equipamento> resultado = new ArrayList<Equipamento>();
         if(equipamentos == null)
         {
             EquipamentoRN equipamentorn = new EquipamentoRN();
             equipamentos = equipamentorn.todos();
         }
         for(Equipamento equipselecionado : equipamentos)
         {
             if(equipselecionado.getDescricao().toLowerCase().contains(name.toLowerCase()))
             {
                 resultado.add(equipselecionado);
             }
             
         }
         return resultado;
     }
    public void novo()
    {
        equipamento = new Equipamento();
        EquipamentoRN equipamentorn = new EquipamentoRN();
        equipamentos = equipamentorn.todos();
        filtro_equipamentos = equipamentorn.todos();
    }
    
    public String Salvar()
    {
        EquipamentoRN equipamentorn = new EquipamentoRN();
        mensagem = equipamentorn.Salvar(equipamento);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,""));
        novo();
        return null;
    }
    
    public String Deletar()
    {
        EquipamentoRN equipamentorn = new EquipamentoRN();
        mensagem = equipamentorn.Deletar(equipamento);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,""));
        novo();
        return null;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public List<Equipamento> getFiltro_equipamentos() {
        return filtro_equipamentos;
    }

    public void setFiltro_equipamentos(List<Equipamento> filtro_equipamentos) {
        this.filtro_equipamentos = filtro_equipamentos;
    }
    public void onRowEdit(RowEditEvent event) {
        EquipamentoRN equipamentorn = new EquipamentoRN();
        Equipamento equipamento_temp = (Equipamento) event.getObject();
        equipamentorn.Salvar(equipamento_temp);
    }
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Equipamento) event.getObject()).getDescricao());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Valor atualizado", "Antigo: " + oldValue + ", Novo:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
}
