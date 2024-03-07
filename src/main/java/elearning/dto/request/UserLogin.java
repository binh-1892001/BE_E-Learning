package elearning.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {
    @NotBlank(message = "phone is not blank")
    private String phone;
    @NotBlank(message = "password is not blank")
    private String password;
}
