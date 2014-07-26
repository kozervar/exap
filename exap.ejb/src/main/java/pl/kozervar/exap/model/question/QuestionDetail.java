
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

	private static final long serialVersionUID = 1L;

	@Column(
	        name = "content")
	private String content;

	@Column(
	        name = "sort_order")
	private Integer sortOrder;

	@ManyToOne(
	        fetch = FetchType.LAZY)
	@JoinColumn(
	        name = "question_header_id")
	private QuestionHeader questionHeader;

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


	public QuestionHeader getQuestionHeader() {
		return questionHeader;
	}


	public void setQuestionHeader(QuestionHeader questionHeader) {
		this.questionHeader = questionHeader;
	}


	public Boolean getChecked() {
		return checked;
	}


	public void setChecked(Boolean checked) {
		this.checked = checked;
	}


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof QuestionDetail)) return false;
		QuestionDetail castOther = (QuestionDetail) other;
		return new EqualsBuilder().append(content, castOther.content)
		        .append(sortOrder, castOther.sortOrder)
		        .append(questionHeader, castOther.questionHeader)
		        .append(checked, castOther.checked).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(content).append(sortOrder)
		        .append(questionHeader).append(checked).toHashCode();
	}

}
