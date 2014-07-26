
package pl.kozervar.exap.model.question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pl.kozervar.exap.model.Informable;

@Entity
@Table(
        name = "question_answer")
public class QuestionAnswer extends Informable {

	private static final long serialVersionUID = 1L;

	@Column(
	        name = "binary_value")
	private Integer binaryValue;

	@Column(
	        name = "short_text_value")
	private String shortTextValue;

	@Column(
	        name = "long_text_value")
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
}
