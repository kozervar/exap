package pl.kozervar.exap.model.question;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QuestionHeader.class)
public abstract class QuestionHeader_ extends pl.kozervar.exap.model.Informable_ {

	public static volatile SetAttribute<QuestionHeader, QuestionDetail> questionDetails;
	public static volatile SingularAttribute<QuestionHeader, String> description;
	public static volatile SingularAttribute<QuestionHeader, QuestionAnswer> questionAnswer;
	public static volatile SingularAttribute<QuestionHeader, Long> score;
	public static volatile SingularAttribute<QuestionHeader, QuestionType> questionType;
	public static volatile SingularAttribute<QuestionHeader, QuestionSubject> questionSubject;

}

