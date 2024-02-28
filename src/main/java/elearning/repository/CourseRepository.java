package elearning.repository;

import elearning.dto.CourseDto;
import elearning.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("select new elearning.dto.CourseDto(e, true) from Course e"
            + " Where (e.voided is null OR e.voided = false ) ")
    List<CourseDto> getAll();
}
