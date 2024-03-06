package elearning.dto.response;

import elearning.dto.base.BaseObjectDto;
import elearning.model.Users;
import elearning.model.base.BaseObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserReponse extends BaseObjectDto {

//    private String username;
    private String fullName;
    private String phone;
    private List<String> role;
    private Boolean voided;

    public UserReponse() {
    }

    public UserReponse(Users users) {
        setId(users.getId());
        this.setCreateDate(users.getCreateDate());
//        this.username = users.getUsername();
        this.fullName = users.getFullName();
        this.phone = users.getPhone();
        this.voided = users.getVoided();
        role = new ArrayList<>();
        if(users.getRoles() != null){
            users.getRoles().forEach(e->role.add(e.getRoleName().name()));
        }
    }
}
