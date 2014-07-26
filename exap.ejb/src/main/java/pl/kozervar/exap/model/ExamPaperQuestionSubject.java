
package pl.kozervar.exap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import pl.kozervar.exap.model.exam.ExamPaper;
import pl.kozervar.exap.model.question.QuestionSubject;

@Entity
@Table(
        name = "exam_paper_question_subject",
        uniqueConstraints = { @UniqueConstraint(
                columnNames = { "exam_paper_id", "question_subject_id" }) })
public class ExamPaperQuestionSubject extends Identifiable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(
	        optional = false,
	        targetEntity = ExamPaper.class)
	@JoinColumn(
	        name = "exam_paper_id")
	private ExamPaper examPaper;

	@ManyToOne(
	        optional = false,
	        targetEntity = QuestionSubject.class)
	@JoinColumn(
	        name = "question_subject_id")
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


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof ExamPaperQuestionSubject)) return false;
		ExamPaperQuestionSubject castOther = (ExamPaperQuestionSubject) other;
		return new EqualsBuilder().append(examPaper, castOther.examPaper)
		        .append(questionSubject, castOther.questionSubject)
		        .append(sortOrder, castOther.sortOrder).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(examPaper).append(questionSubject)
		        .append(sortOrder).toHashCode();
	}
}
