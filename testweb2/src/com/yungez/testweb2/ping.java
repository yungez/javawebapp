package com.yungez.testweb2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/ping")
public class ping {
	@GET
	@Produces("application/json")
	public Response ping() throws JSONException {
		
		String result = "hello world";
		return Response.status(200).entity(result).build();
	}
	
	public Response serialize() throws JSONException {
		return Response.status(200).entity("hello world!").build();
	}

	public Response Deserialize() throws JSONException {
		return Response.status(200).entity("hello world!").build();
	}	
}
	

