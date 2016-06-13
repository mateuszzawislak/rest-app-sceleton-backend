package spring.web.app.skeleton.act.deserializer;

import spring.web.app.skeleton.act.dto.ActDto;
import spring.web.app.skeleton.act.model.Act;

public class ActDeserializer {

	public static Act fromDto(ActDto dto) {
		Act act = new Act();

		act.setId(dto.getId());
		act.setAlias(dto.getAlias());
		act.setContent(dto.getContent());
		act.setTitle(dto.getTitle());

		return act;
	}
}
