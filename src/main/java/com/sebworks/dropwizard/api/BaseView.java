package com.sebworks.dropwizard.api;

import io.dropwizard.jersey.sessions.Flash;
import io.dropwizard.views.View;
import lombok.Getter;

@Getter
public abstract class BaseView extends View {

    protected ResponseMessage message;

    protected BaseView(String templateName) {
        super(templateName);
    }
    protected BaseView(String templateName, ResponseMessage message) {
        super(templateName);
        this.message = message;
    }
    protected BaseView(String templateName, Flash<ResponseMessage> message) {
        super(templateName);
        if(message != null)
            this.message = message.get().orElse(null);
    }
}
