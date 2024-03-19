package elearning.controller;

import elearning.dto.request.ChangePasswordRequest;
import elearning.dto.request.EditUserRequest;
import elearning.dto.request.UserInfoRequest;
import elearning.dto.response.UserReponse;
import elearning.exception.CustomException;
import elearning.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/register/sub-admin")
    public ResponseEntity<String> registerSubAdmin(@RequestBody @Valid UserInfoRequest userInfoRequest) throws CustomException {
        userService.registerSubAdmin(userInfoRequest);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserInfoRequest userInfoRequest) throws CustomException {
        userService.createUser(userInfoRequest);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserInfoRequest userInfoRequest) throws CustomException {
        userService.registerUser(userInfoRequest);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN","ROLE_SUBADMIN","ROLE_USER"})
    @PutMapping ("/edit-info-user")
    public ResponseEntity<String>editInfoUser(@RequestBody @Valid EditUserRequest editUserRequest){
        userService.editInfoUser(editUserRequest);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN","ROLE_SUBADMIN","ROLE_USER"})
    @PutMapping("/change-password")
    public ResponseEntity<String> changePass(@RequestBody @Valid ChangePasswordRequest request) throws CustomException {
        userService.changePassword(request);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/admin/edit-user/{id}")
    public ResponseEntity<String> adminEditUser(@RequestBody @Valid EditUserRequest editUserRequest,@PathVariable Long id) throws CustomException {
        userService.editUser(editUserRequest,id);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/page")
    ResponseEntity<Page<UserReponse>> findAllUser(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "phone", required = false) String phone, @PageableDefault(page = 0, size = 1000,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        return new ResponseEntity<>(userService.findAll(name,phone,pageable),HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/change-status/{id}")
    ResponseEntity<Boolean> changeStatus(@PathVariable Long id) throws CustomException {
        return new ResponseEntity<>(userService.changeStatusActiveUser(id), HttpStatus.OK);
    }
}
