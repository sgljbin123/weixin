package com.gongzhonghao.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.gongzhonghao.dao.BaseDaoI;

@Repository("baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDaoI<T> {

	private Logger logger = Logger.getLogger(BaseDaoImpl.class);
	
	@Autowired  
    public void setSessionFactoryOverride(SessionFactory sessionFactory)  
    {  
  
        super.setSessionFactory(sessionFactory);  
    } 
	@Override
	public Serializable save(T o) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().save(o);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public T find(String queryString, Object[] values){
		List<T> find = (List<T>) getHibernateTemplate().find(queryString,values);
		return (find == null || find.size()==0)?null:find.get(0);
	}
    
	
	@Override
	public void delete(T o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(o);
		
	}
	
	

	@Override
	public void deleteAll(List<T> t){
		getHibernateTemplate().deleteAll(t);
	}
	@Override
	public void flush(){
		getHibernateTemplate().flush();
	}
	@Override
	public void clear(){
		getHibernateTemplate().clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(String queryString, Object[] values) {
		// TODO Auto-generated method stub
		List<T> find = (List<T>) getHibernateTemplate().find(queryString,values);
		return find;
	}
	@Override
	public void update(T o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(o);
		
	}
	@Override
	public void saveOrUpdate(T o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(o);
	}
	@Override
	public List<T> query(final String queryString, final Object[] values, final int page, final int row) {
		// TODO Auto-generated method stub
		List list = getHibernateTemplate().execute(new HibernateCallback<List>()
		    {
		     @Override
		 public List<T> doInHibernate(Session session) {
		       Query query = session.createQuery(queryString);
		         query.setFirstResult((page-1)*row);
		       query.setMaxResults(row);
		        for (int i = 0; i < values.length ; i++) {
		          query.setParameter(i, values[i]);
		        }
		        return query.list();
	     }
		    });
	     return list;
	}
	@Override
	public int count(String queryString, Object[] values) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select count(*)");
		queryString = sql.append(queryString).toString();
		List find = getHibernateTemplate().find(queryString, values);
		return (find == null || find.size()==0)?0:Integer.valueOf((String) find.get(0));
	}
	@Override
	public List<T> query(final String queryString) {
		// TODO Auto-generated method stub
		List list = getHibernateTemplate().execute(new HibernateCallback<List>()
		    {
		     @Override
		 public List<T> doInHibernate(Session session) {
		       Query query = session.createQuery(queryString);
		        return query.list();
	     }
		    });
	     return list;
	}
	@Override
	public List<T> findByParam(String queryString,String[] paramNames,Object[] values) {
		// TODO Auto-generated method stub
		
		return (List<T>) getHibernateTemplate().findByNamedParam(queryString, paramNames, values);
	}
	
	@Override
	public List<T> findByParam(String queryString,String paramName,Object value){
		return (List<T>) getHibernateTemplate().findByNamedParam(queryString, paramName, value);
	}
	
	@Override
	public List<T> findByExample(T o){
		return getHibernateTemplate().findByExample(o);
	}
	@Override
	public void merge(T o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().merge(o);
	}

}

