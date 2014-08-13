
package pl.kozervar.exap.rest.exam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import pl.kozervar.exap.model.question.QuestionType;


public class QuestionData implements Serializable {

    private static final long serialVersionUID = 1L;
    
	private Long questionId;
	private String subject;
	private String description;
	private String content;
	private QuestionType questionType;
	private List<QuestionAnswerData> questionAnswers;
	

	public QuestionData() {
	}

	public QuestionData(Long questionId, String subject, String description,
	        String content, QuestionType questionType, List<QuestionAnswerData> questionAnswers) {
		super();
		this.questionId = questionId;
		this.subject = subject;
		this.description = description;
		this.content = content;
		this.questionType = questionType;
		this.questionAnswers = questionAnswers;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
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

	public List<QuestionAnswerData> getQuestionAnswers() {
	    return questionAnswers;
    }

	public void setQuestionAnswers(List<QuestionAnswerData> questionAnswers) {
	    this.questionAnswers = questionAnswers;
    }

}
