package elearning.controller;

import elearning.dto.CourseDto;
import elearning.dto.search.CourseSearchDto;
import elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/course")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    @Secured({"ROLE_ADMIN"})
    @PostMapping("/save")
    public ResponseEntity<CourseDto> saveOrUpdate(@ModelAttribute CourseDto request) throws IOException {
        CourseDto ret = courseService.saveCourse(request);
        return ResponseEntity.ok(ret);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        courseService.markDeleteCourse(id);
    }

    @Secured({"ROLE_SUBADMIN","ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/get-all")
    public List<CourseDto> getAll() {
        List<CourseDto> ret = courseService.getAllCourse();
        return ret;
    }

    @Secured({"ROLE_SUBADMIN","ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/paging")
    public Page<CourseDto> paging(@RequestBody CourseSearchDto searchDto) {
        Page<CourseDto> ret = courseService.pagingCourseDto(searchDto);
        return ret;
    }

    @Secured({"ROLE_SUBADMIN","ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> get(@PathVariable("id") Long id) {
        CourseDto ret = courseService.getCourseDtoById(id);
        return ResponseEntity.ok(ret);
    }


}
