package spring.web.app.skeleton.web.config;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

@Configuration
public class VelocityConfig {

	@Value("${velocity.engine.config}")
	String velocityEngineConfig;

	@Value("${velocity.tools.config}")
	String velocityToolsConfig;

	@Value("${velocity.templates.suffix}")
	String velocityTemplatesSuffix;

	@Autowired
	ServletContext servletContext;

	@Bean
	public VelocityConfigurer getVelocityConfigurer() {
		try (InputStream velocityEngineConfigStream = servletContext.getResourceAsStream(velocityEngineConfig)) {
			VelocityConfigurer cfg = new VelocityConfigurer();

			Properties velocityEngineConfig = new Properties();
			velocityEngineConfig.load(velocityEngineConfigStream);
			cfg.setVelocityProperties(velocityEngineConfig);

			return cfg;
		} catch (Exception e) {
			throw new IllegalStateException("Unable to load velocity engine configuration", e);
		}
	}

	@Bean
	public VelocityEngine getVelocityEngine() {
		return new VelocityEngine();
	}

	@Bean
	public VelocityViewResolver getVelocityViewResolver() {
		VelocityViewResolver velocityViewResolver = new VelocityViewResolver();

		velocityViewResolver.setToolboxConfigLocation(velocityToolsConfig);
		velocityViewResolver.setSuffix(velocityTemplatesSuffix);
		velocityViewResolver.setContentType("text/html;charset=UTF-8");

		return velocityViewResolver;
	}

}
