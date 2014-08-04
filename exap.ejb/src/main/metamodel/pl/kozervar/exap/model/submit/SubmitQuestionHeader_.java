package pl.kozervar.exap.model.submit;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SubmitQuestionHeader.class)
public abstract class SubmitQuestionHeader_ extends pl.kozervar.exap.model.Informable_ {

	public static volatile SingularAttribute<SubmitQuestionHeader, SubmitQuestionAnswer> submitQuestionAnswer;
	public static volatile SingularAttribute<SubmitQuestionHeader, Long> obtainedScore;
	public static volatile SingularAttribute<SubmitQuestionHeader, Date> reviewDate;
	public static volatile SingularAttribute<SubmitQuestionHeader, String> comment;

}

