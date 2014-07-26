
package pl.kozervar.exap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.kozervar.exap.model.exam.ExamPaper;
import pl.kozervar.exap.model.question.QuestionSubject;

@Entity
@IdClass(ExamPaperQuestionSubjectPK.class)
@Table(
        name = "exam_paper_question_subject")
public class ExamPaperQuestionSubject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(
	        optional = false,
	        targetEntity = ExamPaper.class)
	private ExamPaper examPaper;

	@Id
	@ManyToOne(
	        optional = false,
	        targetEntity = QuestionSubject.class)
	private QuestionSubject questionSubject;

	@Column(
	        name = "sort_order")
	private Integer sortOrder;


	public ExamPaper getExamPaper() {
		return examPaper;
	}


	public void setExamPaper(ExamPaper examPaper) {
		this.examPaper = examPaper;
	}


	public QuestionSubject getQuestionSubject() {
		return questionSubject;
	}


	public void setQuestionSubject(QuestionSubject questionSubject) {
		this.questionSubject = questionSubject;
	}


	public Integer getSortOrder() {
		return sortOrder;
	}


	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
}
