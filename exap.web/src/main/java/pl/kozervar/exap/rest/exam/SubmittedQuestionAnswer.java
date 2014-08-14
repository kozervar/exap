package pl.kozervar.exap.rest.exam;

import java.io.Serializable;
import java.util.List;


public class SubmittedQuestionAnswer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String token;
    private Long questionId;
    private String answer;
    private QuestionAnswerData selectedAnswer;
    private List<QuestionAnswerData> selectedAnswers;
	
    public String getToken() {
    	return token;
    }
	
    public void setToken(String token) {
    	this.token = token;
    }
	
    public Long getQuestionId() {
    	return questionId;
    }
	
    public void setQuestionId(Long questionId) {
    	this.questionId = questionId;
    }
	
    public String getAnswer() {
    	return answer;
    }
	
    public void setAnswer(String answer) {
    	this.answer = answer;
    }
	
    public QuestionAnswerData getSelectedAnswer() {
    	return selectedAnswer;
    }
	
    public void setSelectedAnswer(QuestionAnswerData selectedAnswer) {
    	this.selectedAnswer = selectedAnswer;
    }
	
    public List<QuestionAnswerData> getSelectedAnswers() {
    	return selectedAnswers;
    }
	
    public void setSelectedAnswers(List<QuestionAnswerData> selectedAnswers) {
    	this.selectedAnswers = selectedAnswers;
    }
    
    

}
