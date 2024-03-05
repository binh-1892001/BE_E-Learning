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
public class Chapter extends BaseObject {
	private String title;
	private String description;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
}
