package pl.kozervar.exap.model.exam;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.kozervar.exap.model.ExamPaperQuestion;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExamPaper.class)
public abstract class ExamPaper_ extends pl.kozervar.exap.model.Informable_ {

	public static volatile SingularAttribute<ExamPaper, String> description;
	public static volatile SingularAttribute<ExamPaper, String> name;
	public static volatile SetAttribute<ExamPaper, ExamPaperQuestion> examPaperQuestions;
	public static volatile SingularAttribute<ExamPaper, Boolean> active;
	public static volatile SingularAttribute<ExamPaper, ExamType> examType;

}

