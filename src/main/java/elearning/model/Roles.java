package elearning.model;

import elearning.constant.RoleName;
import elearning.model.base.BaseObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Roles extends BaseObject {
	@Column(unique = true)
	private RoleName roleName;
}
