package pl.kozervar.exap.model.question;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pl.kozervar.exap.model.tag.QuestionTag;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Question.class)
public abstract class Question_ extends pl.kozervar.exap.model.Informable_ {

	public static volatile SingularAttribute<Question, String> content;
	public static volatile SingularAttribute<Question, Double> totalScore;
	public static volatile SetAttribute<Question, QuestionDetail> questionDetails;
	public static volatile SingularAttribute<Question, String> description;
	public static volatile SingularAttribute<Question, String> subject;
	public static volatile SingularAttribute<Question, QuestionAnswer> questionAnswer;
	public static volatile SingularAttribute<Question, QuestionType> questionType;
	public static volatile SetAttribute<Question, QuestionTag> questionTags;

}

