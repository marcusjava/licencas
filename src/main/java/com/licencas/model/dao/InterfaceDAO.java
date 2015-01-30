package com.licencas.model.dao;

import com.licencas.model.entities.Foro;
import com.licencas.model.entities.Licencas;
import com.licencas.model.entities.Local;
import com.licencas.model.entities.Usuario;
import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface InterfaceDAO<T> {
    
    void save (T entity);
    void update (T entity);
    void remove (T entity);
    void merge (T entity);
    T getEntity(Serializable id);
    T getEntityByDetachedCriteria(DetachedCriteria criteria);
    T getEntityByHQLQuery(String stringQuery);
    T getbyId(Integer i);
    List<T> getEntities();
    List<T> getListByDetachedCriteria(DetachedCriteria criteria);  
    List<T> getListbyCriteria(String query);
    List<Foro> getListComarcaForo(Integer parametro);
    List<Local> getListForoLocal(Integer parametro);
    List<T> getListByparam(String query,Integer parmetro);
    List<T> getListByQuery(String query);
    List<T> getListBySQLQuery(String query);
    List<T> getListDesativadas(String query);
    //busca por login do usuario SpringSecutity
    Usuario buscaporlogin(String login);
    //pesquisa pelo campo unico descrição
    Licencas buscaporlicenca(String desc);
    
}
