
package pl.kozervar.exap.rest.facade;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import pl.kozervar.exap.dao.DAO;
import pl.kozervar.exap.model.ExamPaperQuestion;
import pl.kozervar.exap.model.question.Question;
import pl.kozervar.exap.model.question.QuestionDetail;


@Stateless
@Path("/crud/Question")
public class QuestionCreatorRESTFacade {

	@EJB
	private DAO dao;

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Question create(Question entity) {
		Question newQuestion = new Question(entity);
		List<QuestionDetail> questionDetails = entity.getQuestionDetails();
		List<ExamPaperQuestion> examPaperQuestions = entity
		        .getExamPaperQuestion();
		Question created = dao.create(newQuestion);
		created.setQuestionDetails(questionDetails);
		created.setExamPaperQuestion(examPaperQuestions);
		Question updated = dao.update(created);
		return updated;
	}
	
	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public Question get(@PathParam("id") Integer id) {
		return dao.get(Question.class, id);
	}
	
	@GET
	@Path("{id}/questionDetails")
	@Produces({ "application/json" })
	public Collection<QuestionDetail> getQuestionDetails(@PathParam("id") Integer id) {
		Question question = dao.get(Question.class, id);
		return question.getQuestionDetails();
	}
	
	@GET
	@Produces({ "application/json" })
	public Collection<Question> getAll() {
		return dao.getAll(Question.class);
	}
	
	@PUT
	@Path("{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Question update(Question entity) {
		Question updatedQuestion = new Question(entity);
		List<QuestionDetail> questionDetails = entity.getQuestionDetails();
		List<ExamPaperQuestion> examPaperQuestions = entity
		        .getExamPaperQuestion();
		updatedQuestion.setQuestionDetails(questionDetails);
		updatedQuestion.setExamPaperQuestion(examPaperQuestions);
		Question updated = dao.update(updatedQuestion);
		return updated;
	}


	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		dao.delete(dao.get(Question.class, id));
	}

}
