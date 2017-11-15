package com.minyazi.test.webservice.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path(value = "/customer")
@Produces("application/xml")
public interface CustomerWebService {
    @GET
    @Path(value="/{id}/info")
    Customer getCustomerById(@PathParam("id") String id);
    
    @GET
    @Path(value="/search")
    Customer getCustomerByName(@QueryParam("name") String name);
}
