package elearning.model;

import elearning.model.base.BaseObject;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Lưu trữ thông tin mà người dùng đã nhập nhưng chưa đk*/
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserClipboard extends BaseObject {
    private String fullname;
    private String phone;
}
