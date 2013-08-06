package lv.toposoft.api;
import com.wordnik.swagger.annotations.*;
import com.wordnik.swagger.jaxrs.listing.ApiListing;

import javax.ws.rs.*;

@Path("/api-docs")
@Api("/api-docs")
@Produces({"application/json"})
public class ApiListingResourceJSON extends ApiListing {
	public ApiListingResourceJSON(){
		super();
	}
}