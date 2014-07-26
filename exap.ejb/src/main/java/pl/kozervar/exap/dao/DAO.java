// ******************************************************************
// DAO.java
// Copyright 2013 by Marcin Kozaczyk. All rights reserved.
// ******************************************************************

package pl.kozervar.exap.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import pl.kozervar.exap.model.Identifiable;


@Local
public interface DAO extends Serializable {

	/**
	 * @param <T>
	 * @return count of elements
	 */
	<T extends Identifiable> int count(Class<T> entityClass);

	/**
	 * Creates entity.
	 * 
	 * @param <T>
	 * 
	 * @param entity
	 */
	<T extends Identifiable> T create(T entity);

	/**
	 * Deletes entity.
	 * 
	 * @param <T>
	 * 
	 * @param entity
	 */
	<T extends Identifiable> void delete(T entity);

	<T extends Identifiable> List<T> findRange(Class<T> entityClass, int[] range);

	/**
	 * Returns entity.
	 * 
	 * @param <T>
	 * 
	 * @param entityID
	 * @return
	 */
	<T extends Identifiable> T get(Class<T> entityClass, int entityID);

	/**
	 * Returns all entities.
	 * 
	 * @param <T>
	 * 
	 * @return
	 */
	<T extends Identifiable> List<T> getAll(Class<T> entityClass);

	EntityManager getEntityManager();

	/**
	 * Updates entities.
	 * 
	 * @param <T>
	 * 
	 * @param entity
	 * @return
	 */
	<T extends Identifiable> T update(T entity);
}
