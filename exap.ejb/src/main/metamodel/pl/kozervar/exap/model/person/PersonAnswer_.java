package pl.kozervar.exap.model.person;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PersonAnswer.class)
public abstract class PersonAnswer_ extends pl.kozervar.exap.model.Versionable_ {

	public static volatile SingularAttribute<PersonAnswer, Long> questionId;
	public static volatile SingularAttribute<PersonAnswer, PersonExam> personExam;
	public static volatile SingularAttribute<PersonAnswer, String> answer;
	public static volatile SingularAttribute<PersonAnswer, Long> questionDetaildId;

}

