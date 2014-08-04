
package pl.kozervar.exap.model.question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import pl.kozervar.exap.model.Identifiable;

@Entity
@Table(
        name = "question_type")
public class QuestionType extends Identifiable {

    private static final long serialVersionUID = 4968827308076301052L;

	@Column(
	        name = "name")
	private String name;

	@Column(
	        name = "description")
	private String description;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof QuestionType)) return false;
		QuestionType castOther = (QuestionType) other;
		return new EqualsBuilder().append(name, castOther.name)
		        .append(description, castOther.description).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(description)
		        .toHashCode();
	}
}
