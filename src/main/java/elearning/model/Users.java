package elearning.model;

import elearning.model.base.BaseObject;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Users extends BaseObject {
	private String username;
	private String fullName;
	private String phone;
	private String password;
	private Boolean isActive;
	@ManyToMany
	@JoinTable(
			  name = "user_role",
			  joinColumns = @JoinColumn(name = "user_id"),
			  inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Roles> roles;
	@ManyToMany
	@JoinTable(
			  name = "wish_list",
			  joinColumns = @JoinColumn(name = "user_id"),
			  inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private Set<Course> favourite;
}
