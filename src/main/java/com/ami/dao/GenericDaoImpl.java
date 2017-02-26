package com.ami.dao;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Named
public class GenericDaoImpl implements GenericDao {

	@Inject
	private SessionFactory sessionFactory;

	private Session session;


	@Override
	public <T> T addEntity(T entity) throws Exception {
		try {
			session = sessionFactory.getCurrentSession();
			session.save(entity);
			return entity;
		} catch (HibernateException e){
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ami.dao.GenericDao#getEntity(java.lang.String, java.util.List)
	 * need to change it to just one value
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEntity(String query, List<Object> values) throws Exception {
		T entity = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query sqlQuery = session.createQuery(query);

			if (values != null && values.size() > 0){
				for (int i = 0; i < values.size(); i++) {
					sqlQuery.setParameter(i, values.get(i));
				}
			}
			entity = (T) sqlQuery.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getEntities(String query, List<Object> values)
			throws Exception {
		List<T> entities = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query sqlQuery = session.createQuery(query);

			if (values != null && values.size() > 0){
				for (int i = 0; i < values.size(); i++){
					sqlQuery.setParameter(i, values.get(i));
				}
			}
			entities = sqlQuery.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entities;
	}


	/**
	 * delete an entity.
 	 * @param delete T
	 * @param <T> T
	 * @return boolean
	 * @throws Exception exception.
	 */
	@Override
	public <T> boolean deleteEntity(T delete) throws Exception {
		try {
			session = sessionFactory.getCurrentSession();
			session.delete(delete);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public <T> T updateEntity(T entity) throws Exception {

		try {
			session = sessionFactory.getCurrentSession();
			session.update(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}