package elearning.controller;

import elearning.dto.CourseDto;
import elearning.exception.CustomException;
import elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/course")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/save")
    public ResponseEntity<CourseDto> saveOrUpdate(@ModelAttribute CourseDto request) throws IOException {
        CourseDto ret = courseService.saveCourse(request);
        return ResponseEntity.ok(ret);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CourseDto>> getAll() {
        List<CourseDto> ret = courseService.getAllCourse();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<CourseDto>> paging(@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , @RequestParam(required = false) String title) {
        Page<CourseDto> ret = courseService.pagingCourseDto(pageable, title);
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> get(@PathVariable("id") Long id) throws CustomException {
        CourseDto ret = courseService.getCourseDtoById(id);
        return ResponseEntity.ok(ret);
    }

}
