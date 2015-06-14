package com.souq.dao;

import java.util.List;

public interface GenericDao {

	public <T> T  addEntity(T entity) throws Exception;
	public <T> T addEntity(String query, List<Object> values) throws Exception;
	public <T> T getEntity(String query, List<Object> values) throws Exception;
	public <T> List<T> getEntities(String query, List<Object> values) throws Exception;
	public <T> boolean deleteEntity(T entity) throws Exception;
	public <T> int updateEntity(String query, List<Object> values) throws Exception;
	public <T> T updateEntity(T entity) throws Exception;
	public <T> T addUpdateEntity(T entity) throws Exception;
	public <T> T getEntityNative(String query) throws Exception;
}
