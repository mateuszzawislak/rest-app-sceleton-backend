package rest.app.sceleton.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import rest.app.sceleton.user.dto.UserDto;
import rest.app.sceleton.user.model.User;
import rest.app.sceleton.user.serializer.UserSerializer;
import rest.app.sceleton.user.service.UserService;
import rest.app.sceleton.web.dto.MetaDto;
import rest.app.sceleton.web.dto.TableDto;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	HttpServletRequest request;

	@RequestMapping(method = RequestMethod.GET, value = "login")
	public ModelAndView loginForm(@RequestParam(value = "failed", defaultValue = "false") Boolean failed) {
		ModelAndView mav = new ModelAndView("user/login");

		if (failed)
			mav.getModel().put("error", true);

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "logout")
	public String logout() {
		request.getSession(false).invalidate();
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, value = "register")
	public ModelAndView registerForm() {
		return new ModelAndView("user/register");
	}

	@RequestMapping(method = RequestMethod.POST, value = "register")
	public ResponseEntity<UserDto> register(@Valid @RequestBody(required = true) UserDto user) {
		return new ResponseEntity<>(UserSerializer.toDto(userService.register(user.getEmail(), user.getPassword())), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public ResponseEntity<TableDto<UserDto>> list(@RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "30") Integer pageSize) {
		List<User> users = userService.list(pageIndex, pageSize, null, null);
		Long count = userService.count();

		return new ResponseEntity<>(new TableDto<UserDto>(new MetaDto(pageIndex, pageSize, count),
				users.stream().map(UserSerializer::toDto).collect(Collectors.toList())), HttpStatus.OK);
	}

}

