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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = IUserRepository.findUsersByUsername(username).orElseThrow(() -> new RuntimeException("username not found"));
        Roles roles= new Roles();
        roles.setRoleName(RoleName.ROLE_ADMIN);
        System.out.println(users.getRoles());
        users.getRoles().add(roles);
        return new UserPrincipal(users);
    }
}
