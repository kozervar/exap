
package pl.kozervar.exap.rest.exam;

import java.io.Serializable;
import java.util.List;

import pl.kozervar.exap.model.exam.ExamType;

public class ExamData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long examId;
	private String name;
	private String description;
	private ExamType examType;
	private List<QuestionData> questionData;

	public ExamData() {
	}

	public ExamData(Long examId, String name, String description,
	        ExamType examType, List<QuestionData> questionData) {
		super();
		this.examId = examId;
		this.name = name;
		this.description = description;
		this.examType = examType;
		this.questionData = questionData;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	public List<QuestionData> getQuestionData() {
		return questionData;
	}

	public void setQuestionData(List<QuestionData> questionData) {
		this.questionData = questionData;
	}

}
