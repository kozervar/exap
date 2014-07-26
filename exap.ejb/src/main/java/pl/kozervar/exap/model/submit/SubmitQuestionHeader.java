
package pl.kozervar.exap.model.submit;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import pl.kozervar.exap.model.Informable;

@Entity
@Table(
        name = "submit_question_header")
public class SubmitQuestionHeader extends Informable {

	private static final long serialVersionUID = 1L;

	@Column(
	        name = "comment")
	private String comment;

	@Column(
	        name = "obtained_score")
	private Long obtainedScore;

	@Column(
	        name = "review_date")
	private Date reviewDate;

	@ManyToOne(
	        fetch = FetchType.EAGER,
	        cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(
	        name = "submit_question_answer_id")
	private SubmitQuestionAnswer submitQuestionAnswer;


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Long getObtainedScore() {
		return obtainedScore;
	}


	public void setObtainedScore(Long obtainedScore) {
		this.obtainedScore = obtainedScore;
	}


	public Date getReviewDate() {
		return reviewDate;
	}


	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}


	public SubmitQuestionAnswer getSubmitQuestionAnswer() {
		return submitQuestionAnswer;
	}


	public void setSubmitQuestionAnswer(
	        SubmitQuestionAnswer submitQuestionAnswer) {
		this.submitQuestionAnswer = submitQuestionAnswer;
	}


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof SubmitQuestionHeader)) return false;
		SubmitQuestionHeader castOther = (SubmitQuestionHeader) other;
		return new EqualsBuilder().append(comment, castOther.comment)
		        .append(obtainedScore, castOther.obtainedScore)
		        .append(reviewDate, castOther.reviewDate)
		        .append(submitQuestionAnswer, castOther.submitQuestionAnswer)
		        .isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(comment).append(obtainedScore)
		        .append(reviewDate).append(submitQuestionAnswer).toHashCode();
	}

}
