package elearning.service;

import elearning.dto.CourseDto;
import elearning.dto.response.UserWishListResponse;
import elearning.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IWishListService {
    void addWishList(Long idCourse) throws CustomException;

    void deleteWishList(Long idCourse) throws CustomException;

    Page<CourseDto> getWishListCurrentUser(Pageable pageable);
}
