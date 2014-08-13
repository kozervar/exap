
package pl.kozervar.exap.rest.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import pl.kozervar.exap.dao.DAO;
import pl.kozervar.exap.model.ExamPaperQuestion;
import pl.kozervar.exap.model.exam.ExamPaper;
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
	public ExamData savePersonalData(PersonalData personalData) {
		logger.debug("Personal data recived");
		Random randomGenerator = new Random();
		List<ExamPaper> examPapers = dao.getAll(ExamPaper.class);
//		int index = randomGenerator.nextInt(examPapers.size());
		int index = 0;

		ExamPaper randomExam = examPapers.get(index);
		ExamData examData = prepareExamData(randomExam);
		logger.debug("Examination data generated.");
		return examData;
	}

	private ExamData prepareExamData(ExamPaper examPaper) {
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
		return new ExamData(examPaper.getId(), examPaper.getName(),
		        examPaper.getDescription(), examPaper.getExamType(), questions);
	}
}
