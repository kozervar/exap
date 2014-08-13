
package pl.kozervar.exap.rest.exam;

import java.io.Serializable;


public class QuestionAnswerData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer sortOrder;
	private String content;

	public QuestionAnswerData() {
	}

	public QuestionAnswerData(Long id, Integer sortOrder, String content) {
		super();
		this.id = id;
		this.sortOrder = sortOrder;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
