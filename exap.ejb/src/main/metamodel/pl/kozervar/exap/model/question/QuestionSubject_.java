package pl.kozervar.exap.model.question;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.kozervar.exap.model.ExamPaperQuestionSubject;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QuestionSubject.class)
public abstract class QuestionSubject_ extends pl.kozervar.exap.model.Informable_ {

	public static volatile SingularAttribute<QuestionSubject, String> content;
	public static volatile SingularAttribute<QuestionSubject, Long> totalScore;
	public static volatile SetAttribute<QuestionSubject, ExamPaperQuestionSubject> examPaperQuestionSubjects;
	public static volatile SingularAttribute<QuestionSubject, QuestionType> questionType;
	public static volatile SetAttribute<QuestionSubject, QuestionHeader> questionHeaders;

}

