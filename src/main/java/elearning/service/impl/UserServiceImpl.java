package elearning.service.impl;

import elearning.constant.RoleName;
import elearning.dto.request.ChangePasswordRequest;
import elearning.dto.request.EditUserRequest;
import elearning.dto.request.UserLogin;
import elearning.dto.request.UserInfoRequest;
import elearning.dto.response.JwtResponse;
import elearning.dto.response.UserReponse;
import elearning.exception.CustomException;
import elearning.model.Roles;
import elearning.model.Users;
import elearning.repository.IUserRepository;
import elearning.security.jwt.JwtProvider;
import elearning.security.user_principal.UserPrincipal;
import elearning.service.IRoleService;
import elearning.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Value("604800016")
    private Long EXPIRED;

    private final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;

//    @Override
//    public void register(UserRegister userRegister) {
//        if (userRepository.existsByUsername(userRegister.getUsername())) {
//            throw new RuntimeException("username is exists");
//        }
//        Set<Roles> roles = new HashSet<>();
//
//        // Nếu không có quyền được truyền lên, mặc định là role user
//        if (userRegister.getRoles() == null || userRegister.getRoles().isEmpty()) {
//            roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
//        } else {
//            // Xác định quyền dựa trên danh sách quyền được truyền lên
//            userRegister.getRoles().forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
//                    case "user":
//                        roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
//                        break;
//                    default:
//                        throw new RuntimeException("role not found");
//                }
//            });
//        }
//        userRepository.save(Users.builder()
//                .fullName(userRegister.getFullName())
//                .username(userRegister.getUsername())
//                .password(passwordEncoder.encode(userRegister.getPassword()))
//                .roles(roles)
//                .build());
//    }


    /*Đăng ký dối với người dùng*/
    @Override
    public void registerSubAdmin(UserInfoRequest request) throws CustomException {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new CustomException("Username is registed");
        }
        setInfoUser(request,Set.of(RoleName.ROLE_SUBADMIN), new Users());
    }


    @Override
    public void registerUser(UserInfoRequest userInfoRequest) throws CustomException {
        if(userRepository.existsByUsername(userInfoRequest.getUsername())){
            throw new CustomException("Username is registed");
        }
        setInfoUser(userInfoRequest, Set.of(RoleName.ROLE_USER),new Users());
    }

    private void setInfoUser(UserInfoRequest userInfoRequest, Set<RoleName> roleNames, Users users) throws CustomException {
        BeanUtils.copyProperties(userInfoRequest, users);
        users.setPassword(passwordEncoder.encode(userInfoRequest.getPassword()));
        if(roleNames !=null && !roleNames.isEmpty()){
            Set<Roles> roles = new HashSet<>();
            roleNames.forEach(e->{
                Roles roles1 = roleService.findByRoleName(e);
                roles.add(roles1);
            });
            users.setRoles(roles);
        }
        userRepository.save(users);
    }



    @Override
    public JwtResponse login(UserLogin userLogin) throws CustomException {
        Authentication authentication;
        try {
            authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        } catch (AuthenticationException e) {
            throw new CustomException("Username or Password is incorrect 11312321");
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Users users = userRepository.findById(userPrincipal.getId()).orElseThrow(() -> new RuntimeException("user not found"));
        String refreshToken = null;
//        if (users.getRefreshToken() == null || users.getRefreshToken().isEmpty()) {
//            refreshToken = jwtProvider.generateRefreshToken(userPrincipal);
//
//        } else {
//            if (!jwtProvider.isTokenExpired(users.getRefreshToken())) {
//                refreshToken = users.getRefreshToken();
//            } else {
//                refreshToken = jwtProvider.generateRefreshToken(userPrincipal);
//            }
//        }
//        // lưu refresh token vào database
//        users.setRefreshToken(refreshToken);
        // thực hiện trả về cho người dùng
        return JwtResponse.builder()
                .accessToken(jwtProvider.generateToken(userPrincipal))
                .refreshToken(refreshToken)
                .expired(EXPIRED)
                .fullName(userPrincipal.getFullName())
                .username(userPrincipal.getUsername())
                .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<Users> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public String handleLogout(Authentication authentication) {
        return null;
    }

    @Override
    public void editInfoUser(EditUserRequest editUserRequest) {
        Users users = this.getCurrentUser();
        BeanUtils.copyProperties(editUserRequest, users);
        userRepository.save(users);
    }

    @Override
    public void changePassword(ChangePasswordRequest passwordRequest) throws CustomException {
        Users users = this.getCurrentUser();
        if(!passwordEncoder.matches(passwordRequest.getOldPassword(), users.getPassword())){
            throw new CustomException("Wrong password");
        }
        users.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
        userRepository.save(users);
    }

    /**
     *
     * @param editUserRequest
     * @param id
     */

    /*Admin*/
    @Override
    public void editUser(EditUserRequest editUserRequest, Long id) throws CustomException {
        Users users = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        BeanUtils.copyProperties(editUserRequest,users);
        if (editUserRequest.getRole() != null && !editUserRequest.getRole().isEmpty()) {
            Set<Roles> roles = new HashSet<>();
            editUserRequest.getRole().forEach(e -> {
                switch (e) {
                    case "admin":
                        roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
                    case "subadmin":
                        roles.add(roleService.findByRoleName(RoleName.ROLE_SUBADMIN));
                    case "user":
                        roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
                }
            });
            users.setRoles(roles);
        }
        userRepository.save(users);
    }

    @Override
    public Page<UserReponse> findAll(String name, String phone, Pageable pageable) {
        Page<Users> users = userRepository.findUsersByFullNameAndPhone(name, phone,pageable);
        return users.map(UserReponse::new);
    }

    @Override
    public Users getCurrentUser() {
        UserPrincipal userPrincipal = (UserPrincipal) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        return userRepository.findUsersByUsername(userPrincipal.getUsername()).orElseThrow(()-> new RuntimeException("User not found"));
    }

//    @Override
//    public JwtResponse handleRefreshToken(HttpServletRequest request, HttpServletResponse response) {
//        String refreshToken = jwtTokenFilter.getTokenFromRequest(request);
//        if (refreshToken != null || jwtProvider.validateToken(refreshToken)) {
//            String username = jwtProvider.getUsernameFromToken(refreshToken);
//
//            UserPrincipal userPrincipal = (UserPrincipal) userDetailService.loadUserByUsername(username);
//            if (jwtProvider.isTokenValid(refreshToken, userPrincipal) && !jwtProvider.isTokenExpired(refreshToken)) {
//                String accessToken = jwtProvider.generateToken(userPrincipal);
//                return JwtResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .expired(EXPIRED)
//                        .fullName(userPrincipal.getFullName())
//                        .username(userPrincipal.getUsername())
//                        .roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
//                        .build();
//            } else {
//                throw new RuntimeException("can't generate token");
//            }
//        } else {
//            throw new RuntimeException("Un Authentication");
//        }
//    }

}
