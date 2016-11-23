/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.util.UtilMensagens;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Marcely
 */
@ManagedBean(name="controleLocatario")
@SessionScoped
public class ControleLocatario implements Serializable{
    
    private LocatarioDAO dao;
    private Locatario objeto;
    
    public ControleLocatario(){
        dao = new LocatarioDAO();
    }
    
    public String listar(){
        return "/privado/locatario/listar?faces-redirect=true";
        
    }
    
    public String novo(){
        objeto = new Locatario();
        return "formulario";
        
    }
    
    public String salvar(){
        if (dao.salvar(objeto)){
            UtilMensagens.mensagemInformacao("Sucesso ao persistir!");
            return "listar";
        }else{
            UtilMensagens.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar(){
        objeto = null;
        return "listar";
    }
    
    public String editar (Integer id){
        objeto = dao.localizar(id);
        return "formulario";
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            UtilMensagens.mensagemInformacao(dao.getMensagem());
        }else{
            UtilMensagens.mensagemErro(dao.getMensagem());
        }
    }

    public LocatarioDAO getDao() {
        return dao;
    }

    public void setDao(LocatarioDAO dao) {
        this.dao = dao;
    }

    public Locatario getObjeto() {
        return objeto;
    }

    public void setObjeto(Locatario objeto) {
        this.objeto = objeto;
    }
    
    
    
    
}
