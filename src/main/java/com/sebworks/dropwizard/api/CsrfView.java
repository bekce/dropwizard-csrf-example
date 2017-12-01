package com.sebworks.dropwizard.api;

import com.sebworks.dropwizard.core.CsrfFilter;
import io.dropwizard.jersey.sessions.Flash;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;

@Getter
public class CsrfView extends BaseView {
    protected String csrfToken;

    public CsrfView(String templateName, HttpServletRequest request) {
        this(templateName, null, request);
    }

    public CsrfView(String templateName, Flash<ResponseMessage> message, HttpServletRequest request) {
        super(templateName, message);
        this.csrfToken = (String) request.getSession().getAttribute(CsrfFilter.CSRF_TOKEN_KEY);
    }

}
