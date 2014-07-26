
package pl.kozervar.exap.rest.facade.submit;

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

import pl.kozervar.exap.model.submit.SubmitQuestionAnswer;
import pl.kozervar.exap.rest.facade.RESTFacade;


@Stateless
@Path("/SubmitQuestionAnswer")
public class SubmitQuestionAnswerRESTFacade extends
        RESTFacade<SubmitQuestionAnswer> {

	public SubmitQuestionAnswerRESTFacade() {
		super(SubmitQuestionAnswer.class);
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
	public SubmitQuestionAnswer create(SubmitQuestionAnswer entity) {
		return super.create(entity);
	}

	@PUT
	@Path("{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Override
	public SubmitQuestionAnswer edit(SubmitQuestionAnswer entity) {
		return super.edit(entity);
	}

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	@Override
	public SubmitQuestionAnswer find(@PathParam("id") Integer id) {
		return super.find(id);
	}

	@GET
	@Produces({ "application/json" })
	@Override
	public Collection<SubmitQuestionAnswer> findAll() {
		return super.findAll();
	}

	@GET
	@Path("{from}/{to}")
	@Produces({ "application/json" })
	@Override
	public Collection<SubmitQuestionAnswer> findRange(
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
