
package pl.kozervar.exap.model.question;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import pl.kozervar.exap.model.Informable;

@Entity
@Table(
        name = "question_header")
public class QuestionHeader extends Informable {

	private static final long serialVersionUID = 1L;

	@Column(
	        name = "description")
	private String description;

	@Column(
	        name = "score")
	private Long score;

	@ManyToOne(
	        fetch = FetchType.LAZY)
	@JoinColumn(
	        name = "question_subject_id")
	private QuestionSubject questionSubject;

	@ManyToOne(
	        fetch = FetchType.EAGER)
	@JoinColumn(
	        name = "question_type_id")
	private QuestionType questionType;

	@ManyToOne(
	        fetch = FetchType.EAGER,
	        cascade = CascadeType.ALL)
	@JoinColumn(
	        name = "question_answer_id")
	private QuestionAnswer questionAnswer;

	@OneToMany(
	        fetch = FetchType.EAGER,
	        cascade = CascadeType.ALL,
	        mappedBy = "questionHeader")
	@OrderBy("sortOrder ASC")
	private Set<QuestionDetail> questionDetails = new HashSet<QuestionDetail>();


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Long getScore() {
		return score;
	}


	public void setScore(Long score) {
		this.score = score;
	}


	public QuestionSubject getQuestionSubject() {
		return questionSubject;
	}


	public void setQuestionSubject(QuestionSubject questionSubject) {
		this.questionSubject = questionSubject;
	}


	public QuestionType getQuestionType() {
		return questionType;
	}


	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}


	public QuestionAnswer getQuestionAnswer() {
		return questionAnswer;
	}


	public void setQuestionAnswer(QuestionAnswer questionAnswer) {
		this.questionAnswer = questionAnswer;
	}


	public Set<QuestionDetail> getQuestionDetails() {
		return questionDetails;
	}


	public void setQuestionDetails(Set<QuestionDetail> questionDetails) {
		this.questionDetails = questionDetails;
	}


}
