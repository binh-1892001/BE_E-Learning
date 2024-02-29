package elearning.service;

import elearning.dto.CourseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface CourseService {

    CourseDto saveCourse(CourseDto dto) throws IOException;

    void deleteCourse(Long id);

    List<CourseDto> getAllCourse();

    CourseDto getCourseDtoById(Long id);

    Page<CourseDto> pagingCourseDto(Pageable pageable, String title);

}
