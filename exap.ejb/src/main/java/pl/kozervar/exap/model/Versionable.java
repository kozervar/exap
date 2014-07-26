
package pl.kozervar.exap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

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

}
