package elearning.service;

import elearning.dto.request.ChangePasswordRequest;
import elearning.dto.request.EditUserRequest;
import elearning.dto.request.UserInfoRequest;
import elearning.dto.request.UserLogin;
import elearning.dto.response.JwtResponse;
import elearning.dto.response.UserReponse;
import elearning.exception.CustomException;
import elearning.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IUserService {

    public void registerUser(UserInfoRequest userInfoRequest) throws CustomException;

    void registerSubAdmin(UserInfoRequest request) throws CustomException;

    JwtResponse login(UserLogin userLogin) throws CustomException;

    List<Users> getAllUser();

    String handleLogout(Authentication authentication);

    void editInfoUser(EditUserRequest editUserRequest);

    void changePassword(ChangePasswordRequest passwordRequest) throws CustomException;

    void editUser(EditUserRequest editUserRequest, Long id) throws CustomException;

    Page<UserReponse> findAll(String name, String phone, Pageable pageable);

    Users getCurrentUser();

    boolean changeStatusActiveUser(Long id) throws CustomException;

    UserReponse createUser(UserInfoRequest request) throws CustomException;
}
