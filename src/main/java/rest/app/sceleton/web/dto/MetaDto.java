package rest.app.sceleton.web.dto;

import java.io.Serializable;

public class MetaDto implements Serializable {

	private static final long serialVersionUID = 4201928142844106697L;

	private Integer pageIndex;

	private Integer pageSize;

	private Long total;

	public MetaDto() {

	}

	public MetaDto(Integer pageIndex, Integer pageSize, Long total) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.total = total;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
