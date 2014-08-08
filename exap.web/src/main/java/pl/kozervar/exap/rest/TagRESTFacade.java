
package pl.kozervar.exap.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import pl.kozervar.exap.dao.DAO;
import pl.kozervar.exap.model.ExamPaperQuestion;
import pl.kozervar.exap.model.question.Question;
import pl.kozervar.exap.model.question.QuestionDetail;
import pl.kozervar.exap.model.question.Question_;
import pl.kozervar.exap.model.tag.QuestionTag;
import pl.kozervar.exap.model.tag.QuestionTag_;
import pl.kozervar.exap.model.tag.Tag;
import pl.kozervar.exap.model.tag.Tag_;


@Stateless
@Path("/tags")
public class TagRESTFacade {

	@EJB
	private DAO dao;

	@Inject
	private EntityManager em;
	
	@Inject
	private Logger logger;
	
	@GET
	@Produces({ "application/json" })
	public Collection<Tag> getAvaliableTags() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tag> query = cb.createQuery(Tag.class);
		Root<Tag> root = query.from(Tag.class);
		query.select(root);
		TypedQuery<Tag> q = em.createQuery(query);
		List<Tag> results = q.getResultList();
		List<Tag> tags = new ArrayList<Tag>(results.size());
		return tags;
	}

	@GET
	@Path("query")
	@Produces({ "application/json" })
	public Collection<Tag> getAvaliableTagsQuery(
	        @QueryParam("query") String queryString) {
		if (queryString == null || queryString.length() == 0)
		    return new ArrayList<Tag>();
		String pattern = "%" + queryString + "%";

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tag> query = cb.createQuery(Tag.class);
		Root<Tag> root = query.from(Tag.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.like(root.get(Tag_.name), pattern));

		query.select(root)
		        .where(cb.and(predicates.toArray(new Predicate[] {})));

		TypedQuery<Tag> q = em.createQuery(query);
		List<Tag> results = q.getResultList();
		return results;
	}

}
