
package pl.kozervar.exap.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.UUIDGenerator.class,
        property = "@ExamPaperQuestion_UUID")
public class ExamPaperQuestion extends Identifiable {

	private static final long serialVersionUID = 4053835661995721531L;

	@ManyToOne(
	        optional = false,
	        targetEntity = ExamPaper.class,
	        cascade = { CascadeType.DETACH, CascadeType.MERGE,
	                CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(
	        name = "exam_paper_id",
	        referencedColumnName = "id")
	private ExamPaper examPaper;

	@ManyToOne(
	        optional = false,
	        targetEntity = Question.class)
	@JoinColumn(
	        name = "question_id",
	        referencedColumnName = "id")
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


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
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
		return new EqualsBuilder()
		        .append(getExamPaper(), castOther.getExamPaper())
		        .append(getQuestion(), castOther.getQuestion()).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getExamPaper())
		        .append(getQuestion()).toHashCode();
	}
}
