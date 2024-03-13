package elearning.service;

import elearning.constant.RoleName;
import elearning.dto.RoleDto;
import elearning.model.Roles;

import java.util.List;

public interface IRoleService {
    Roles findByRoleName(RoleName roleName);

    List<RoleDto> getAll();
}
