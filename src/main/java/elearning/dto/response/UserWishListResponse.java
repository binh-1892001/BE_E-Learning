package elearning.dto.response;

import elearning.dto.CourseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserWishListResponse {
    private String username;
    private String fullName;
    private String phone;
    List<CourseDto> wishList;
}
