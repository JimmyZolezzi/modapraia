package moda.praia.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import moda.praia.config.JPAConfig;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer  implements WebApplicationInitializer {
	
	    public void onStartup(ServletContext container) {
	       
	    	 // Create the 'root' Spring application context
	        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	        rootContext.register(JPAConfig.class,SecurityConfig.class);
	 
	        // Manage the lifecycle of the root application context
	        container.addListener(new ContextLoaderListener(rootContext));
	        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	        ctx.register(SpringWebConfig.class);
	        ctx.setServletContext(container);
	        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
	 
	        servlet.setLoadOnStartup(1);
	        servlet.addMapping("/");
	         
	    }
	    

}
