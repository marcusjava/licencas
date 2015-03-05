/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.view;

import com.licencas.controller.ComarcaRN;
import com.licencas.model.entities.Comarca;
import java.io.Serializable;
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
public class ComarcaMB implements Serializable{
    
    
    private static final long serialVersionUID = 1L;
    
    private Comarca comarca = new Comarca();
    private List<Comarca> lista;
    private List<Comarca> filtro_lista;
    private String mensagem;
   

    public Comarca getComarca() {
        return comarca;
    }

    public void setComarca(Comarca comarca) {
        this.comarca = comarca;
    }

    public List<Comarca> getLista() {
        ComarcaRN comarcarn = new ComarcaRN();
        lista = comarcarn.todas();
        return lista;
    }

    public void setLista(List<Comarca> lista) {
        this.lista = lista;
    }

    public List<Comarca> getFiltro_lista() {
        return filtro_lista;
    }

    public void setFiltro_lista(List<Comarca> filtro_lista) {
        this.filtro_lista = filtro_lista;
    }
    
    
    public void Salvar()
    {   
        FacesContext context = FacesContext.getCurrentInstance();
        ComarcaRN comarcarn = new ComarcaRN();
        mensagem = comarcarn.Salvar(comarca);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,""));
        novo();
    }
    public void deletar()
    {
        ComarcaRN comarcarn = new ComarcaRN();
       mensagem = comarcarn.deleteComarca(this.comarca);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,""));
       novo();
    }
    @PostConstruct
    public void novo()
    {
        ComarcaRN comarcarn = new ComarcaRN();
        filtro_lista = comarcarn.todas();
        this.comarca = new Comarca();
    }
    public void onRowEdit(RowEditEvent event) {
        ComarcaRN comarcarn = new ComarcaRN();
        Comarca editada = (Comarca)event.getObject();
        comarcarn.Salvar(editada);
        
    }
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Comarca) event.getObject()).getCom_desc());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    
}
