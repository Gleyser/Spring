package br.com.rest;

import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

@Path("/hello")
public class HelloResource {
	
	@GET
	public String get() {
		return "testando GET";
	}
	
	@POST
	public String post() {
		return "testando POST";
	}
	
	@DELETE
	public String delete() {
		return "testando DELETE";
	}
	
	@PUT
	public String put() {
		return "testando PUT";
	}

}
