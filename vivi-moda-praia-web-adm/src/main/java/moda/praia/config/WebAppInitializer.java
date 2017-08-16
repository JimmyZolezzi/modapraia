package moda.praia.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import moda.praia.security.SecurityConfiguration;


public class WebAppInitializer  implements WebApplicationInitializer {
	
	    public void onStartup(ServletContext container) {
	       

	    	 // Create the 'root' Spring application context
	        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	        rootContext.register(JPAConfig.class,SecurityConfiguration.class);
	        
	 
	        // Manage the lifecycle of the root application context
	        container.addListener(new ContextLoaderListener(rootContext));
	        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	        ctx.register(SpringWebConfig.class);
	        ctx.setServletContext(container);
	        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
	        //dispatcher
	        servlet.setLoadOnStartup(1);
	        servlet.addMapping("/");
	        
	        FilterRegistration filterRegistration =
	        		container.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
	        filterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
	         
	    }
	    

}
