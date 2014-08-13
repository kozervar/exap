
package pl.kozervar.exap.model.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import pl.kozervar.exap.model.Informable;
import pl.kozervar.exap.model.exam.ExamPaper;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(
        name = "person_exam")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.UUIDGenerator.class,
        property = "@PersonExam_UUID")
public class PersonExam extends Informable {

	private static final long serialVersionUID = -4273328911593134195L;

	@Column( 
	        name = "sha_token", nullable = false)
	private String shaToken;
	
	@OneToOne(
	        optional = false,
	        targetEntity = ExamPaper.class,
	        cascade = { CascadeType.DETACH, CascadeType.MERGE,
	                CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(
	        name = "exam_paper_id",
	        referencedColumnName = "id")
	private ExamPaper examPaper;
	
	@OneToOne(
	        optional = false,
	        targetEntity = Person.class,
	        cascade = { CascadeType.DETACH, CascadeType.MERGE,
	                CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(
	        name = "person_id",
	        referencedColumnName = "id")
	private Person person;
	
	@OneToMany(
	        mappedBy = "personExam",
	        fetch = FetchType.EAGER,
	        orphanRemoval = true,
	        cascade = CascadeType.ALL)
	@OrderBy(value="sortOrder")
	private Set<PersonAnswer> personAnswers;
	
	public PersonExam() {
	}

	
    public String getShaToken() {
    	return shaToken;
    }

	
    public void setShaToken(String shaToken) {
    	this.shaToken = shaToken;
    }

	
    public ExamPaper getExamPaper() {
    	return examPaper;
    }

	
    public void setExamPaper(ExamPaper examPaper) {
    	this.examPaper = examPaper;
    }

	
    public List<PersonAnswer> getPersonAnswers() {
		if (personAnswers == null) {
			return new ArrayList<PersonAnswer>(0);
		}
		else {
			return new ArrayList<PersonAnswer>(personAnswers);
		}
    }

	
    public void setPersonAnswers(List<PersonAnswer> personAnswers) {
		if (personAnswers == null) {
			return;
		}

		if (this.personAnswers == null) {
			this.personAnswers = new HashSet<PersonAnswer>(
			        personAnswers);
		}
		else {
			this.personAnswers.clear();
			this.personAnswers.addAll(personAnswers);
		}

		for (PersonAnswer personAnswer : this.personAnswers) {
			personAnswer.setPersonExam(this);
		}
    }
    
    public Person getPerson() {
	    return person;
    }


	public void setPerson(Person person) {
	    this.person = person;
    }


	@Override
    public boolean equals( final Object other )
    {
        if( !(other instanceof PersonExam) )
            return false;
        PersonExam castOther = (PersonExam)other;
        return new EqualsBuilder().append( shaToken, castOther.shaToken ).isEquals();
    }


    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( shaToken ).toHashCode();
    }

}
