package com.xxx.template.jsf.config;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.context.ServletContextAware;

import com.sun.faces.config.ConfigureListener;
import com.xxx.template.jsf.controller.JsfSpringBean;

/**
 * JSF是一個Servlet，所以JSF與SPRING MVC可以共存
 * 本類別會向Spring boot註冊一個ServletRegistrationBean
 * (實際物件是自定義的JsfServletRegistrationBean類別)
 * Server啟動時就會把這個Bean註冊進Container
 * 
 * 此類別設定應用採用Primefaces的實作，請使用application.properties設定active profile
 * 
 * @author nt80212
 *
 */
@Profile("Primefaces")
@Configuration
public class PrimeFacesConfig implements ServletContextAware{
	@Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<ConfigureListener>(
            new ConfigureListener());
    }

	@Bean
	public ServletRegistrationBean facesServletRegistration() {

		ServletRegistrationBean servletRegistrationBean = 
				new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}
	
	@Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());       
        servletContext.setInitParameter("javax.faces.PROJECT_STAGE",
				"Development");
		servletContext.setInitParameter(
		"javax.faces.FACELETS_REFRESH_PERIOD", "1");
		servletContext.setInitParameter(
				"javax.faces.STATE_SAVING_METHOD", "server");
		servletContext.setInitParameter(
				"primefaces.THEME", "south-street");
	}
	
	/*測試用*/
	@Bean
	public JsfSpringBean jsfSpringBean() {
		return new JsfSpringBean();
	}

	

}