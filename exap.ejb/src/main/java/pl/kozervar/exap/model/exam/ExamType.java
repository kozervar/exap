
package pl.kozervar.exap.model.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import pl.kozervar.exap.model.Identifiable;

@Entity
@Table(
        name = "exam_type")
public class ExamType extends Identifiable {

	private static final long serialVersionUID = 1L;

	@Column(
	        name = "name")
	private String name;

	@Column(
	        name = "description")
	private String description;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
}
