package rest.app.sceleton.monitoring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("monitoring")
public class MonitoringController {

	@RequestMapping(method = RequestMethod.GET, value = "ping")
	public ResponseEntity<String> ping() {
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

}
