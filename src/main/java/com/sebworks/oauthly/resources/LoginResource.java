package com.sebworks.oauthly.resources;

import com.sebworks.oauthly.api.LoginView;
import com.sebworks.oauthly.api.ResponseMessage;
import io.dropwizard.jersey.sessions.Flash;
import io.dropwizard.jersey.sessions.Session;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/login")
@Produces(MediaType.TEXT_HTML)
public class LoginResource {
    @GET
    public LoginView get(@Context HttpServletRequest request, @Session Flash<ResponseMessage> message) {
        return new LoginView(message, request);
    }

    @POST
    public Response post(@FormParam("username") String username, @FormParam("password") String password,
                         @Session Flash<ResponseMessage> message){
        message.set(new ResponseMessage().setInfo("Success! you sent "+username+":"+password));
        return Response.seeOther(UriBuilder.fromUri("/login").build()).build();
    }

}
