package gr.pr.date_releases.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = "gr.pr.date_releases")
@PropertySource("classpath:mysql.properties")
public class AppConfiguration extends WebMvcConfigurationSupport {

	@Autowired
	private Environment environment;

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}


	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

//		sessionFactory.setAnnotatedClasses(UserEntity.class, Role.class);
//		sessionFactory.setAnnotatedPackages("gr.pr.udemy.spring.security");

		Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.connection.driver_class",environment.getProperty("jdbc.driver"));
		hibernateProperties.setProperty("hibernate.connection.url",environment.getProperty("jdbc.url"));
		hibernateProperties.setProperty("hibernate.connection.user",environment.getProperty("jdbc.user"));
		hibernateProperties.setProperty("hibernate.connection.password",environment.getProperty("jdbc.password"));

		hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql",environment.getProperty("hibernate.show_sql"));

		hibernateProperties.setProperty("hibernate.c3p0.min_size",environment.getProperty("hibernate.c3p0.min_size"));
		hibernateProperties.setProperty("hibernate.c3p0.max_size",environment.getProperty("hibernate.c3p0.max_size"));
		hibernateProperties.setProperty("hibernate.c3p0.timeout",environment.getProperty("hibernate.c3p0.timeout"));
		hibernateProperties.setProperty("hibernate.c3p0.max_statements",environment.getProperty("hibernate.c3p0.max_statements"));


		sessionFactory.setHibernateProperties(hibernateProperties);

		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager(){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
