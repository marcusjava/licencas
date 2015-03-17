/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.view;

import com.licencas.controller.BemRN;
import com.licencas.controller.UsuarioRN;
import com.licencas.model.entities.Bem;
import com.licencas.model.entities.Licencas;
import com.licencas.model.entities.Local;
import com.licencas.model.entities.Usuario;
import com.licencas.util.RelatorioUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Marcus
 */
@ManagedBean
@ViewScoped
public class BemMB implements Serializable{
    private static final long serialVersionUID = 1L;
    private Bem bem;
    private List<Bem> bens;
    private List<Bem> filtro_bens;
    private List<Bem> pesq_pat;
    private List<Bem> pesq_local;
    private List<Bem> pesq_comp;
    private List<Bem> filtro_pesq_comp;
    private String mensagem;
    private Local novo_local;
    private String patrimonio;
    private Local local;
    private Licencas licenca;
    
    //InformaÃ§oes para o relatorio jasper
    private StreamedContent arquivoretorno;
    private int tiporelatorio;
    private String nomerelatorio;
    private HttpServletResponse response;
    
    @PostConstruct
    public void init()
    {
        BemRN bemrn = new BemRN();
        bens = bemrn.todos();
        filtro_bens = bemrn.todos();
        licenca = new Licencas();
        bem = new Bem();
    }
    
    
    public String Salvar()
    {
        BemRN bemrn = new BemRN();
        mensagem = bemrn.Salvar(bem);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,""));
        init();
        this.refresh();
        return null;
    }
    //metodo para buscar patrimonio
    public void BuscarPatrimonio()
    {
        BemRN bemrn = new BemRN();
        bem = bemrn.BuscarPatrimonio(patrimonio);
        pesq_pat = bemrn.ListaPatrimonio(patrimonio);
        if(bem == null || pesq_pat == null)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Patrimonio nÃ£o cadastrado!",""));
            init();
        }
    }
    
    //JasperReports

    public StreamedContent getArquivoretorno() {
        UsuarioRN usuariorn = new UsuarioRN();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(mensagem);
        ExternalContext external = context.getExternalContext();
        String login = external.getRemoteUser();
        Usuario usuario = usuariorn.buscaporlogin(login);
        String nomeRelatorioJasper = nomerelatorio;
        String nomeRelatorioSaida = usuario.getLogin()+"_bens";
        RelatorioUtil relatorio = new RelatorioUtil();
        HashMap parametros = new HashMap();
        parametros.put("codigousuario", usuario.getCodigo());
        try
        {
             this.arquivoretorno = relatorio.geraRelatorio(parametros, nomeRelatorioJasper, nomeRelatorioSaida, tiporelatorio,bens);
           
        }catch(Exception e)
        {
            mensagem = e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,""));
            return null;
        }
        return this.arquivoretorno;
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
    //metodo que busca bens por local
    public void BuscarLocal()
    {
      BemRN bemrn = new BemRN();
      pesq_local = bemrn.ListaBensLocal(local);
    }
    //metodo para transferir patrimonio para outro local
    public String Transferir()
    {
        BemRN bemrn = new BemRN();
        bem.setLocal(novo_local);
        mensagem = bemrn.Salvar(bem);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,""));
        init();
        return null;
    }
    public String Deletar()
    {
        BemRN bemrn = new BemRN();
        mensagem = bemrn.Deletar(bem);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,""));
        init();
        return null;
    }

    public Bem getBem() {
        return bem;
    }

    public void setBem(Bem bem) {
        this.bem = bem;
    }

    public List<Bem> getBens() {
        return bens;
    }

    public void setBens(List<Bem> bens) {
        this.bens = bens;
    }

    public List<Bem> getFiltro_bens() {
        return filtro_bens;
    }

    public void setFiltro_bens(List<Bem> filtro_bens) {
        this.filtro_bens = filtro_bens;
    }

    public Local getNovo_local() {
        return novo_local;
    }

    public void setNovo_local(Local novo_local) {
        this.novo_local = novo_local;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public List<Bem> getPesq_pat() {
        return pesq_pat;
    }

    public void setPesq_pat(List<Bem> pesq_pat) {
        this.pesq_pat = pesq_pat;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    

    public List<Bem> getPesq_local() {
        return pesq_local;
    }

    public void setPesq_local(List<Bem> pesq_local) {
        this.pesq_local = pesq_local;
    }

    public int getTiporelatorio() {
        return tiporelatorio;
    }

    public void setTiporelatorio(int tiporelatorio) {
        this.tiporelatorio = tiporelatorio;
    }

    public String getNomerelatorio() {
        return nomerelatorio;
    }

    public void setNomerelatorio(String nomerelatorio) {
        this.nomerelatorio = nomerelatorio;
    }

    public Licencas getLicenca() {
        return licenca;
    }

    public void setLicenca(Licencas licenca) {
        this.licenca = licenca;
    }

    public List<Bem> getPesq_comp() {
       BemRN bemrn = new BemRN();
        return pesq_comp = bemrn.pesqcomputador();
    }

    public void setPesq_comp(List<Bem> pesq_comp) {
        this.pesq_comp = pesq_comp;
    }

    public List<Bem> getFiltro_pesq_comp() {
        BemRN bemrn = new BemRN();
        return filtro_pesq_comp = bemrn.pesqcomputador();
    }

    public void setFiltro_pesq_comp(List<Bem> filtro_pesq_comp) {
        this.filtro_pesq_comp = filtro_pesq_comp;
    }
     
    
    
     public void onRowEdit(RowEditEvent event) {
        BemRN bemrn = new BemRN();
        Bem bem_temp = (Bem) event.getObject();
        bemrn.Salvar(bem);
    }
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Bem) event.getObject()).getEquipamento().getDescricao());
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
