
package pl.kozervar.exap.rest.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;

import pl.kozervar.exap.dao.DAO;
import pl.kozervar.exap.model.ExamPaperQuestion;
import pl.kozervar.exap.model.exam.ExamPaper;
import pl.kozervar.exap.model.person.Person;
import pl.kozervar.exap.model.person.PersonAnswer;
import pl.kozervar.exap.model.person.PersonExam;
import pl.kozervar.exap.model.question.Question;
import pl.kozervar.exap.model.question.QuestionDetail;

@Stateless
@Path("/exam")
public class ExamFormRESTFacade {

	@EJB
	private DAO dao;

	@Inject
	private EntityManager em;

	@Inject
	private Logger logger;

	@POST
	@Path("personalData")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public ExamData savePersonalData(Person personalData) {
		Person person = dao.create(personalData);
		String shaToken = DigestUtils.sha256Hex(person.toString() + UUID.randomUUID());
		logger.debug("Personal data saved: " + shaToken);
		
		Random randomGenerator = new Random();
		List<ExamPaper> examPapers = em.createNamedQuery(ExamPaper.SELECT_ACTIVE, ExamPaper.class).getResultList();
		int index = randomGenerator.nextInt(examPapers.size());

		ExamPaper randomExam = examPapers.get(index);
		
		PersonExam personExam = new PersonExam();
		personExam.setShaToken(shaToken);
		personExam.setPerson(person);
		personExam.setExamPaper(randomExam);
		dao.create(personExam);
		
		ExamData examData = prepareExamData(shaToken, randomExam);
		logger.debug("Examination data generated.");
		return examData;
	}
	
	@POST
	@Path("answer")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public void saveAnswer(SubmittedQuestionAnswer questionAnswer) {
		
		String shaToken = questionAnswer.getToken();
		String answer = questionAnswer.getAnswer();
		Long questionId = questionAnswer.getQuestionId();
		QuestionAnswerData selectedAnswer = questionAnswer.getSelectedAnswer();
		List<QuestionAnswerData> selectedAnswers = questionAnswer.getSelectedAnswers();
		
		TypedQuery<PersonExam> personExamQuery = em.createNamedQuery(PersonExam.SELECT_BY_TOKEN, PersonExam.class);
		personExamQuery.setParameter("p0", shaToken);
		PersonExam personExam = personExamQuery.getSingleResult();
		if(answer != null && selectedAnswer == null && selectedAnswers == null)
		{
			PersonAnswer personAnswer = new PersonAnswer();
			personAnswer.setPersonExam(personExam);
			personAnswer.setQuestionId(questionId);
			personAnswer.setAnswer(answer);
			dao.create(personAnswer);
			logger.debug("Person answer created. Single answer added.");
		}
		if(answer == null && selectedAnswer != null && selectedAnswers == null)
		{
			PersonAnswer personAnswer = new PersonAnswer();
			personAnswer.setPersonExam(personExam);
			personAnswer.setQuestionId(questionId);
			personAnswer.setQuestionDetaildId(selectedAnswer.getId());
			dao.create(personAnswer);
			logger.debug("Person answer created. Answer with question detail added.");
		}
		if(answer == null && selectedAnswer == null && selectedAnswers != null)
		{
			int count = 0;
			for (QuestionAnswerData questionAnswerData : selectedAnswers) {
				PersonAnswer personAnswer = new PersonAnswer();
				personAnswer.setPersonExam(personExam);
				personAnswer.setQuestionId(questionId);
				personAnswer.setQuestionDetaildId(questionAnswerData.getId());
				dao.create(personAnswer);
				count++;
	        }
			logger.debug("Person answer created. Answer with "+count+" question details added.");
		}
	}

	private ExamData prepareExamData(String shaToken, ExamPaper examPaper) {
		List<ExamPaperQuestion> examPaperQuestions = examPaper
		        .getExamPaperQuestions();
		List<QuestionData> questions = new ArrayList<QuestionData>();
		Collections.sort(examPaperQuestions,
		        new Comparator<ExamPaperQuestion>() {

			        @Override
			        public int compare(ExamPaperQuestion o1,
			                ExamPaperQuestion o2) {
			        	Integer s1 = o1.getSortOrder();
			        	Integer s2 = o2.getSortOrder();
				        return s1 < s2 ? -1 : s1 > s2 ? 1 : 0;
			        }
		        });
		for (ExamPaperQuestion examPaperQuestion : examPaperQuestions) {
			Question q = examPaperQuestion.getQuestion();
			List<QuestionDetail> questionDetails = q.getQuestionDetails();
			List<QuestionAnswerData> questionAnswers = new ArrayList<QuestionAnswerData>(
			        questionDetails.size());
			for (QuestionDetail qd : questionDetails) {
				QuestionAnswerData qad = new QuestionAnswerData(qd.getId(),
				        qd.getSortOrder(), qd.getContent());
				questionAnswers.add(qad);
			}
			Collections.sort(questionAnswers,
			        new Comparator<QuestionAnswerData>() {

				        @Override
				        public int compare(QuestionAnswerData o1,
				        		QuestionAnswerData o2) {
				        	Integer s1 = o1.getSortOrder();
				        	Integer s2 = o2.getSortOrder();
					        return s1 < s2 ? -1 : s1 > s2 ? 1 : 0;
				        }
			        });
			QuestionData questionData = new QuestionData(q.getId(),
			        q.getSubject(), q.getDescription(), q.getContent(),
			        q.getQuestionType(), questionAnswers);
			questions.add(questionData);
		}
		return new ExamData(shaToken, examPaper.getId(), examPaper.getName(),
		        examPaper.getDescription(), examPaper.getExamType(), questions);
	}
}
