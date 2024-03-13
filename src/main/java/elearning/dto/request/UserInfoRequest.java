package elearning.dto.request;

import elearning.constant.RoleName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserInfoRequest {
//    @NotBlank(message = "username not blank")
//    private String username;
    @NotBlank(message = "fullName not blank")
    private String fullName;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be only number")
    private String phone;
    @NotBlank(message = "password not blank")
    private String password;
    private Set<RoleName> role;
}
