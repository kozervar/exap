
package pl.kozervar.exap.rest.facade.question;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import pl.kozervar.exap.model.question.QuestionSubject;
import pl.kozervar.exap.rest.facade.RESTFacade;


@Stateless
@Path("/QuestionSubject")
public class QuestionSubjectRESTFacade extends RESTFacade<QuestionSubject> {

	public QuestionSubjectRESTFacade() {
		super(QuestionSubject.class);
	}

	@GET
	@Path("count")
	@Produces("text/plain")
	@Override
	public String count() {
		return super.count();
	}

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Override
	public QuestionSubject create(QuestionSubject entity) {
		return super.create(entity);
	}

	@PUT
	@Path("{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Override
	public QuestionSubject edit(QuestionSubject entity) {
		return super.edit(entity);
	}

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	@Override
	public QuestionSubject find(@PathParam("id") Integer id) {
		return super.find(id);
	}

	@GET
	@Produces({ "application/json" })
	@Override
	public Collection<QuestionSubject> findAll() {
		return super.findAll();
	}

	@GET
	@Path("{from}/{to}")
	@Produces({ "application/json" })
	@Override
	public Collection<QuestionSubject> findRange(
	        @PathParam("from") Integer from, @PathParam("to") Integer to) {
		return super.findRange(from, to);
	}

	@DELETE
	@Path("{id}")
	@Override
	public void remove(@PathParam("id") Integer id) {
		super.remove(id);
	}

}
