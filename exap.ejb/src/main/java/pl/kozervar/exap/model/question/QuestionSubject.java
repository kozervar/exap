
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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import pl.kozervar.exap.model.ExamPaperQuestionSubject;
import pl.kozervar.exap.model.Informable;

@Entity
@Table(
        name = "question_subject")
public class QuestionSubject extends Informable {

	private static final long serialVersionUID = 1L;

	@Column(
	        name = "content")
	private String content;

	@Column(
	        name = "total_score")
	private Long totalScore;

	@ManyToOne(
	        fetch = FetchType.EAGER)
	@JoinColumn(
	        name = "question_type_id")
	private QuestionType questionType;

	@OneToMany(
	        fetch = FetchType.EAGER,
	        cascade = CascadeType.ALL,
	        mappedBy = "questionSubject")
	@OrderBy("id ASC")
	private Set<QuestionHeader> questionHeaders = new HashSet<QuestionHeader>();

	@OneToMany(
	        mappedBy = "questionSubject",
	        fetch = FetchType.LAZY,
	        cascade = { CascadeType.PERSIST })
	private Set<ExamPaperQuestionSubject> examPaperQuestionSubjects;


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Long getTotalScore() {
		return totalScore;
	}


	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}


	public QuestionType getQuestionType() {
		return questionType;
	}


	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}


	public Set<QuestionHeader> getQuestionHeaders() {
		return questionHeaders;
	}


	public void setQuestionHeaders(Set<QuestionHeader> questionHeaders) {
		this.questionHeaders = questionHeaders;
	}


	public Set<ExamPaperQuestionSubject> getExamPaperQuestionSubjects() {
		return examPaperQuestionSubjects;
	}


	public void setExamPaperQuestionSubjects(
	        Set<ExamPaperQuestionSubject> examPaperQuestionSubjects) {
		this.examPaperQuestionSubjects = examPaperQuestionSubjects;
	}


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof QuestionSubject)) return false;
		QuestionSubject castOther = (QuestionSubject) other;
		return new EqualsBuilder().append(content, castOther.content)
		        .append(totalScore, castOther.totalScore)
		        .append(questionType, castOther.questionType).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(content).append(totalScore)
		        .append(questionType).toHashCode();
	}


}
