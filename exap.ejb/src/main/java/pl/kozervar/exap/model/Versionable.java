
package pl.kozervar.exap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@MappedSuperclass
public abstract class Versionable extends Identifiable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(
	        nullable = false)
	@Version
	private Long version = 1l;

	public Versionable() {

	}


	public Long getVersion() {
		return this.version;
	}


	public void setVersion(Long version) {
		this.version = version;
	}


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof Versionable)) return false;
		Versionable castOther = (Versionable) other;
		return new EqualsBuilder().append(version, castOther.version)
		        .isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(version).toHashCode();
	}

}
