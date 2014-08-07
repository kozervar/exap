
package pl.kozervar.exap.model.tag;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import pl.kozervar.exap.model.Versionable;
import pl.kozervar.exap.model.question.Question;

@Entity
@Table(
        name = "question_tag",
        uniqueConstraints = { @UniqueConstraint(
                columnNames = { "question_id", "tag_id" }) })
@JsonIdentityInfo(
        generator = ObjectIdGenerators.UUIDGenerator.class,
        property = "@TagQuestion_UUID")
public class QuestionTag extends Versionable {

	private static final long serialVersionUID = 4506953035917424455L;

	@ManyToOne(
	        optional = false,
	        targetEntity = Question.class)
	@JoinColumn(
	        name = "question_id")
	private Question question;

	@ManyToOne(
	        optional = false,
	        targetEntity = Tag.class,
	        cascade = { CascadeType.DETACH, CascadeType.MERGE,
	                CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(
	        name = "tag_id")
	private Tag tag;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}


	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof QuestionTag)) return false;
		QuestionTag castOther = (QuestionTag) other;
		return new EqualsBuilder().append(tag, castOther.tag)
		        .append(question, castOther.question).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(tag).append(question).toHashCode();
	}


}
