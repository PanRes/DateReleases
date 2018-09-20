package gr.pr.date_releases.controllers.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@PropertySource("classpath:fileUrls.properties")
public class GenericControllerAdvice {
	
	@Autowired
	private Environment environment;
	
	@ModelAttribute("seriesImgsDir")
	public String getSeriesImagesDir() {
		return environment.getProperty("series.img.url");
	}
	
	@ModelAttribute("userImgsDir")
	public String getUserImagesDir() {
		return environment.getProperty("users.img.url");
	}
}
