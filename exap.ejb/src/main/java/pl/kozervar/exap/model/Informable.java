
package pl.kozervar.exap.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@MappedSuperclass
public abstract class Informable extends Versionable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic
	private String creationUser;

	@Basic
	private Date creationDate;

	@Basic
	private Date updateDate;

	@Basic
	private String updateUser;

	public Informable() {

	}


	public Informable(Informable informable) {
	    super(informable);
	    this.creationUser = informable.getCreationUser();
	    this.creationDate = informable.getCreationDate();
	    this.updateDate = informable.getUpdateDate();
	    this.updateUser = informable.getUpdateUser();
    }


	public String getCreationUser() {
		return this.creationUser;
	}


	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}


	public Date getCreationDate() {
		return this.creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Date getUpdateDate() {
		return this.updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public String getUpdateUser() {
		return this.updateUser;
	}


	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof Informable)) return false;
		Informable castOther = (Informable) other;
		return new EqualsBuilder().append(creationUser, castOther.creationUser)
		        .append(creationDate, castOther.creationDate)
		        .append(updateDate, castOther.updateDate)
		        .append(updateUser, castOther.updateUser).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(creationUser).append(creationDate)
		        .append(updateDate).append(updateUser).toHashCode();
	}

}
