
package pl.kozervar.exap.rest.creator;

import java.io.Serializable;
import java.util.List;

import pl.kozervar.exap.model.question.Question;
import pl.kozervar.exap.model.tag.Tag;


public class QuestionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
	private Question question;
	private List<Tag> tags;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}
