package pl.kozervar.exap.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.kozervar.exap.model.exam.ExamPaper;
import pl.kozervar.exap.model.question.QuestionSubject;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExamPaperQuestionSubject.class)
public abstract class ExamPaperQuestionSubject_ extends pl.kozervar.exap.model.Identifiable_ {

	public static volatile SingularAttribute<ExamPaperQuestionSubject, ExamPaper> examPaper;
	public static volatile SingularAttribute<ExamPaperQuestionSubject, Integer> sortOrder;
	public static volatile SingularAttribute<ExamPaperQuestionSubject, QuestionSubject> questionSubject;

}

