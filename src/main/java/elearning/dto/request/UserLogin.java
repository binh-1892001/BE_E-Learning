package elearning.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {
    private String username;
    private String password;
}
