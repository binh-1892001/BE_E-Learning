package elearning.dto;

import elearning.constant.RoleName;
import elearning.model.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto {
    private RoleName roleName;
    public RoleDto(Roles roles){
        this.roleName = roles.getRoleName();
    }
}
