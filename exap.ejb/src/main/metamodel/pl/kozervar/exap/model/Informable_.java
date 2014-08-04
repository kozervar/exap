package pl.kozervar.exap.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Informable.class)
public abstract class Informable_ extends pl.kozervar.exap.model.Versionable_ {

	public static volatile SingularAttribute<Informable, Date> creationDate;
	public static volatile SingularAttribute<Informable, String> creationUser;
	public static volatile SingularAttribute<Informable, Date> updateDate;
	public static volatile SingularAttribute<Informable, String> updateUser;

}

