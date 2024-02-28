package elearning.model;

import elearning.model.base.BaseObject;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Comment extends BaseObject {
	private String content;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;
	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;
	@ManyToOne
	@JoinColumn(name = "comment_id")
	private Comment comment;
}
