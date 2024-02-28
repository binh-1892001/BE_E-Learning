package elearning.service;

import elearning.dto.CourseDto;
import elearning.dto.search.CourseSearchDto;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface CourseService {

    CourseDto saveCourse(CourseDto dto) throws IOException;

    void markDeleteCourse(Long id);

    List<CourseDto> getAllCourse();

    CourseDto getCourseDtoById(Long id);

    Page<CourseDto> pagingCourseDto(CourseSearchDto courseSearchDto);

}
