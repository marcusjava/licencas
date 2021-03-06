/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.view;


import com.licencas.controller.ForoRN;
import com.licencas.model.entities.Comarca;
import com.licencas.model.entities.Foro;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Marcus
 */
@ManagedBean
@RequestScoped
public class ForoMB implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    
    private Foro foro;
    private List<Foro> foros;
    private List<Foro> filtro_lista;
    private Comarca comarcaselecionada;
    private ForoRN fororn;
    private String mensagem;
    private Comarca comarca;
    
    
    @PostConstruct
    public void novo()
    {
        fororn = new ForoRN();
        foros = fororn.todas();
        filtro_lista = fororn.todas();
        foro = new Foro();
        //foro.setComarca(new Comarca());
        
        
    }
    
    public String Salvar()
    {
        mensagem = fororn.addForo(foro);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,""));
        foros.clear();
        filtro_lista.clear();
        novo();
        this.refresh();
        return null;
        
    }
    
    public void deletar()
    {
       mensagem = fororn.deleteForo(foro);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,""));
       novo();
    }
    
    public void Editar()
    {
        mensagem = fororn.addForo(foro);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,""));
        novo();
    }
    
    public Foro getForo() {
        return foro;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }

    public Comarca getComarcaselecionada() {
        return comarcaselecionada;
    }

    public void setComarcaselecionada(Comarca comarcaselecionada) {
        this.comarcaselecionada = comarcaselecionada;
    }
    
    
    public List<Foro> getForos() {
        foros = fororn.todas();
        return foros;
    }

    public void setForos(List<Foro> foros) {
        this.foros = foros;
    }

    public List<Foro> getFiltro_lista() {
        filtro_lista = fororn.todas();
        return filtro_lista;
    }

    public Comarca getComarca() {
        return comarca;
    }

    public void setComarca(Comarca comarca) {
        this.comarca = comarca;
    }
    
    

    public void setFiltro_lista(List<Foro> filtro_lista) {
        this.filtro_lista = filtro_lista;
    }
    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();

        System.out.println("ATUALIZANDO");
    }
    public void onRowEdit(RowEditEvent event) {
        Foro forotemp = (Foro) event.getObject();
        fororn.addForo(forotemp);
        
    }
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Foro) event.getObject()).getFor_desc());
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
