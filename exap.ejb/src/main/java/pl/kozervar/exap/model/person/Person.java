
package pl.kozervar.exap.model.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import pl.kozervar.exap.model.Informable;

@Entity
@Table(
        name = "person")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.UUIDGenerator.class,
        property = "@Person_UUID")
public class Person extends Informable {

	private static final long serialVersionUID = -4273328911593134195L;

	public Person() {
	}

	public Person(Person person) {
		super(person);
		this.name = person.getName();
		this.surname = person.getSurname();
		this.email = person.getSurname();
		this.phoneNumber = person.getPhoneNumber();
		this.accepted = person.getAccepted();
	}

	@Column(
	        name = "name", nullable = false)
	private String name;

	@Column(
	        name = "surname", nullable = false)
	private String surname;

	@Column(
	        name = "email", nullable = false)
	private String email;
	@Column(
	        name = "phone_number")
	private String phoneNumber;

	@Column(
	        name = "accepted", nullable = false)
	private Boolean accepted = false;

	
    public String getName() {
    	return name;
    }

	
    public void setName(String name) {
    	this.name = name;
    }

	
    public String getSurname() {
    	return surname;
    }

	
    public void setSurname(String surname) {
    	this.surname = surname;
    }

	
    public String getEmail() {
    	return email;
    }

	
    public void setEmail(String email) {
    	this.email = email;
    }

	
    public String getPhoneNumber() {
    	return phoneNumber;
    }

	
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }

	
    public Boolean getAccepted() {
    	return accepted;
    }

	
    public void setAccepted(Boolean accepted) {
    	this.accepted = accepted;
    }

	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof Person)) return false;
		Person castOther = (Person) other;
		return new EqualsBuilder().append(name, castOther.name)
		        .append(surname, castOther.surname)
		        .append(email, castOther.email)
		        .append(phoneNumber, castOther.phoneNumber)
		        .append(accepted, castOther.accepted).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(surname).append(email)
		        .append(phoneNumber).append(accepted).toHashCode();
	}

	@Override
    public String toString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("Person [name=");
	    builder.append(name);
	    builder.append(", surname=");
	    builder.append(surname);
	    builder.append(", email=");
	    builder.append(email);
	    builder.append(", phoneNumber=");
	    builder.append(phoneNumber);
	    builder.append(", accepted=");
	    builder.append(accepted);
	    builder.append("]");
	    return builder.toString();
    }

}
