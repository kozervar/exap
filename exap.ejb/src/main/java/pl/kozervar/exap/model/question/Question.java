
package pl.kozervar.exap.model.question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import pl.kozervar.exap.model.ExamPaperQuestion;
import pl.kozervar.exap.model.Informable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(
        name = "question")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.UUIDGenerator.class,
        property = "@Question_UUID")
public class Question extends Informable {

	private static final long serialVersionUID = -5070667742526477724L;

	public Question() {
		this.getClass();
	}

	public Question(Question question) {
		super(question);
		this.totalScore = question.getTotalScore();
		this.subject = question.getSubject();
		this.description = question.getDescription();
		this.content = question.getContent();
		this.questionType = question.getQuestionType();
		this.questionAnswer = question.getQuestionAnswer();
	}


	@Column(
	        name = "total_score")
	private Double totalScore;

	@Column(
	        name = "subject",
	        columnDefinition = "TEXT")
	private String subject;

	@Column(
	        name = "description",
	        columnDefinition = "TEXT")
	private String description;

	@Column(
	        name = "content",
	        columnDefinition = "TEXT")
	private String content;

	@ManyToOne(
	        fetch = FetchType.EAGER)
	@JoinColumn(
	        name = "question_type_id")
	private QuestionType questionType;

	@OneToMany(
	        fetch = FetchType.EAGER,
	        cascade = { CascadeType.ALL, CascadeType.PERSIST },
	        mappedBy = "question")
	@OrderBy("sortOrder ASC")
	private Set<QuestionDetail> questionDetails;

	@ManyToOne(
	        fetch = FetchType.EAGER,
	        cascade = CascadeType.ALL)
	@JoinColumn(
	        name = "question_answer_id")
	private QuestionAnswer questionAnswer;

	@OneToMany(
	        mappedBy = "question",
	        fetch = FetchType.LAZY,
	        cascade = { CascadeType.PERSIST })
	@JsonIgnore
	private Set<ExamPaperQuestion> examPaperQuestion;

	public Double getTotalScore() {
		return totalScore;
	}


	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
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


	public List<QuestionDetail> getQuestionDetails() {
		if (questionDetails == null) {
			return new ArrayList<QuestionDetail>(0);
		}
		else {
			return new ArrayList<QuestionDetail>(questionDetails);
		}
	}


	public void setQuestionDetails(List<QuestionDetail> questionDetails) {
		if (questionDetails == null) {
			return;
		}

		if (this.questionDetails == null) {
			this.questionDetails = new HashSet<QuestionDetail>(questionDetails);
		}
		else {
			this.questionDetails.clear();
			this.questionDetails.addAll(questionDetails);
		}

		for (QuestionDetail questionDetail : this.questionDetails) {
			questionDetail.setQuestionHeader(this);
		}
	}

	public List<ExamPaperQuestion> getExamPaperQuestion() {
		if (examPaperQuestion == null) {
			return new ArrayList<ExamPaperQuestion>(0);
		}
		else {
			return new ArrayList<ExamPaperQuestion>(examPaperQuestion);
		}
	}


	public void setExamPaperQuestion(List<ExamPaperQuestion> examPaperQuestion) {
		if (examPaperQuestion == null) {
			return;
		}

		if (this.examPaperQuestion == null) {
			this.examPaperQuestion = new HashSet<ExamPaperQuestion>(
			        examPaperQuestion);
		}
		else {
			this.examPaperQuestion.clear();
			this.examPaperQuestion.addAll(examPaperQuestion);
		}

		for (ExamPaperQuestion examPaperQuestion2 : this.examPaperQuestion) {
			examPaperQuestion2.setQuestion(this);
		}
	}


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof Question)) return false;
		Question castOther = (Question) other;
		return new EqualsBuilder().append(content, castOther.content)
		        .append(subject, castOther.subject)
		        .append(description, castOther.description)
		        .append(totalScore, castOther.totalScore)
		        .append(questionType, castOther.questionType).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(content).append(subject)
		        .append(description).append(totalScore).append(questionType)
		        .toHashCode();
	}

}
