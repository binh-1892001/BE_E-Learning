package elearning.service.impl;

import elearning.constant.RoleName;
import elearning.model.Roles;
import elearning.repository.IRoleRepository;
import elearning.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Roles findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("role not found"));
    }
}
