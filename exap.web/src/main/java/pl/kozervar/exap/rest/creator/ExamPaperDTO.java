
package pl.kozervar.exap.rest.creator;

import java.io.Serializable;
import java.util.List;

import pl.kozervar.exap.model.ExamPaperQuestion;
import pl.kozervar.exap.model.exam.ExamPaper;
import pl.kozervar.exap.model.question.Question;


public class ExamPaperDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private ExamPaper examPaper;
	private List<Question> questions;
	private List<ExamPaperQuestion> examPaperQuestion;

	public ExamPaper getExamPaper() {
		return examPaper;
	}

	public void setExamPaper(ExamPaper examPaper) {
		this.examPaper = examPaper;
	}

	public List<ExamPaperQuestion> getExamPaperQuestion() {
	    return examPaperQuestion;
    }

	public void setExamPaperQuestion(List<ExamPaperQuestion> examPaperQuestion) {
	    this.examPaperQuestion = examPaperQuestion;
    }

	public List<Question> getQuestions() {
	    return questions;
    }

	public void setQuestions(List<Question> questions) {
	    this.questions = questions;
    }

}
