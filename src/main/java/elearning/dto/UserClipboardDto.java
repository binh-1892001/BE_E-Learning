package elearning.dto;

import elearning.dto.base.BaseObjectDto;
import elearning.model.UserClipboard;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class UserClipboardDto extends BaseObjectDto {
    private String fullname;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be only number")
    private String phone;
    public UserClipboardDto(UserClipboard userClipboard){
        BeanUtils.copyProperties(userClipboard,this);
    }
}
