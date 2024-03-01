package elearning.repository;

import elearning.dto.CourseDto;
import elearning.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select new elearning.dto.CourseDto(e, true) from Course e")
    List<CourseDto> getAll();

    @Query("select new elearning.dto.CourseDto(e, true) from Course e"
            + " Where ( 1=1 ) "
            + " and ( :title is null or  e.title like concat('%',:title,'%'))" +
            "  order by e.id asc ")
    Page<CourseDto> getCoursePage(Pageable pageable, String title);

}
