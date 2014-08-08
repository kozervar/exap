
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
import pl.kozervar.exap.model.question.Question;
import pl.kozervar.exap.model.question.QuestionDetail;
import pl.kozervar.exap.model.question.Question_;
import pl.kozervar.exap.model.tag.QuestionTag;
import pl.kozervar.exap.model.tag.QuestionTag_;
import pl.kozervar.exap.model.tag.Tag;
import pl.kozervar.exap.model.tag.Tag_;


@Stateless
@Path("/crud/Question")
public class QuestionCreatorRESTFacade {

	@EJB
	private DAO dao;

	@Inject
	private EntityManager em;
	
	@Inject
	private Logger logger;

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Question create(QuestionDTO questionDTO) {
		Question question = questionDTO.getQuestion();
		List<Tag> tags = questionDTO.getTags();
		Question newQuestion = new Question(question);
		List<QuestionDetail> questionDetails = question.getQuestionDetails();
		List<ExamPaperQuestion> examPaperQuestions = question
		        .getExamPaperQuestion();
		Question created = dao.create(newQuestion);
		created.setQuestionDetails(questionDetails);
		created.setExamPaperQuestion(examPaperQuestions);
		for (Tag tag : tags) {
			QuestionTag qt = new QuestionTag();
			qt.setTag(tag);
			qt.setQuestion(created);
			dao.create(qt);
		}
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
	public Collection<QuestionDetail> getQuestionDetails(
	        @PathParam("id") Integer id) {
		Question question = dao.get(Question.class, id);
		return question.getQuestionDetails();
	}

	@GET
	@Produces({ "application/json" })
	public Collection<Question> getAll() {
		return dao.getAll(Question.class);
	}

	@GET
	@Path("tags")
	@Produces({ "application/json" })
	public Collection<Tag> getAvaliableTags() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<QuestionTag> query = cb.createQuery(QuestionTag.class);
		Root<QuestionTag> root = query.from(QuestionTag.class);
		root.join(QuestionTag_.tag);
		query.select(root);
		TypedQuery<QuestionTag> q = em.createQuery(query);
		List<QuestionTag> results = q.getResultList();
		List<Tag> tags = new ArrayList<Tag>(results.size());
		for (QuestionTag questionTag : results) {
			tags.add(questionTag.getTag());
		}
		return tags;
	}

	@GET
	@Path("tagsquery")
	@Produces({ "application/json" })
	public Collection<Tag> getAvaliableTagsQuery(
	        @QueryParam("query") String queryString) {
		if (queryString == null || queryString.length() == 0)
		    return new ArrayList<Tag>();
		String pattern = "%" + queryString + "%";

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<QuestionTag> query = cb.createQuery(QuestionTag.class);
		Root<QuestionTag> root = query.from(QuestionTag.class);
		Join<QuestionTag, Tag> tag = root.join(QuestionTag_.tag);

		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.like(tag.get(Tag_.name), pattern));

		query.select(root)
		        .where(cb.and(predicates.toArray(new Predicate[] {})));

		TypedQuery<QuestionTag> q = em.createQuery(query);
		List<QuestionTag> results = q.getResultList();
		List<Tag> tags = new ArrayList<Tag>(results.size());
		for (QuestionTag questionTag : results) {
			tags.add(questionTag.getTag());
		}
		return tags;
	}


	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Path("/bytags")
	public Collection<Question> getQuestionsByTags(List<Tag> tags) {
		if(tags == null)
			return new ArrayList<Question>();
		logger.info("Get question by tags " + tags.size());
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Question> query = cb.createQuery(Question.class);
		Root<Question> root = query.from(Question.class);
		Join<Question, QuestionTag> join = (Join<Question, QuestionTag>) root
		        .fetch(Question_.questionTags);

		List<Predicate> predicates = new ArrayList<Predicate>();
		for (Tag tag : tags) {
			predicates.add(cb.equal(join.get(QuestionTag_.tag).get(Tag_.name), tag.getName()));
		}
		query.select(root)
		        .where(cb.and(predicates.toArray(new Predicate[] {}))).distinct(true);
		TypedQuery<Question> q = em.createQuery(query);
		return q.getResultList();
	}

	@GET
	@Path("{id}/tags")
	@Produces({ "application/json" })
	public Collection<Tag> getQuestionTags(@PathParam("id") Integer id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Question> query = cb.createQuery(Question.class);
		Root<Question> root = query.from(Question.class);
		root.fetch(Question_.questionTags);
		query.select(root).where(cb.equal(root.get(Question_.id), id));
		TypedQuery<Question> q = em.createQuery(query);
		Question question = q.getSingleResult();
		List<Tag> tags = new ArrayList<Tag>(question.getQuestionTags().size());
		for (QuestionTag questionTag : question.getQuestionTags()) {
			tags.add(questionTag.getTag());
		}
		return tags;
	}

	@GET
	@Path("{id}/tagsquery")
	@Produces({ "application/json" })
	public Collection<Tag> getQuestionTagsQuery(@PathParam("id") Integer id,
	        @QueryParam("query") String queryString) {
		if (queryString == null || queryString.length() == 0)
		    return new ArrayList<Tag>();
		String pattern = "%" + queryString + "%";

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<QuestionTag> query = cb.createQuery(QuestionTag.class);
		Root<QuestionTag> root = query.from(QuestionTag.class);
		Join<QuestionTag, Tag> tag = root.join(QuestionTag_.tag);

		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.like(tag.get(Tag_.name), pattern));

		query.select(root)
		        .where(cb.and(predicates.toArray(new Predicate[] {})));

		TypedQuery<QuestionTag> q = em.createQuery(query);
		List<QuestionTag> results = q.getResultList();
		List<Tag> tags = new ArrayList<Tag>(results.size());
		for (QuestionTag questionTag : results) {
			tags.add(questionTag.getTag());
		}
		return tags;
	}

	@PUT
	@Path("{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Question update(QuestionDTO questionDTO) {
		logger.info("Updating Question");
		Question updatedQuestion = questionDTO.getQuestion();
		List<Tag> tags = questionDTO.getTags();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Question> query = cb.createQuery(Question.class);
		Root<Question> root = query.from(Question.class);
		root.fetch(Question_.questionTags);
		query.select(root).where(
		        cb.equal(root.get(Question_.id), updatedQuestion.getId()));
		TypedQuery<Question> q = em.createQuery(query);
		Question question = q.getSingleResult();

		question.setSubject(updatedQuestion.getSubject());
		question.setContent(updatedQuestion.getContent());
		question.setDescription(updatedQuestion.getDescription());
		question.setQuestionAnswer(updatedQuestion.getQuestionAnswer());
		question.setQuestionType(updatedQuestion.getQuestionType());
		question.setTotalScore(updatedQuestion.getTotalScore());

		question.setQuestionDetails(updatedQuestion.getQuestionDetails());
		
		Set<QuestionTag> originalQuestionTags = new HashSet<QuestionTag>(question.getQuestionTags());
		
		Set<QuestionTag> questionTagsSet = new HashSet<QuestionTag>();
		for (Tag tag : tags) {
			QuestionTag qt = new QuestionTag();
			qt.setTag(tag);
			qt.setQuestion(updatedQuestion);
			questionTagsSet.add(qt);
		}
		originalQuestionTags.retainAll(questionTagsSet);
		originalQuestionTags.addAll(questionTagsSet);
		
		question.setQuestionTags(new ArrayList<QuestionTag>(originalQuestionTags));

		return dao.update(question);
	}

	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		dao.delete(dao.get(Question.class, id));
	}

}
