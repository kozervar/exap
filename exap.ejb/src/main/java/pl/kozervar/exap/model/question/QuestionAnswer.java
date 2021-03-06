
package pl.kozervar.exap.model.question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import pl.kozervar.exap.model.Informable;

@Entity
@Table(
        name = "question_answer")
public class QuestionAnswer extends Informable {


    private static final long serialVersionUID = 7547040375533609739L;

	@Column(
	        name = "binary_value")
	private Integer binaryValue;

	@Column(
	        name = "short_text_value")
	private String shortTextValue;

	@Column(
	        name = "long_text_value", columnDefinition="TEXT")
	private String longTextValue;

	@Column(
	        name = "comment")
	private String comment;


	public Integer getBinaryValue() {
		return binaryValue;
	}


	public void setBinaryValue(Integer binaryValue) {
		this.binaryValue = binaryValue;
	}


	public String getShortTextValue() {
		return shortTextValue;
	}


	public void setShortTextValue(String shortTextValue) {
		this.shortTextValue = shortTextValue;
	}


	public String getLongTextValue() {
		return longTextValue;
	}


	public void setLongTextValue(String longTextValue) {
		this.longTextValue = longTextValue;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof QuestionAnswer)) return false;
		QuestionAnswer castOther = (QuestionAnswer) other;
		return new EqualsBuilder().append(binaryValue, castOther.binaryValue)
		        .append(shortTextValue, castOther.shortTextValue)
		        .append(longTextValue, castOther.longTextValue)
		        .append(comment, castOther.comment).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(binaryValue).append(shortTextValue)
		        .append(longTextValue).append(comment).toHashCode();
	}
}
