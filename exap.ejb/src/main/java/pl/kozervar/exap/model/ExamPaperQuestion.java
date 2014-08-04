
package pl.kozervar.exap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import pl.kozervar.exap.model.exam.ExamPaper;
import pl.kozervar.exap.model.question.Question;

@Entity
@Table(
        name = "exam_paper_question",
        uniqueConstraints = { @UniqueConstraint(
                columnNames = { "exam_paper_id", "question_id" }) })
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@UUID")
public class ExamPaperQuestion extends Identifiable {

    private static final long serialVersionUID = 4053835661995721531L;

	@ManyToOne(
	        optional = false,
	        targetEntity = ExamPaper.class)
	@JoinColumn(
	        name = "exam_paper_id")
	private ExamPaper examPaper;

	@ManyToOne(
	        optional = false,
	        targetEntity = Question.class)
	@JoinColumn(
	        name = "question_id")
	private Question question;

	@Column(
	        name = "sort_order")
	private Integer sortOrder;


	public ExamPaper getExamPaper() {
		return examPaper;
	}


	public void setExamPaper(ExamPaper examPaper) {
		this.examPaper = examPaper;
	}


	public Question getQuestionSubject() {
		return question;
	}


	public void setQuestionSubject(Question question) {
		this.question = question;
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
		if (!(other instanceof ExamPaperQuestion)) return false;
		ExamPaperQuestion castOther = (ExamPaperQuestion) other;
		return new EqualsBuilder().append(examPaper, castOther.examPaper)
		        .append(question, castOther.question)
		        .append(sortOrder, castOther.sortOrder).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(examPaper).append(question)
		        .append(sortOrder).toHashCode();
	}
}
