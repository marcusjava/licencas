/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.util;

/**
 *
 * @author Marcus
 */
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.hibernate.jdbc.Work;
public class GeraRelatorio implements Work{

    
    private boolean relatoriogerado;
    private String caminhorelatorio;
    private HttpServletResponse response;
    private Map<String, Object> parametros;
    private String nomeArquivoSaida;
    
    public GeraRelatorio(String caminhorelatorio,HttpServletResponse response,Map<String, Object> parametros,String nomeArquivoSaida)
    {
        this.caminhorelatorio = caminhorelatorio;
        this.response = response;
        this.parametros = parametros;
        this.nomeArquivoSaida = nomeArquivoSaida;
        
        this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt","BR"));
    }
    
    
    @Override
    public void execute(Connection cnctn) throws SQLException {
        try
        {
            InputStream relatorioStream = this.getClass().getResourceAsStream(caminhorelatorio);
            JasperPrint print = JasperFillManager.fillReport(relatorioStream, parametros, cnctn);
            this.relatoriogerado = print.getPages().size() > 0;
            
            if(this.relatoriogerado)
            {
                JRExporter exportador = new JRPdfExporter();
                exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "attachment;filename=\"" + this.nomeArquivoSaida + "\"");
                exportador.exportReport();
                
            }
        }catch(Exception e)
        {
            throw new SQLException("Erro ao executar relatorio" + this.caminhorelatorio,e);
        }
    }

    public boolean isRelatoriogerado() {
        return relatoriogerado;
    }

    
        
    
    
    
    }
    

