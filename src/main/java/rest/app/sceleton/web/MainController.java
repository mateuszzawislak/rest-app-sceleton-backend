package rest.app.sceleton.web;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(Map<String, Object> model) {
		ModelAndView mv = new ModelAndView("index");
		mv.getModel().put("time", new Date());
		return mv;
	}

}
