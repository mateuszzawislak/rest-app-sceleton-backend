package spring.web.app.skeleton.monitoring.controller;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("monitoring")
public class MonitoringController {

	@Autowired
	EntityManager em;

	@RequestMapping(method = RequestMethod.GET, value = "ping")
	public ResponseEntity<String> ping() {
		em.createNativeQuery("SELECT 1").getResultList();

		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

}
