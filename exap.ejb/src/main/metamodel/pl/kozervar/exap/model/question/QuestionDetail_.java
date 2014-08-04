package pl.kozervar.exap.model.question;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QuestionDetail.class)
public abstract class QuestionDetail_ extends pl.kozervar.exap.model.Identifiable_ {

	public static volatile SingularAttribute<QuestionDetail, String> content;
	public static volatile SingularAttribute<QuestionDetail, Integer> sortOrder;
	public static volatile SingularAttribute<QuestionDetail, Double> score;
	public static volatile SingularAttribute<QuestionDetail, Question> question;

}

