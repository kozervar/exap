
package pl.kozervar.exap.rest.facade;

import java.util.Collection;

import javax.ejb.EJB;

import pl.kozervar.exap.dao.DAO;
import pl.kozervar.exap.model.Identifiable;

public abstract class RESTFacade<T extends Identifiable> {

	@EJB
	private DAO dao;

	private final Class<T> entityClass;

	public RESTFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public String count() {
		return String.valueOf(getDao().count(entityClass));
	}

	public T create(T entity) {
		T create = getDao().create(entity);
		return create;
	}

	public T edit(T entity) {
		T update = getDao().update(entity);
		return update;
	}

	public T find(Integer id) {
		return getDao().get(entityClass, id);
	}

	public Collection<T> findAll() {
		return getDao().getAll(entityClass);
	}

	public Collection<T> findRange(Integer from, Integer to) {
		return getDao().findRange(entityClass, new int[] { from, to });
	}

	public DAO getDao() {
		return dao;
	}

	public void remove(Integer id) {
		getDao().delete(getDao().get(entityClass, id));
	}
}
