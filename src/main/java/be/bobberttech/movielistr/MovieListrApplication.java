package be.bobberttech.movielistr;

import be.bobberttech.movielistr.movies.MovieListrModule;
import be.bobberttech.movielistr.movies.MovieResource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.java8.Java8Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

import static io.dropwizard.jersey.filter.AllowedMethodsFilter.ALLOWED_METHODS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_HEADERS_PARAM;
import static org.eclipse.jetty.servlets.CrossOriginFilter.ALLOWED_ORIGINS_PARAM;

public class MovieListrApplication extends io.dropwizard.Application<MovieListrConfiguration>{

    private Injector injector;

    public static void main(String[] args) throws Exception {
        new MovieListrApplication().run(args);
    }

    @Override
    public void run(MovieListrConfiguration configuration, Environment environment) throws Exception {
        injector = Guice.createInjector(new MovieListrModule());
        registerResources(environment);
        setupCORSFilter(environment);
    }

    @Override
    public void initialize(Bootstrap<MovieListrConfiguration> bootstrap) {
        bootstrap.addBundle(new Java8Bundle());
        super.initialize(bootstrap);
    }

    private void registerResources(Environment environment) {
        environment.jersey().register(injector.getInstance(MovieResource.class));
    }

    private void setupCORSFilter(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORSFilter", CrossOriginFilter.class);

        filter.setInitParameter(ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(ALLOWED_HEADERS_PARAM, "Origin, Content-Type, Accept");

        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
