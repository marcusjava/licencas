/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.conversores;


import com.licencas.model.dao.HibernateDAO;
import com.licencas.model.dao.InterfaceDAO;
import com.licencas.model.entities.Foro;
import com.licencas.util.FacesContextUtil;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import java.lang.annotation.Annotation;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Marcus
 */
@FacesConverter(value = "foroconverter")
public class ForoConverter implements Converter{
    private InterfaceDAO<Foro> foroDAO()
        {
            InterfaceDAO<Foro> foroDAO = new HibernateDAO<Foro>(Foro.class,FacesContextUtil.getRequestSession());
            return foroDAO;
        }
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if (value != null && value.isEmpty()) 
         {
            
            try 
             {
               
                return (Foro) component.getAttributes().get(value);
                 
             }// Fim do try
            catch (Exception e) 
             {
                throw new ConverterException("Erro ao realizar a conversão " + value + "." + e.getMessage());
             }// Fim do catch
         }
         return null;
    }
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Foro)
         {
            //Aqui voce vai passar o identificador da sua classe
            Foro foro = (Foro) value;
            if (foro instanceof Foro && foro.getId() != null) {
                component.getAttributes().put( foro.getId().toString(), foro);
                return foro.getId().toString();
            }
        
        }
        return "";
    
    }

   
   
}
