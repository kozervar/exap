
package pl.kozervar.exap.model.exam;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pl.kozervar.exap.model.ExamPaperQuestionSubject;
import pl.kozervar.exap.model.Informable;

@Entity
@Table(
        name = "exam_paper")
public class ExamPaper extends Informable {

	private static final long serialVersionUID = 1L;

	@Column(
	        name = "name")
	private String name;

	@Column(
	        name = "description")
	private String description;

	@Column(
	        name = "active")
	private Boolean active;

	@ManyToOne(
	        fetch = FetchType.EAGER)
	@JoinColumn(
	        name = "exam_type_id")
	private ExamType examType;

	@OneToMany(
	        mappedBy = "examPaper",
	        fetch = FetchType.EAGER,
	        orphanRemoval = true,
	        cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<ExamPaperQuestionSubject> examPaperQuestionSubjects = new HashSet<ExamPaperQuestionSubject>();


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


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public Set<ExamPaperQuestionSubject> getExamPaperQuestionSubjects() {
		return examPaperQuestionSubjects;
	}


	public void setExamPaperQuestionSubjects(
	        Set<ExamPaperQuestionSubject> examPaperQuestionSubjects) {
		this.examPaperQuestionSubjects = examPaperQuestionSubjects;
	}


	public ExamType getExamType() {
		return examType;
	}


	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

}
