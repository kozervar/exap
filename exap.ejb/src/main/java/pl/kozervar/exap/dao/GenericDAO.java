// ******************************************************************
// GenericDAO.java
// Copyright 2013 by Marcin Kozaczyk. All rights reserved.
// ******************************************************************

package pl.kozervar.exap.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;

import pl.kozervar.exap.model.Identifiable;


@Singleton
@LocalBean
public class GenericDAO implements DAO {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@Inject
	private Logger logger;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends Identifiable> int count(Class<T> entityClass) {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder()
		        .createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(em.getCriteriaBuilder().count(rt));
		Query q = getEntityManager().createQuery(cq);
		int result = ((Long) q.getSingleResult()).intValue();
		logger.debug("Count = " + result);
		return result;
	}

	@Override
	public <T extends Identifiable> T create(T entity) {
		T merged = em.merge(entity);
		logger.debug("Created entity: " + entity.toString());
		return merged;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public <T extends Identifiable> void delete(T entity) {
		Identifiable entityToBeRemoved = em.find(entity.getClass(),
		        entity.getId());
		em.remove(entityToBeRemoved);
		logger.debug("Removed entity: " + entityToBeRemoved.toString());
		flush();
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends Identifiable> List<T> findRange(Class<T> entityClass,
	        int[] range) {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder()
		        .createQuery();
		cq.select(cq.from(entityClass));
		Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		logger.debug("Finding range: " + range.toString());
		return q.getResultList();
	}

	@Override
	public <T extends Identifiable> T get(Class<T> entityClass, int entityID) {
		T found;
		try {
			found = em.find(entityClass, entityID);
		}
		catch (IllegalArgumentException e) {
			found = em.find(entityClass, new Long(entityID));
		}
		logger.debug("Get entity: " + found.toString());
		return found;
	}

	// Using the unchecked because JPA does not have a
	// em.getCriteriaBuilder().createQuery()<T> method
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends Identifiable> List<T> getAll(Class<T> entityClass) {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		logger.debug("Getting all: " + entityClass.getCanonicalName());
		List resultList = em.createQuery(cq).getResultList();
		return resultList;
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public <T extends Identifiable> T update(T entity) {
		T mergedEntity = em.merge(entity);
		logger.debug("Updated entity: " + mergedEntity.toString());
		return mergedEntity;
	}

	private void populateQueryParameters(Query query,
	        Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

	// Using the unchecked because JPA does not have a
	// query.getSingleResult()<T> method
	@SuppressWarnings("unchecked")
	protected <T extends Identifiable> T findOneResult(String namedQuery,
	        Map<String, Object> parameters) {
		T result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);

			// Method that will populate parameters if they are passed not null
			// and empty
			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (T) query.getSingleResult();

		}
		catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	protected void flush() {
		em.flush();
	}
}
