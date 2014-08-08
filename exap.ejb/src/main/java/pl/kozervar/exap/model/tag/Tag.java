
package pl.kozervar.exap.model.tag;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import pl.kozervar.exap.model.Identifiable;

@Entity
@Table(
        name = "tag",
        uniqueConstraints = { @UniqueConstraint(
                columnNames = { "name" }) })
public class Tag extends Identifiable {

	private static final long serialVersionUID = 1668069179527572580L;

	@Column(
	        name = "name")
	private String name;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof Tag)) return false;
		Tag castOther = (Tag) other;
		return new EqualsBuilder().append(name, castOther.name).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).toHashCode();
	}

}
