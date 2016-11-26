/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.dao.UnidadeCondominialDAO;

import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.UnidadeCondominial;


import br.edu.ifsul.util.UtilMensagens;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Marcely
 */
@ManagedBean(name="controleCondominio")
@SessionScoped
public class ControleCondominio implements Serializable{
    
    private CondominioDAO<Condominio> dao;
    private Condominio objeto;
    private UnidadeCondominialDAO<UnidadeCondominial> unidadeCondominialDAO;
    private UnidadeCondominial item;
   
    private Boolean novoItem;
    
    public void novoItem(){
        item = new UnidadeCondominial();
        novoItem = true;
    }
    
    public void alterarItem(int index){
        item = objeto.getUnidadeCondominial().get(index);
        novoItem = false;
    }
    
    public void removerItem(int index){
        objeto.removerItem(index);
        UtilMensagens.mensagemInformacao("Item removido com sucesso!");
    }
    
    public void salvarItem(){
        if (novoItem){
            objeto.adicionarUnidadeCondominial(item);
        }
        UtilMensagens.mensagemInformacao("Operação executada com sucesso!");
    }
    

    

    public ControleCondominio(){
        dao = new CondominioDAO<>();
        unidadeCondominialDAO = new UnidadeCondominialDAO<>();
      
        
    }
    
    public String listar(){
        return "/privado/condominio/listar?faces-redirect=true";
        
    }
    
    public void novo(){
        setObjeto(new Condominio());
       
        
    }
    
    public void salvar(){
        boolean persistiu;
        if (getObjeto().getId() == null){
            persistiu = getDao().persist(getObjeto());
        }else{
            persistiu = getDao().merge(getObjeto());
        }
        if (persistiu){
            UtilMensagens.mensagemInformacao(getDao().getMensagem());
          
        }else{
            UtilMensagens.mensagemErro(getDao().getMensagem());
          
        }
    }
    
   
    
    public void editar (Integer id){
        objeto = dao.localizar(id);
     
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
       if (dao.remove(objeto)){
            UtilMensagens.mensagemInformacao(getDao().getMensagem());
        }else{
            UtilMensagens.mensagemErro(getDao().getMensagem());
        }
    }

    public CondominioDAO getDao() {
        return dao;
    }

    public void setDao(CondominioDAO dao) {
        this.dao = dao;
    }

    public Condominio getObjeto() {
        return objeto;
    }

    public void setObjeto(Condominio objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoEstado
     */
    

    public UnidadeCondominialDAO<UnidadeCondominial> getCondominialDAO() {
        return unidadeCondominialDAO;
    }

    public void setUnidadeCondominialDAO(UnidadeCondominialDAO<UnidadeCondominial> unidadeCondominialDAO) {
        this.unidadeCondominialDAO = unidadeCondominialDAO;
    }

   

    public UnidadeCondominial getItem() {
        return item;
    }

    public void setItem(UnidadeCondominial item) {
        this.item = item;
    }

    public Boolean getNovoItem() {
        return novoItem;
    }

    public void setNovoItem(Boolean novoItem) {
        this.novoItem = novoItem;
    }
    
    
    
    
}
