
package pl.kozervar.exap.rest.creator;

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
import pl.kozervar.exap.model.exam.ExamPaper;
import pl.kozervar.exap.model.question.Question;
import pl.kozervar.exap.model.question.QuestionDetail;
import pl.kozervar.exap.model.question.Question_;
import pl.kozervar.exap.model.tag.QuestionTag;
import pl.kozervar.exap.model.tag.QuestionTag_;
import pl.kozervar.exap.model.tag.Tag;
import pl.kozervar.exap.model.tag.Tag_;


@Stateless
@Path("/crud/ExamPaper")
public class ExamCreatorRESTFacade {

	@EJB
	private DAO dao;

	@Inject
	private EntityManager em;
	
	@Inject
	private Logger logger;

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public ExamPaper create(ExamPaper examPaper) {
		ExamPaper newExamPaper = new ExamPaper(examPaper);
		newExamPaper.setExamPaperQuestions(examPaper.getExamPaperQuestions());
		ExamPaper created = dao.create(newExamPaper);
		return created;
	}
	@GET
	@Produces({ "application/json" })
	public Collection<ExamPaper> getAll() {
		return dao.getAll(ExamPaper.class);
	}

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public ExamPaper get(@PathParam("id") Integer id) {
		return dao.get(ExamPaper.class, id);
	}

	@PUT
	@Path("{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public ExamPaper update(ExamPaper examPaper) {
		dao.update(examPaper);
		return null;
	};

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		dao.delete(dao.get(ExamPaper.class, id));
	}

}
