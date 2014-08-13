package pl.kozervar.exap.model.person;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.kozervar.exap.model.exam.ExamPaper;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PersonExam.class)
public abstract class PersonExam_ extends pl.kozervar.exap.model.Informable_ {

	public static volatile SetAttribute<PersonExam, PersonAnswer> personAnswers;
	public static volatile SingularAttribute<PersonExam, ExamPaper> examPaper;
	public static volatile SingularAttribute<PersonExam, Person> person;
	public static volatile SingularAttribute<PersonExam, String> shaToken;

}

