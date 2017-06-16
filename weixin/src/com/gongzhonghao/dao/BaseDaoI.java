package com.gongzhonghao.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDaoI<T> {

	
	public Serializable save(T o);
	public List<T> findList(String queryString, Object[] values);
	public T find(String queryString, Object[] values);
	public void delete(T o);
	public void deleteAll(List<T> t);
	public void update(T o);
	public void flush();
	public void clear();
	public void saveOrUpdate(T o);
	public List<T> query(String queryString,final Object[] params,Object[] values,int page,int row);
	public int count(String queryString,Object[] values);
	public List<T> query(final String queryString);
	public List<T> findByParam(String queryString,String[] paramNames,Object[] values);
	public List<T> findByParam(String queryString,String paramName,Object value);
	public List<T> findByExample(T o);
	public void merge(T o);
}
