/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.view;

import com.licencas.controller.ComarcaRN;
import com.licencas.controller.LicencasRN;
import com.licencas.controller.LocalRN;
import com.licencas.model.entities.Comarca;
import com.licencas.model.entities.Foro;
import com.licencas.model.entities.Local;
import com.licencas.model.entities.Licencas;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Marcus
 */
@ManagedBean
@ViewScoped
public class LocalMB implements Serializable{
    private static final long serialVersionUID = 3440836059037399266L;
    
   
    
    private Local local;
    private Licencas licenca = new Licencas();
    private Comarca comarca = new Comarca();
    private Foro foro = new Foro();
    private Local localselec;
    private List<Local> locais;
    private List<Local> locais_filtro;
    private List<Comarca> combocomarca;
    private List<Foro> comboforos;
    private List<Local> combolocais;
    private List<Licencas> desativadas;
    private String mensagem;
    
    
    
   
     @PostConstruct
     public void init()
     {
        local = new Local();
        local.setForo(new Foro());
        local.setLicenca(new Licencas());
     }

    public void novo()
    {
       local = new Local();
       licenca = new Licencas();
       comarca = new Comarca();
       foro = new Foro();
       local.setForo(new Foro());
       //local.setLicenca(new Licencas());
    }
    public String Salvar()
    {
        LocalRN localrn = new LocalRN();
        local.setLicenca(null);
        mensagem = localrn.addLocal(local);
        locais = null;
        novo();
        return null;
       
    }
    
    public String SalvarLicenca()
    {
        LocalRN localrn = new LocalRN();
        if (local.getLicenca() == null)
        {
            licenca.setStatus("ATIVADA");
            local.setLicenca(licenca);
            mensagem = localrn.addLocal(local);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,""));
        }
        else
        {
            if("ATIVADA".equals(licenca.getStatus()))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "A Licença já está ativada",""));
            }
            if(local.getLicenca() != null)
            {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "O local já possui licença favor liberar",""));  
            }
        }
        novo();
        return null;
    }
    
    public String Deletar()
    {
        LocalRN localrn = new LocalRN();
        mensagem = localrn.deleteLocal(localselec);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,""));
        novo();
        return null;
    }

       
    public void selecionaForo()
    {
        LocalRN localrn = new LocalRN();
        if (comarca != null)
        {
            comboforos = localrn.buscaporforo(comarca.getId());
            
        }
        
        
    }
    public void selecionaLocal()
    {
        LocalRN localrn = new LocalRN();
        combolocais = localrn.getListForoLocal(foro.getId());
                  
      
    }

    public Licencas getLicenca() {
        return licenca;
    }

    public void setLicenca(Licencas licenca) {
        this.licenca = licenca;
    }
    
    public Foro getForo() {
        return foro;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }


    //Lista de todas licencas desativadas
    public List<Licencas> getDesativadas() {
        LicencasRN licencarn = new LicencasRN();
        desativadas = licencarn.listaDesativadas();
        return desativadas;
    }

    public void setDesativadas(List<Licencas> desativadas) {
        this.desativadas = desativadas;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
    
   

    public Comarca getComarca() {
        return comarca;
    }

    public void setComarca(Comarca comarca) {
        this.comarca = comarca;
    }

    public List<Local> getLocais() {
        LocalRN localrn = new LocalRN();
        locais = localrn.todas();
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }

    public List<Local> getLocais_filtro() {
        return locais_filtro;
    }

    public void setLocais_filtro(List<Local> locais_filtro) {
        this.locais_filtro = locais_filtro;
    }

    public List<Comarca> getCombocomarca() {
        ComarcaRN comarcarn = new ComarcaRN();
        combocomarca = comarcarn.todas();
        return combocomarca;
    }

    public void setCombocomarca(List<Comarca> combocomarca) {
        this.combocomarca = combocomarca;
    }

    public List<Foro> getComboforos() {
        return comboforos;
    }

    public void setComboforos(List<Foro> comboforos) {
        this.comboforos = comboforos;
    }

    public List<Local> getCombolocais() {
        return combolocais;
    }

    public void setCombolocais(List<Local> combolocais) {
        this.combolocais = combolocais;
    }

    public Local getLocalselec() {
        return localselec;
    }

    public void setLocalselec(Local localselec) {
        this.localselec = localselec;
    }
    
    
    public void onRowEdit(RowEditEvent event) {
        LocalRN localrn = new LocalRN();
        Local editada = (Local)event.getObject();
        localrn.addLocal(editada);
        
    }
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Local) event.getObject()).getLoc_desc());
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
