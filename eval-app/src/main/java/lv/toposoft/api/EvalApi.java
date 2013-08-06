package lv.toposoft.api;

import com.wordnik.swagger.annotations.*;

import lv.toposoft.client.model.Script;
import lv.toposoft.client.model.ScriptResponse;
import lv.toposoft.api.NotFoundException;
import lv.toposoft.app.ScriptManager;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Path("/eval")
@Api(value = "/eval", description = "the eval API")
@Produces({"application/json"})
@Consumes({"application/json"})
public class EvalApi {
	private MongoOperations mongoOperation;

	public MongoOperations getMongoOperation() {
		return mongoOperation;
	}

	public void setMongoOperation(MongoOperations mongoOperation) {
		this.mongoOperation = mongoOperation;
	}

	@POST
	@Path("/")
	@ApiOperation(value = "Create script", notes = "Currently only JavaScript is supported", responseClass = "ScriptResponse")
	@ApiErrors(value = { })     
	public Response createScript(
	    @ApiParam(value = "Created script object"
	    ,required=true) Script body
    ) throws NotFoundException {
      // do some magic!
		if(body.getScriptType().isEmpty()){
			  body.setScriptType("javascript");
		}
		mongoOperation.save(body, "script");
		ScriptResponse sr = new ScriptResponse();
		sr.setId(body.getId());
		return Response.ok().entity(sr).build();
	}

  @GET
  @Path("/{scriptId}")
  @ApiOperation(value = "Execute script and return source,result and output", notes = "", responseClass = "Script")
  @ApiErrors(value = { @ApiError(code = 400, reason = "Invalid script Id")})
     
  public Response getScriptById(
    @ApiParam(value = "The script Id"
    ,required=true)@PathParam("scriptId") String scriptId
    )
      throws NotFoundException {
      // do some magic!
	  Query searchScriptQuery = new Query(Criteria.where("id").is(scriptId));
	  
	  // find the saved user again.
	  Script scr = mongoOperation.findOne(searchScriptQuery, Script.class);
	  if(scr == null){
		  throw new NotFoundException(400, "Invalid script Id");
	  }
	  ScriptManager sm = new ScriptManager();
	  sm.setScript(scr);
	  sm.executeScript();
      return Response.ok().entity(scr).build();
  }

  /*
  public EvalApi(){
	  ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
	  mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");	  
  }
  */
}

