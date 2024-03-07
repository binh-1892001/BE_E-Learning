package elearning.service;

import elearning.dto.CourseDto;
import elearning.dto.base.BaseObjectDto;
import elearning.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IWishListService {
    void addWishList(BaseObjectDto dto) throws CustomException;

    void deleteWishList(Long idCourse) throws CustomException;

    Page<CourseDto> getWishListCurrentUser(Pageable pageable);

    Page<CourseDto> getWishListByUserId(Long userId, Pageable pageable);

    void deleteList(List<Long> dto);
}
