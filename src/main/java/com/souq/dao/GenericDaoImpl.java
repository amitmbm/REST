package com.souq.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class GenericDaoImpl implements GenericDao {

	@Inject
	SessionFactory sessionFactory;

	Session session = null;


	@Override
	public <T> T addEntity(T entity) throws Exception {
		try{
			session = sessionFactory.getCurrentSession();
			session.save(entity);
			return entity;
		}catch(Exception e){
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
		try{
			session = sessionFactory.getCurrentSession();
			Query sqlQuery = session.createQuery(query);

			if (values != null && values.size() > 0){
				for (int i = 0; i < values.size() ; i++){
					sqlQuery.setParameter(i, values.get(i));
				}
			}
			entity = (T)sqlQuery.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return entity;
	}


	/*
	 * (non-Javadoc)
	 * @see com.ami.dao.GenericDao#getEntity(java.lang.String, java.util.List)
	 * need to change it to just one value
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getEntityNative(String query) throws Exception {
		T entity = null;
		try{
			session = sessionFactory.getCurrentSession();
			Query sqlQuery = session.createSQLQuery(query);
			entity = (T)sqlQuery.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getEntities(String query, List<Object> values)
			throws Exception {
		List<T> entities = null;
		try{
			session = sessionFactory.getCurrentSession();
			Query sqlQuery = session.createQuery(query);

			if (values != null && values.size() > 0){
				for (int i = 0; i < values.size() ; i++){
					sqlQuery.setParameter(i, values.get(i));
				}
			}
			entities = sqlQuery.list();
		}catch(Exception e){
			e.printStackTrace();
			//tx.rollback();	
		}
		return entities;
	}

	@Override
	public <T> boolean deleteEntity(T delete) throws Exception {
		try{
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
	public <T> int updateEntity(String query, List<Object> values)throws Exception {  
		int result=0;
		try{
			session = sessionFactory.getCurrentSession();
			Query sqlQuery = session.createQuery(query);

			if (values != null && values.size() > 0){
				for (int i = 0; i < values.size() ; i++){
					sqlQuery.setParameter(i, values.get(i));
				}
			}
			result = sqlQuery.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public <T> T updateEntity(T entity) throws Exception {

		try{
			session = sessionFactory.getCurrentSession();
			session.update(entity);
		/*	if (entity instanceof MasterDTO)
				((MasterDTO)entity).setUpdatedAt(new Date());
*/
			return entity;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public <T> T addUpdateEntity(T entity)throws Exception{
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
		return entity;
	}

	@Override
	public <T> T addEntity(String query, List<Object> values) throws Exception {
		T entity = null;
		try{
			session = sessionFactory.getCurrentSession();
			Query sqlQuery = session.createQuery(query);

			if (values != null && values.size() > 0){
				for (int i = 0; i < values.size() ; i++){
					sqlQuery.setParameter(i, values.get(i));
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return entity;
	}
}