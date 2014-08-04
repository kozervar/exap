package pl.kozervar.exap.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.kozervar.exap.model.exam.ExamPaper;
import pl.kozervar.exap.model.question.Question;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExamPaperQuestion.class)
public abstract class ExamPaperQuestion_ extends pl.kozervar.exap.model.Identifiable_ {

	public static volatile SingularAttribute<ExamPaperQuestion, ExamPaper> examPaper;
	public static volatile SingularAttribute<ExamPaperQuestion, Integer> sortOrder;
	public static volatile SingularAttribute<ExamPaperQuestion, Question> question;

}

