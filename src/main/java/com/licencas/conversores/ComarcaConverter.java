/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.conversores;

import com.licencas.model.dao.HibernateDAO;
import com.licencas.model.dao.InterfaceDAO;
import com.licencas.model.entities.Comarca;
import com.licencas.util.FacesContextUtil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Marcus
 */
@FacesConverter(value = "comarcaconverter")
public class ComarcaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Comarca comarca = new Comarca();
        InterfaceDAO<Comarca> comarcaDAO = new HibernateDAO<Comarca>(Comarca.class,FacesContextUtil.getRequestSession());
        try
        {
            comarca = comarcaDAO.getEntity(Integer.parseInt(value));
        }catch(Exception e)
        {
            e.getMessage();
        }
        return comarca;
    }
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        Comarca comarca = (Comarca) value;
        String codigo = comarca.getId()+"";
        return codigo;
    
    }
    }

    


    

