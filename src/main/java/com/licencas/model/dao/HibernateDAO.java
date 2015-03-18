package com.licencas.model.dao;

import com.licencas.model.entities.Bem;
import com.licencas.model.entities.Comarca;
import com.licencas.model.entities.Foro;
import com.licencas.model.entities.Licencas;
import com.licencas.model.entities.Local;
import com.licencas.model.entities.Usuario;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class HibernateDAO<T> implements InterfaceDAO<T>, Serializable {

    private static final long serialVersionUID = 1L;
    
    private Class<T> classe;
    private Session session;

    public HibernateDAO(Class<T> classe, Session session) {
        super();
        this.classe = classe;
        this.session = session;
    }
    
    
    @Override
    public void save(T entity) {
        session.save(entity);
    }

    @Override
    public void update(T entity) {
        session.update(entity);
    }

    @Override
    public void remove(T entity) {
        session.delete(entity);
        
    }

    @Override
    public void merge(T entity) {
        session.merge(entity);
    }

    @Override
    public T getEntity(Serializable id) {
        T entity = (T)session.get(classe, id);
        return entity;
    }

    @Override
    public T getEntityByDetachedCriteria(DetachedCriteria criteria) {
        T entity = (T)criteria.getExecutableCriteria(session).uniqueResult();
        return entity;
    }

        
    @Override
    public T getEntityByHQLQuery(String stringQuery) {
        Query query = session.createQuery(stringQuery);        
        return (T) query.uniqueResult();
    }

    @Override
    public List<T> getListByDetachedCriteria(DetachedCriteria criteria) {
        return criteria.getExecutableCriteria(session).list();
    }
    
    @Override
    public List<T> getEntities() {
        List<T> entities = (List<T>) session.createCriteria(classe).list();
        return entities;
    }    

    @Override
    public T getbyId(Integer i) {
        T entity = (T)session.get(classe, i);
        return entity;
        
    }

    @Override
    public List<T> getListbyCriteria(String query)
    {
        SQLQuery q = session.createSQLQuery(query);
        q.addEntity(classe);
        q.setParameter(1, q);
        return q.list();
    }
    
    /**
     *
     * @param parametro
     * @return
     */
    @Override
    public List<Foro> getListComarcaForo(Integer parametro)
    {
        SQLQuery q = session.createSQLQuery("Select * from foro where com_id = :parametro");
        q.addEntity(Foro.class);
        q.setParameter("parametro", parametro);
        return q.list();
    }
    @Override
    public List<Local> getListForoLocal(Integer parametro)
    {
        SQLQuery q = session.createSQLQuery("Select * from local where for_id = :parametro");
        q.addEntity(Local.class);
        q.setParameter("parametro", parametro);
        return q.list();
    }
    
    
    @Override
    public List<T> getListByparam(String query,Integer parametro)
    {
        
      Query q = session.createQuery(query);
      q.setParameter("id", parametro);
      return q.list();
    }

    @Override
    public List<T> getListByQuery(String query) {
        Query q = session.createQuery(query);
        return q.list();
        
    }

    @Override
    public List<T> getListBySQLQuery(String query) {
        SQLQuery q = session.createSQLQuery(query);
        q.addEntity(Licencas.class);
        return q.list();
    
    
    }

    @Override
    public List<T> getListDesativadas(String query) {
       Criteria crit = session.createCriteria(Licencas.class);
       crit.add(Restrictions.eq("status","DESATIVADA"));
       return crit.list();
    
    }

    @Override
    public Usuario buscaporlogin(String login) {
        String hql = "Select u from Usuario u where u.login = :login";
        Query consulta = this.session.createQuery(hql);
        consulta.setString("login", login);
        return (Usuario) consulta.uniqueResult();
    }

    @Override
    public Licencas buscaporlicenca(String desc) {
        String hql = "Select l from Licencas l where l.lic_desc = :desc";
        Query consulta = this.session.createQuery(hql);
        consulta.setString("desc", desc);
        return (Licencas) consulta.uniqueResult();
    }

    @Override
    public Comarca buscacomarca(String desc) {
        String hql = "Select c from Comarca c where c.com_desc = :desc";
        Query consulta = this.session.createQuery(hql);
        consulta.setString("desc", desc);
        return (Comarca) consulta.uniqueResult();
    }

    @Override
    public T getCampoUnico(String hql, String desc) {
        Query consulta = this.session.createQuery(hql);
        consulta.setString("desc", desc);
        return (T) consulta.uniqueResult();
    }

    @Override
    public List<T> getLisByPatrimonio(String hql,String patrimonio) {
        
        Query q = session.createQuery(hql);
        q.setParameter("patrimonio", patrimonio);
        return q.list();
    
    }

    @Override
    public List<T> getListByLocal(String hql, Local local) {
        Criteria crit = session.createCriteria(Bem.class);
        crit.add(Restrictions.eq("local",local));
        return crit.list();
    
    }

    @Override
    public List<T> getListBySqlQuery(String query) {
        SQLQuery q = session.createSQLQuery(query);
        q.addEntity(classe);
        return q.list();
    
    }
    
    
    //busca todos os computadores
    @Override
    public List<T> getListByComput() {
        Criteria crit = session.createCriteria(Bem.class);
        crit.add(Restrictions.and(Restrictions.ne("computador",""),Restrictions.isNotNull("computador")));
        return crit.list();
    }
}

    
    

