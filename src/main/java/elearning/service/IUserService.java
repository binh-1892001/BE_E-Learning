package elearning.service;

import elearning.dto.request.UserLogin;
import elearning.dto.response.JwtResponse;
import elearning.exception.CustomException;
import elearning.model.Users;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IUserService {

    JwtResponse login(UserLogin userLogin) throws CustomException;

    List<Users> getAllUser();

    String handleLogout(Authentication authentication);
//
//    JwtResponse handleRefreshToken(HttpServletRequest request, HttpServletResponse response);
}
