package com.xxx.template.jsf;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sun.faces.config.FacesInitializer;

/**
 * JSF是一個Servlet，所以JSF與SPRING MVC可以共存
 * 本類別會向Spring boot註冊一個ServletRegistrationBean
 * (實際物件是自定義的JsfServletRegistrationBean類別)
 * Server啟動時就會把這個Bean註冊進Container
 * 
 * 
 * @author nt80212
 *
 */
@Configuration
public class ConfigureJSF {

	@Bean
	public ServletRegistrationBean facesServletRegistration() {

		ServletRegistrationBean servletRegistrationBean = new JsfServletRegistrationBean();

		return servletRegistrationBean;
	}

	public class JsfServletRegistrationBean extends ServletRegistrationBean {

		public JsfServletRegistrationBean() {
			super();
		}

		@Override
		public void onStartup(ServletContext servletContext)
				throws ServletException {

			FacesInitializer facesInitializer = new FacesInitializer();

			Set<Class<?>> clazz = new HashSet<Class<?>>();
			clazz.add(ConfigureJSF.class);
			facesInitializer.onStartup(clazz, servletContext);
		}
	}
	
	/*測試用*/
	@Bean
	public JSFSpringBean jsfSpringBean() {
		return new JSFSpringBean();
	}
	
	/* spring boot不需要web.xml，但是JSF需要設定context parameter
	 * 所以要透過程式碼方式設定
	 * 
	 * 以下定義兩個profile
	 * 在application.properties指定使用
	 * */
	/*profile dev設定*/
	@Profile("dev")
	static class ConfigureJSFContextParameters implements ServletContextInitializer {

		@Override
		public void onStartup(ServletContext servletContext)
				throws ServletException {

			servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX",
					".xhtml");
			servletContext.setInitParameter(
					"javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
			servletContext.setInitParameter("javax.faces.PROJECT_STAGE",
					"Development");
			servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
			servletContext.setInitParameter(
					"javax.faces.FACELETS_REFRESH_PERIOD", "1");

		}
	}
	
	/*profile production設定*/
	@Profile("production")
	static class ConfigureJSFContextParametersProd implements ServletContextInitializer {

		@Override
		public void onStartup(ServletContext servletContext)
				throws ServletException {

			servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX",
					".xhtml");
			servletContext.setInitParameter(
					"javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
			servletContext.setInitParameter("javax.faces.PROJECT_STAGE",
					"Production");
			servletContext.setInitParameter("facelets.DEVELOPMENT", "false");
			servletContext.setInitParameter(
					"javax.faces.FACELETS_REFRESH_PERIOD", "-1");

		}
	}
}