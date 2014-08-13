package pl.kozervar.exap.model.person;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import pl.kozervar.exap.model.Versionable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(
        name = "person_answer")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.UUIDGenerator.class,
        property = "@PersonAnswer_UUID")
public class PersonAnswer extends Versionable{

    private static final long serialVersionUID = -6641268832816618255L;

	@ManyToOne(
	        optional = false,
	        targetEntity = PersonExam.class,
	        cascade = { CascadeType.DETACH, CascadeType.MERGE,
	                CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(
	        name = "person_exam_id",
	        referencedColumnName = "id")
	private PersonExam personExam;
	
	@Column(
	        name = "question_id")
	private Long questionId;

	@Column(
	        name = "question_detail_id")
	private Long questionDetaildId;
	
	@Column(
	        name = "answer", columnDefinition="TEXT")
	private String answer;

	
    public Long getQuestionId() {
    	return questionId;
    }

	
    public void setQuestionId(Long questionId) {
    	this.questionId = questionId;
    }

	
    public Long getQuestionDetaildId() {
    	return questionDetaildId;
    }

	
    public void setQuestionDetaildId(Long questionDetaildId) {
    	this.questionDetaildId = questionDetaildId;
    }

	
    public String getAnswer() {
    	return answer;
    }

	
    public void setAnswer(String answer) {
    	this.answer = answer;
    }
    
    public PersonExam getPersonExam() {
	    return personExam;
    }


	public void setPersonExam(PersonExam personExam) {
	    this.personExam = personExam;
    }


	@Override
    public boolean equals( final Object other )
    {
        if( !(other instanceof PersonAnswer) )
            return false;
        PersonAnswer castOther = (PersonAnswer)other;
        return new EqualsBuilder().append( questionId, castOther.questionId )
            .append( questionDetaildId, castOther.questionDetaildId ).append( answer, castOther.answer )
            .isEquals();
    }


    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append( questionId ).append( questionDetaildId ).append( answer )
            .toHashCode();
    }
	
}
