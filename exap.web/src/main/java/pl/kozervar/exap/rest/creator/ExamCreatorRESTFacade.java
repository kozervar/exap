
package pl.kozervar.exap.rest.creator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

import pl.kozervar.exap.dao.DAO;
import pl.kozervar.exap.model.ExamPaperQuestion;
import pl.kozervar.exap.model.ExamPaperQuestion_;
import pl.kozervar.exap.model.exam.ExamPaper;
import pl.kozervar.exap.model.exam.ExamPaper_;
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
	public ExamPaper create(ExamPaperDTO examPaperDTO) {
		ExamPaper newExamPaper = new ExamPaper(examPaperDTO.getExamPaper());
		List<Question> questions = examPaperDTO.getQuestions();
		ExamPaper createdExamPaper = dao.create(newExamPaper);
		List<ExamPaperQuestion> examPaperQuestions = createdExamPaper
		        .getExamPaperQuestions();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Question> query = cb.createQuery(Question.class);
		Root<Question> root = query.from(Question.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		for (Question question : questions) {
			predicates.add(cb.equal(root.get(Question_.id), question.getId()));
		}

		query.select(root).where(cb.or(predicates.toArray(new Predicate[] {})));
		TypedQuery<Question> q = em.createQuery(query);
		List<Question> originalQuestions = q.getResultList();

		int sortOrder = 0;
		for (Question question : originalQuestions) {
			ExamPaperQuestion epq = new ExamPaperQuestion();
			epq.setQuestion(question);
			epq.setExamPaper(createdExamPaper);
			epq.setSortOrder(sortOrder++);
			examPaperQuestions.add(epq);
		}
		createdExamPaper.setExamPaperQuestions(examPaperQuestions);
		return dao.update(createdExamPaper);
	}

	@GET
	@Produces({ "application/json" })
	public Collection<ExamPaper> getAll() {
		// List<ExamPaper> all = dao.getAll(ExamPaper.class);
		// List<ExamPaperDTO> examPaperDTOs = new
		// ArrayList<ExamPaperDTO>(all.size());
		// for (ExamPaper examPaper : all) {
		// List<Question> questions = new ArrayList<Question>();
		// List<ExamPaperQuestion> examPaperQuestions =
		// examPaper.getExamPaperQuestions();
		// for (ExamPaperQuestion examPaperQuestion : examPaperQuestions) {
		// questions.add(examPaperQuestion.getQuestion());
		// }
		// examPaper.setExamPaperQuestions(null);
		// ExamPaperDTO examPaperDTO = new ExamPaperDTO();
		// examPaperDTO.setExamPaper(examPaper);
		// }
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
	public ExamPaper update(ExamPaperDTO examPaperDTO,
	        @PathParam("id") Integer id) {
		ExamPaper examPaperToUpdate = examPaperDTO.getExamPaper();
		List<Question> questions = examPaperDTO.getQuestions();
		ExamPaper found = em.find(ExamPaper.class, id.longValue());
		
		found.setActive(examPaperToUpdate.getActive());
		found.setDescription(examPaperToUpdate.getDescription());
		found.setExamType(examPaperToUpdate.getExamType());
		found.setName(examPaperToUpdate.getName());
		
		Set<ExamPaperQuestion> originalExamPaperQuestions = new HashSet<ExamPaperQuestion>(found.getExamPaperQuestions());
		
		Set<ExamPaperQuestion> examPaperQuestions = new HashSet<ExamPaperQuestion>();

		int sortOrder = 0;
		for (Question question : questions) {
			ExamPaperQuestion epq = new ExamPaperQuestion();
			epq.setQuestion(question);
			epq.setExamPaper(examPaperToUpdate);
			epq.setSortOrder(sortOrder++);
			examPaperQuestions.add(epq);
		}
		originalExamPaperQuestions.retainAll(examPaperQuestions);
		for (ExamPaperQuestion examPaperQuestion : originalExamPaperQuestions) {
	        for (ExamPaperQuestion newExamPaperQuestion : examPaperQuestions) {
	            if(examPaperQuestion.equals(newExamPaperQuestion))
	            {
	            	examPaperQuestion.setSortOrder(newExamPaperQuestion.getSortOrder());
	            }
            }
        }
		originalExamPaperQuestions.addAll(examPaperQuestions);
		
		found.setExamPaperQuestions(new ArrayList<ExamPaperQuestion>(originalExamPaperQuestions));
		return dao.update(found);
	};

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		dao.delete(dao.get(ExamPaper.class, id));
	}

}
