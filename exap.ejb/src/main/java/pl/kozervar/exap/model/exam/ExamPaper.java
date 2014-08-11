
package pl.kozervar.exap.model.exam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import pl.kozervar.exap.model.ExamPaperQuestion;
import pl.kozervar.exap.model.Informable;

@Entity
@Table(
        name = "exam_paper")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.UUIDGenerator.class,
        property = "@ExamPaper_UUID")
public class ExamPaper extends Informable {

	private static final long serialVersionUID = -4273328911593134195L;

	public ExamPaper() {
	}

	public ExamPaper(ExamPaper examPaper) {
		super(examPaper);
		this.name = examPaper.getName();
		this.description = examPaper.getDescription();
		this.active = examPaper.getActive();
		this.examType = examPaper.getExamType();
	}


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
	        cascade = CascadeType.ALL)
	@OrderBy(value="sortOrder")
	private Set<ExamPaperQuestion> examPaperQuestions = new HashSet<ExamPaperQuestion>();


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


	public List<ExamPaperQuestion> getExamPaperQuestions() {
		if (examPaperQuestions == null) {
			return new ArrayList<ExamPaperQuestion>(0);
		}
		else {
			return new ArrayList<ExamPaperQuestion>(examPaperQuestions);
		}
	}

	public void setExamPaperQuestions(List<ExamPaperQuestion> examPaperQuestions) {
		if (examPaperQuestions == null) {
			return;
		}

		if (this.examPaperQuestions == null) {
			this.examPaperQuestions = new HashSet<ExamPaperQuestion>(
			        examPaperQuestions);
		}
		else {
			this.examPaperQuestions.clear();
			this.examPaperQuestions.addAll(examPaperQuestions);
		}

		for (ExamPaperQuestion examPaperQuestion : this.examPaperQuestions) {
			examPaperQuestion.setExamPaper(this);
		}
	}

	public ExamType getExamType() {
		return examType;
	}


	public void setExamType(ExamType examType) {
		this.examType = examType;
	}


	@Override
	public boolean equals(final Object other) {
		if (this == other) return true;
		if (!(other instanceof ExamPaper)) return false;
		ExamPaper castOther = (ExamPaper) other;
		return new EqualsBuilder().append(name, castOther.name)
		        .append(description, castOther.description)
		        .append(active, castOther.active)
		        .append(examType, castOther.examType).isEquals();
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(description)
		        .append(active).append(examType).toHashCode();
	}

}
