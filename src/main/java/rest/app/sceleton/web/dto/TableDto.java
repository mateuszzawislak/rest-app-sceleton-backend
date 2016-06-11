package rest.app.sceleton.web.dto;

import java.io.Serializable;
import java.util.List;

public class TableDto<T> implements Serializable {

	private static final long serialVersionUID = -6385116734058560549L;

	List<T> items;

	MetaDto meta;

	public TableDto() {

	}

	public TableDto(MetaDto meta, List<T> items) {
		this.meta = meta;
		this.items = items;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public MetaDto getMeta() {
		return meta;
	}

	public void setMeta(MetaDto meta) {
		this.meta = meta;
	}

}
