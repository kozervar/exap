
package pl.kozervar.exap.model;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import pl.kozervar.exap.model.exam.ExamPaper;
import pl.kozervar.exap.model.question.QuestionSubject;


public class ExamPaperQuestionSubjectPK implements Serializable {

	private static final long serialVersionUID = -8185593875971014653L;

	@ManyToOne(
	        fetch = FetchType.LAZY)
	@JoinColumn(
	        name = "exam_paper_id",
	        referencedColumnName = "id")
	private ExamPaper examPaper;

	@ManyToOne(
	        fetch = FetchType.LAZY)
	@JoinColumn(
	        name = "question_subject_id",
	        referencedColumnName = "id")
	private QuestionSubject questionSubject;


}
