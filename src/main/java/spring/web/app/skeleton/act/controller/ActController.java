package spring.web.app.skeleton.act.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.web.app.skeleton.act.deserializer.ActDeserializer;
import spring.web.app.skeleton.act.dto.ActDto;
import spring.web.app.skeleton.act.model.Act;
import spring.web.app.skeleton.act.serializer.ActSerializer;
import spring.web.app.skeleton.act.service.ActService;
import spring.web.app.skeleton.web.dto.MetaDto;
import spring.web.app.skeleton.web.dto.TableDto;

@Controller
@RequestMapping("act")
public class ActController {

	@Autowired
	ActService actService;

	@RequestMapping(method = RequestMethod.GET, value = "{alias}")
	public ModelAndView details(@PathVariable("alias") String alias) {
		ActDto act = ActSerializer.toDto(actService.getByAlias(alias));

		ModelAndView mav = new ModelAndView("act/details");
		mav.getModel().put("act", act);
		return mav;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "details/{id}")
	public ResponseEntity<ActDto> get(@PathVariable("id") Long id) {
		return new ResponseEntity<>(ActSerializer.toDto(actService.get(id)), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ActDto> create(@Valid @RequestBody(required = true) ActDto act) {
		return new ResponseEntity<>(ActSerializer.toDto(actService.create(ActDeserializer.fromDto(act))), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public ResponseEntity<TableDto<ActDto>> list(@RequestParam(value = "firstResult", defaultValue = "0") Integer firstResult,
			@RequestParam(value = "maxResults", defaultValue = "30") Integer maxResults) {
		List<Act> acts = actService.list(firstResult, maxResults, null, null);
		Long count = actService.count();

		return new ResponseEntity<>(new TableDto<ActDto>(new MetaDto(firstResult, maxResults, count),
				acts.stream().map(ActSerializer::toDto).collect(Collectors.toList())), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		actService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<ActDto> update(@PathVariable("id") Long id, @Valid @RequestBody(required = true) ActDto dto) {
		Act act = actService.update(id, ActDeserializer.fromDto(dto));
		return new ResponseEntity<>(ActSerializer.toDto(act), HttpStatus.OK);
	}
}
