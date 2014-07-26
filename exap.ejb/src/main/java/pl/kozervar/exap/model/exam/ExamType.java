
package pl.kozervar.exap.model.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import pl.kozervar.exap.model.Identifiable;

@Entity
@Table(
        name = "exam_type")
public class ExamType extends Identifiable {

	private static final long serialVersionUID = 1L;

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
		if (!(other instanceof ExamType)) return false;
		ExamType castOther = (ExamType) other;
		return new EqualsBuilder().append(name, castOther.name)
		        .append(description, castOther.description).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(description)
		        .toHashCode();
	}


	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", name)
		        .append("description", description).toString();
	}
}
