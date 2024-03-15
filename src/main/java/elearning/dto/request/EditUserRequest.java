package elearning.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EditUserRequest {
    @NotBlank(message = "fullName not blank")
    private String fullName;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be only number")
    private String phone;
    private String password;
    private List<String> role;
    private Boolean voided;
}
