package com.sebworks.dropwizard.api;

import io.dropwizard.jersey.sessions.Flash;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;

@Getter
public class LoginView extends CsrfView {

    public LoginView(Flash<ResponseMessage> message, HttpServletRequest request) {
        super("login.mustache", message, request);
    }

}
