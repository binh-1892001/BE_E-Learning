package elearning.service;

import elearning.constant.RoleName;
import elearning.model.Roles;

public interface IRoleService {
    Roles findByRoleName(RoleName roleName);
}
