
package pl.kozervar.exap.model;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;

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

}
