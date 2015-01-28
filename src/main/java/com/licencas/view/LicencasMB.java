
package com.licencas.view;

import com.licencas.controller.LicencasRN;
import com.licencas.controller.UsuarioRN;
import com.licencas.model.entities.Licencas;
import com.licencas.model.entities.Local;
import com.licencas.model.entities.Usuario;
import com.licencas.util.RelatorioUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Marcus
 */
@ManagedBean
@ViewScoped
public class LicencasMB implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Licencas licenca = new Licencas();
    private Licencas excluir = new Licencas();
    private List<Licencas> licencas;
    private List<Licencas> filtro_licencas;
    private List<Licencas> filtro_desativadas;
    private List<Licencas> desativadas;
    private List<Licencas> ativadas;
    private List<Licencas> filtro_ativadas;
    private Licencas selecionada;
    private String mensagem;
    //Informaçoes para o relatorio jasper
    private StreamedContent arquivoretorno;
    private int tiporelatorio;

    
    @PostConstruct
    public void novo()
    {
        LicencasRN licencarn = new LicencasRN();
        this.licenca = new Licencas();
        licencas = licencarn.todas();
        filtro_licencas = licencarn.todas();
        desativadas =  licencarn.listaDesativadas();
        filtro_desativadas = licencarn.listaDesativadas();
        ativadas = licencarn.listaAtivadas();
        filtro_ativadas = licencarn.listaAtivadas();
        selecionada = new Licencas();
        licenca.setLocal(new Local());
        
    }
    
    public void Salvar()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        LicencasRN licencarn = new LicencasRN();
        mensagem = licencarn.addLicencas(licenca);
        FacesMessage msg = new FacesMessage(mensagem);
        context.addMessage(null,msg );
        novo();
    }
    public void deletar()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        LicencasRN licencarn = new LicencasRN();
        mensagem = licencarn.deleteLicencas(this.licenca);
        FacesMessage msg = new FacesMessage(mensagem);
        context.addMessage(null,msg );
        novo();
    }
       
     public void Liberar()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        LicencasRN licencarn = new LicencasRN();
        mensagem = licencarn.Liberar(selecionada);
        FacesMessage msg = new FacesMessage(mensagem);
        context.addMessage(null,msg );
        novo();
    }
    //JasperReports

    public StreamedContent getArquivoretorno() {
        UsuarioRN usuariorn = new UsuarioRN();
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(mensagem);
        ExternalContext external = context.getExternalContext();
        String login = external.getRemoteUser();
        Usuario usuario = usuariorn.buscaporlogin(login);
        String nomeRelatorioJasper ="licencas";
        String nomeRelatorioSaida = usuario.getNome()+"_licencas";
        RelatorioUtil relatorio = new RelatorioUtil();
        HashMap parametros = new HashMap();
        parametros.put("codigousuario", usuario.getCodigo());
        try
        {
            this.arquivoretorno = relatorio.geraRelatorio(parametros, nomeRelatorioJasper, nomeRelatorioSaida, tiporelatorio);
        }catch(Exception e)
        {
            mensagem = e.getMessage();
        }
        context.addMessage(null,msg );
        return this.arquivoretorno;
    }

    public void setArquivoretorno(StreamedContent arquivoretorno) {
        this.arquivoretorno = arquivoretorno;
    }

    public int getTiporelatorio() {
        return tiporelatorio;
    }

    public void setTiporelatorio(int tiporelatorio) {
        this.tiporelatorio = tiporelatorio;
    }
     
     
    public Licencas getLicenca() {
        return licenca;
    }

    public void setLicenca(Licencas licenca) {
        this.licenca = licenca;
    }

    public List<Licencas> getLicencas() {
        return licencas;
    }

    public void setLicencas(List<Licencas> licencas) {
        this.licencas = licencas;
    }

    public List<Licencas> getFiltro_licencas() {
        return filtro_licencas;
    }

    public void setFiltro_licencas(List<Licencas> filtro_licencas) {
        this.filtro_licencas = filtro_licencas;
    }

    public Licencas getExcluir() {
        return excluir;
    }

    public void setExcluir(Licencas excluir) {
        this.excluir = excluir;
    }

    public Licencas getSelecionada() {
        return selecionada;
    }

    public void setSelecionada(Licencas selecionada) {
        this.selecionada = selecionada;
    }

    public List<Licencas> getFiltro_desativadas() {
        return filtro_desativadas;
    }

    public void setFiltro_desativadas(List<Licencas> filtro_desativadas) {
        this.filtro_desativadas = filtro_desativadas;
    }

    public List<Licencas> getAtivadas() {
        return ativadas;
    }

    public List<Licencas> getFiltro_ativadas() {
        return filtro_ativadas;
    }
    
    
   
    

    public List<Licencas> getDesativadas() {
        return desativadas;
    }

    public void setDesativadas(List<Licencas> desativadas) {
        this.desativadas = desativadas;
    }
    
    
   
    public void onRowEdit(RowEditEvent event) {
        LicencasRN licencarn = new LicencasRN();
        Licencas licenca_temp = (Licencas) event.getObject();
        licencarn.addLicencas(licenca_temp);
    }
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Licencas) event.getObject()).getLic_desc());
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
