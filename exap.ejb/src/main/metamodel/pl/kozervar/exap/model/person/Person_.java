package pl.kozervar.exap.model.person;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ extends pl.kozervar.exap.model.Informable_ {

	public static volatile SingularAttribute<Person, Boolean> accepted;
	public static volatile SingularAttribute<Person, String> phoneNumber;
	public static volatile SingularAttribute<Person, String> email;
	public static volatile SingularAttribute<Person, String> name;
	public static volatile SingularAttribute<Person, String> surname;

}

