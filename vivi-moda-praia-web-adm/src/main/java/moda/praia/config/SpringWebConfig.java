package moda.praia.config;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import moda.praia.web.view.JstlView;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan({ "moda.praia.admin.controller","moda.praia.controller","moda.praia.modulo"})
@EnableTransactionManagement
@ImportResource("classpath:spring-context.xml")
//@EnableJpaRepositories("moda.praia.modulo.produtos.repositorios")
public class SpringWebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	 @Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {

		 PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		    resolver.setPageParameterName("1");
		    resolver.setSizeParameterName("10");
		    resolver.setOneIndexedParameters(true);
		    argumentResolvers.add(resolver);
		    super.addArgumentResolvers(argumentResolvers);

	 }

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		rb.setBasenames(new String[] { "messages/messages", "messages/validation" });
		return rb;
	}
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {	
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(entityManagerFactory);
		return new JpaTransactionManager(entityManagerFactory);
	}
}
