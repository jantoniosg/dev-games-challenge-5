package com.santander.games.challenges.quarkus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class GreetingResource {

  @GET
  @Path("/hello")
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello Challenge 5!";
  }

  @GET
  @Path("/hello/{name}")
  @Produces(MediaType.TEXT_PLAIN)
  public String helloName(@PathParam("name") String name) {
    return "Hello " + name + "! This is challenge 5!";
  }

  @GET
  @Path("/goodbye")
  @Produces(MediaType.TEXT_PLAIN)
  public String goodbye() {
    return "Goodbye Challenge 5!";
  }

  @GET
  @Path("/goodbye/{name}")
  @Produces(MediaType.TEXT_PLAIN)
  public String goodbyeName(@PathParam("name") String name) {
    return "Goodbye " + name + "!";
  }
}