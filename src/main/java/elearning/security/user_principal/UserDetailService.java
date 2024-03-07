package elearning.security.user_principal;

import elearning.constant.RoleName;
import elearning.model.Roles;
import elearning.model.Users;
import elearning.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository IUserRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Users users = IUserRepository.findUsersByPhone(phone).orElseThrow(() -> new RuntimeException("username not found"));
        return new UserPrincipal(users);
    }
}
