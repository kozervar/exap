package pl.kozervar.exap.model.question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import pl.kozervar.exap.model.Identifiable;

@Entity
@Table(
        name = "question_detail")
public class QuestionDetail extends Identifiable {

    private static final long serialVersionUID = -7212424997926199361L;

    @Column(
	        name = "content", columnDefinition="TEXT")
	private String content;

	@Column(
	        name = "sort_order")
	private Integer sortOrder;

	@ManyToOne(
	        fetch = FetchType.LAZY)
	@JoinColumn(
	        name = "question_id")
	private Question question;
	
	@Column(
	        name = "score")
	private Double score;

	@Transient
	private Boolean checked;


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Integer getSortOrder() {
		return sortOrder;
	}


	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}


	public Question getQuestionHeader() {
		return question;
	}


	public void setQuestionHeader(Question question) {
		this.question = question;
	}


	public Boolean getChecked() {
		return checked;
	}


	public void setChecked(Boolean checked) {
		this.checked = checked;
	}


	public Double getScore() {
	    return score;
    }


	public void setScore(Double score) {
	    this.score = score;
    }


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof QuestionDetail)) return false;
		QuestionDetail castOther = (QuestionDetail) other;
		return new EqualsBuilder().append(content, castOther.content)
		        .append(sortOrder, castOther.sortOrder)
		        .append(score, castOther.score).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(content).append(sortOrder)
		        .append(score).toHashCode();
	}

}
