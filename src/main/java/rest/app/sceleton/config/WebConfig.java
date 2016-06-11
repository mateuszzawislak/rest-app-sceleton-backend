package rest.app.sceleton.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import rest.app.sceleton.web.config.VelocityHandlerInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public VelocityHandlerInterceptor getVelocityHandlerInterceptor() {
		return new VelocityHandlerInterceptor();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(new String[] { "classpath:/META-INF/resources/" });
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(getVelocityHandlerInterceptor());
	}


}
