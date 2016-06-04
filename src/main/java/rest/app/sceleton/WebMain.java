package rest.app.sceleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("rest.app.sceleton")
public class WebMain {

	public static void main(String[] args) {
		SpringApplication.run(WebMain.class, args);
	}

}