package spring.web.app.skeleton.act.serializer;

import spring.web.app.skeleton.act.dto.ActDto;
import spring.web.app.skeleton.act.model.Act;

public class ActSerializer {

	public static ActDto toDto(Act act) {
		ActDto dto = new ActDto();

		dto.setId(act.getId());
		dto.setAlias(act.getAlias());
		dto.setContent(act.getContent());
		dto.setTitle(act.getTitle());
		dto.setModifiedAt(act.getModifiedAt());
		dto.setCreatedAt(act.getCreatedAt());

		return dto;
	}

}
