
package pl.kozervar.exap.rest.facade.question;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import pl.kozervar.exap.model.question.QuestionHeader;
import pl.kozervar.exap.model.question.QuestionHeader_;
import pl.kozervar.exap.model.question.QuestionSubject;
import pl.kozervar.exap.model.question.QuestionSubject_;
import pl.kozervar.exap.rest.facade.RESTFacade;


@Stateless
@Path("/QuestionHeader")
public class QuestionHeaderRESTFacade extends RESTFacade<QuestionHeader> {

	public QuestionHeaderRESTFacade() {
		super(QuestionHeader.class);
	}

	@GET
	@Path("count")
	@Produces("text/plain")
	@Override
	public String count() {
		return super.count();
	}

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Override
	public QuestionHeader create(QuestionHeader entity) {
		return super.create(entity);
	}

	@PUT
	@Path("{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Override
	public QuestionHeader edit(QuestionHeader entity) {
		return super.edit(entity);
	}

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	@Override
	public QuestionHeader find(@PathParam("id") Integer id) {
		return super.find(id);
	}

	@GET
	@Produces({ "application/json" })
	@Override
	public Collection<QuestionHeader> findAll() {
		EntityManager em = getEntityManager();
		CriteriaQuery<QuestionHeader> cq = em.getCriteriaBuilder().createQuery(QuestionHeader.class);
		Root<QuestionHeader> root = cq.from(QuestionHeader.class);
		Fetch<QuestionHeader, QuestionSubject> fetch = root.fetch(QuestionHeader_.questionSubject, JoinType.LEFT);
		fetch.fetch(QuestionSubject_.examPaperQuestionSubjects, JoinType.LEFT);
		cq.distinct(true);
		List<QuestionHeader> resultList = em.createQuery(cq).getResultList();
		return resultList;
	}

	@GET
	@Path("{from}/{to}")
	@Produces({ "application/json" })
	@Override
	public Collection<QuestionHeader> findRange(
	        @PathParam("from") Integer from, @PathParam("to") Integer to) {
		return super.findRange(from, to);
	}

	@DELETE
	@Path("{id}")
	@Override
	public void remove(@PathParam("id") Integer id) {
		super.remove(id);
	}

}
