package elearning.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {
    @NotBlank(message = "username is not blank")
    private String username;
    @NotBlank(message = "password is not blank")
    private String password;
}
