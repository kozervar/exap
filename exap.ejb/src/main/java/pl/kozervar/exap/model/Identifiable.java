
package pl.kozervar.exap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@MappedSuperclass
public abstract class Identifiable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(
	        name = "ID",
	        unique = true,
	        nullable = false)
	@Id
	@GeneratedValue
	private Long id;

	public Identifiable() {

	}


	public Identifiable(Identifiable identifiable) {
	    this.id = identifiable.getId();
    }


	public Long getId() {
		return this.id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof Identifiable)) return false;
		Identifiable castOther = (Identifiable) other;
		return new EqualsBuilder().isEquals();
	}


	private transient int hashCode;

	@Override
	public int hashCode() {
		if (hashCode == 0) {
			hashCode = new HashCodeBuilder().toHashCode();
		}
		return hashCode;
	}

}
