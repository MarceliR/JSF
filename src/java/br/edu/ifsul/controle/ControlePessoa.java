/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;


import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.UtilMensagens;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Marcely
 */
@ManagedBean(name="controlePessoa")
@SessionScoped
public class ControlePessoa implements Serializable{
    
    private PessoaDAO<Pessoa> dao;
    private Pessoa objeto;
 
    
    
    public ControlePessoa(){
        dao = new PessoaDAO<>();
      
    }
    
    public String listar(){
        return "/privado/pessoa/listar?faces-redirect=true";
        
    }
    
    public String novo(){
        setObjeto(new Pessoa());
        return "formulario";
        
    }
    
    public String salvar(){
        boolean persistiu;
        if (getObjeto().getId() == null){
            persistiu = getDao().persist(getObjeto());
        }else{
            persistiu = getDao().merge(getObjeto());
        }
        if (persistiu){
            UtilMensagens.mensagemInformacao(getDao().getMensagem());
            return "listar";
        }else{
            UtilMensagens.mensagemErro(getDao().getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar(){
        setObjeto(null);
        return "listar";
    }
    
    public String editar (Integer id){
        objeto = dao.localizar(id);
        return "formulario";
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
       if (dao.remove(objeto)){
            UtilMensagens.mensagemInformacao(getDao().getMensagem());
        }else{
            UtilMensagens.mensagemErro(getDao().getMensagem());
        }
    }

    public PessoaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaDAO dao) {
        this.dao = dao;
    }

    public Pessoa getObjeto() {
        return objeto;
    }

    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoEstado
     */
  
    
}
