package com.santander.games.challenges.quarkus;

import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@SecuritySchemes(value = {
        @SecurityScheme(type = SecuritySchemeType.APIKEY,
                description = "api-key for 3scale",
                securitySchemeName = "api-key",
                in = SecuritySchemeIn.HEADER)})
public class GreetingResource {

  @GET
  @SecurityRequirement(name = "api-key")
  @Path("/hello")
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello Challenge 5!";
  }

  @GET
  @SecurityRequirement(name = "api-key")
  @Path("/hello/{name}")
  @Produces(MediaType.TEXT_PLAIN)
  public String helloName(@PathParam("name") String name) {
    return "Hello " + name + "! This is challenge 5!";
  }

  @GET
  @SecurityRequirement(name = "api-key")
  @Path("/goodbye")
  @Produces(MediaType.TEXT_PLAIN)
  public String goodbye() {
    return "Goodbye Challenge 5!";
  }

  @GET
  @SecurityRequirement(name = "api-key")
  @Path("/goodbye/{name}")
  @Produces(MediaType.TEXT_PLAIN)
  public String goodbyeName(@PathParam("name") String name) {
    return "Goodbye " + name + "!";
  }
}
