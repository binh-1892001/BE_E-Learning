package elearning.controller;

import elearning.dto.CourseDto;
import elearning.exception.CustomException;
import elearning.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/course")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;


    @Operation(summary = "Admin Add course")
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/save")
    public ResponseEntity<CourseDto> save(@ModelAttribute CourseDto request) throws IOException {
        CourseDto ret = courseService.saveCourse(request);
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Admin edit course")
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/update/{id}")
    public ResponseEntity<CourseDto> update(@ModelAttribute CourseDto request, @PathVariable Long id) throws CustomException, IOException {
        CourseDto ret = courseService.upDateCourse(request, id);
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Admin disable course")
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws CustomException {
        courseService.deleteCourse(id);
    }

    @Operation(summary = "Get all course")
    @Secured({"ROLE_SUBADMIN","ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/get-all")
    public ResponseEntity<List<CourseDto>> getAll() {
        List<CourseDto> ret = courseService.getAllCourse();
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Page and find Course with title")
    @GetMapping("/paging")
    public ResponseEntity<Page<CourseDto>> paging(@RequestParam(required = false)String home,@PageableDefault(page = 0, size = 20,sort = "id",direction = Sort.Direction.DESC) Pageable pageable
            , @RequestParam(required = false) String title) {
        Page<CourseDto> ret = courseService.pagingCourseDto(pageable, title, home);
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Get course by Id")
    @Secured({"ROLE_SUBADMIN","ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> get(@PathVariable("id") Long id) throws CustomException {
        CourseDto ret = courseService.getCourseDtoById(id);
        return ResponseEntity.ok(ret);
    }


}
