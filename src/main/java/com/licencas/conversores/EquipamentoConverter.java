/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.licencas.conversores;

import com.licencas.model.entities.Equipamento;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Marcus
 */
@FacesConverter(value = "equipamentoconverter")
public class EquipamentoConverter implements Converter{
    
    
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component,
            String value) {
        if (value != null) {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
    }
 
    @Override
    public String getAsString(FacesContext ctx, UIComponent component,
            Object value) {
 
        if (value != null && ! "".equals(value)) {
            Equipamento entity = (Equipamento) value;
 
            if (entity.getId() != null) {
                this.addAttribute(component, entity);
 
                if (entity.getId() != null) {
                    return String.valueOf(entity.getId());
                }
                return (String) value;
            }
        }
        return "";
    }
 
    private void addAttribute(UIComponent component, Equipamento loc) {
        this.getAttributesFrom(component).put(loc.getId().toString(), loc);
    }
 
    private Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
    
}
