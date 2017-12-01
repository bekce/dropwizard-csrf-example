package com.sebworks.oauthly;

import com.sebworks.oauthly.core.CsrfFilter;
import com.sebworks.oauthly.resources.LoginResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.server.session.SessionHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.Map;

public class App extends Application<Conf> {

    public static void main(final String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "oauthly";
    }

    @Override
    public void initialize(final Bootstrap<Conf> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets"));
        bootstrap.addBundle(new ViewBundle<Conf>(){
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(Conf conf) {
                return conf.getViewRendererConfiguration();
            }
        });
    }

    @Override
    public void run(final Conf configuration,
                    final Environment environment) {
        environment.servlets().setSessionHandler(new SessionHandler());
        environment.servlets().addFilter("csrfFilter", new CsrfFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");

        environment.jersey().register(new LoginResource());
    }



}
