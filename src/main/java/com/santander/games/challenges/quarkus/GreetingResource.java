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
// https://github.com/smallrye/smallrye-open-api/issues/240
@SecuritySchemes(value = {
        @SecurityScheme(securitySchemeName = "api_key",
                type = SecuritySchemeType.APIKEY,
                apiKeyName = "api_key",
                in = SecuritySchemeIn.HEADER),
        @SecurityScheme(securitySchemeName = "openIdConnectUrl",
                type = SecuritySchemeType.OPENIDCONNECT,
                openIdConnectUrl = "http://sso-rh-sso.apps.cluster-8fhm9.8fhm9.sandbox1428.opentlc" +
                        ".com/auth/realms/user11/.well-known/openid-configuration")}
)
@SecurityRequirement(name = "api-key")
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
