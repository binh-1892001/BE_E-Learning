package elearning.dto;

import elearning.dto.base.BaseObjectDto;
import elearning.model.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto extends BaseObjectDto {
    private String username;
    private String fullName;
    private String phone;
    private String password;
    private Boolean active;

    public UsersDto() {
    }

    public UsersDto(Users entity) {
//        this.username = entity.getUsername();
        this.fullName = entity.getFullName();
        this.phone = entity.getPhone();
//        this.password = entity.getPassword();
        this.active = entity.getActive();
    }

    public UsersDto(Users entity, Boolean isGetFull) {
//        this.username = entity.getUsername();
        this.fullName = entity.getFullName();
        this.phone = entity.getPhone();
//        this.password = entity.getPassword();
        this.active = entity.getActive();

        if (isGetFull) {
            this.createDate = entity.getCreateDate();
            this.modifyBy = entity.getModifyBy();
            this.createBy = entity.getCreateBy();
            this.modifyDate = entity.getModifyDate();
        }
    }
}
