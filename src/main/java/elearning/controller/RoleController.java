package elearning.controller;

import elearning.dto.RoleDto;
import elearning.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/role")
@RestController
@Secured("ROLE_ADMIN")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @GetMapping("/get-all")
    public ResponseEntity<List<RoleDto>> getAll(){
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }
}
