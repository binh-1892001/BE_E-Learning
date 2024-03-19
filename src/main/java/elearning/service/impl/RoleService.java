package elearning.service.impl;

import elearning.constant.RoleName;
import elearning.dto.RoleDto;
import elearning.model.Roles;
import elearning.repository.IRoleRepository;
import elearning.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Roles findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("role not found"));
    }

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAll().stream().map(RoleDto::new).collect(Collectors.toList());
    }
}
