package elearning.security.user_principal;

import elearning.model.Users;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@Data
public class UserPrincipal implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private String fullName;
    @Getter
    private boolean active;

    public UserPrincipal(Users user) {
        this.id = user.getId();
        this.fullName =user.getFullName();
        this.username= user.getPhone();
        this.password =user.getPassword();
        this.active = Objects.isNull(user.getActive()) || user.getActive();
        this.authorities = user.getRoles().stream().map(item->new SimpleGrantedAuthority(item.getRoleName().toString())).toList();}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
