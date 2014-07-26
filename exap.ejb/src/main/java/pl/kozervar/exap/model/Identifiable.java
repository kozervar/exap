
package pl.kozervar.exap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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


	public Long getId() {
		return this.id;
	}


	public void setId(Long id) {
		this.id = id;
	}

}
