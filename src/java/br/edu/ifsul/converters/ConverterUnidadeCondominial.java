/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.UnidadeCondominial;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Marcely
 */
@FacesConverter(value = "converterUnidadeCondominial")
public class ConverterUnidadeCondominial implements Serializable, Converter{

    //método que converte da tela para objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
       if (string == null || string.equals("Selecione um registro")){
           return null;
       }
       try{
           return EntityManagerUtil.getEntityManager().find(UnidadeCondominial.class,
                   Integer.parseInt(string));
       }catch (Exception e){
           return null;
       }
    }

    @Override//método que converter do objeto para a tela
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o==null){
            return null;
        }
        UnidadeCondominial obj = (UnidadeCondominial) o;
        return obj.getId().toString();
    }
    
}
