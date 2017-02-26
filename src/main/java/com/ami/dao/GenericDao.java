package com.ami.dao;

import java.util.List;

/**
 * generic class for db interaction.
 */
public interface GenericDao {

	 <T> T  addEntity(T entity) throws Exception;
	 <T> T getEntity(String query, List<Object> values) throws Exception;
	 <T> List<T> getEntities(String query, List<Object> values) throws Exception;
	 <T> boolean deleteEntity(T entity) throws Exception;
	 <T> T updateEntity(T entity) throws Exception;
}
