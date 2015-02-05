/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRRtfExporter;


/**
 *
 * @author Marcus
 */
@WebServlet(name = "RelatorioServlet", urlPatterns = {"/RelatorioServlet"})
public class RelatorioServlet extends HttpServlet {

    
    public static final int	RELATORIO_PDF=1;
    public static final int	RELATORIO_EXCEL=2;
    public static final int	RELATORIO_HTML= 3;
    public static final int	RELATORIO_PLANILHA_OPEN_OFFICE=4;
    private static final long serialVersionUID = 432309823829155729L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InputStream caminho = null;
        
        ServletOutputStream ouputStream = null;
        
        try{                                                              
   
             HttpSession session = request.getSession(true);                          

             //Recuperando os parâmetros enviados
             List listagem = (List)session.getAttribute("listagem");
             String relatorio = session.getAttribute("relatorio").toString();           
             int tipo = Integer.parseInt(session.getAttribute("tipo").toString());
             
             if (listagem == null)
                listagem = new ArrayList();
             
             caminho = RelatorioServlet.class.getResourceAsStream(relatorio);
             
             JRDataSource jrds = new JRBeanCollectionDataSource(listagem);  
             JasperPrint jasperPrint = JasperFillManager.fillReport(caminho, null, jrds);
             
             byte bytes[] = null;
             
             switch(tipo)
             {
                 
                 case RelatorioServlet.RELATORIO_PDF:
                    
                     response.setHeader("application/pdf", "Content-Type");  
                     response.setContentType("application/pdf");
                     bytes = JasperExportManager.exportReportToPdf(jasperPrint);                                                                     
                    
                 break;
                     
                 case RelatorioServlet.RELATORIO_EXCEL:
                     
                     ByteArrayOutputStream output = new ByteArrayOutputStream();                                    
                     
                     JRExporter exporter = null;  
                     exporter = new JRRtfExporter();                                        ;
                     exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8"); 
                     exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
                     exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                     
                     exporter.exportReport();                        
                     
                     bytes = output.toByteArray(); 
                      
                     String fileName = "relatorio.rtf";  
                     response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");                      
                     
                 break;    
                     
             }             

             response.setContentLength(bytes.length);                                
             ouputStream = response.getOutputStream();  
             ouputStream.write(bytes, 0, bytes.length);                            
             ouputStream.flush();
             
         }catch(Exception e){
             e.printStackTrace();         
         }finally {
            
            if (caminho != null)
                caminho.close();
            
            if (ouputStream != null)
                ouputStream.close();
            
         }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
