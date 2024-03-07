package elearning.model;

import elearning.model.base.BaseObject;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Lesson extends BaseObject {
	private String title;
	private String video;
	private String resources;
	private String description;
	private String document;
	@ManyToOne
	@JoinColumn(name = "chapter_id")
	private Chapter chapter;
}
