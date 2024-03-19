package elearning.controller;

import elearning.dto.ChapterDto;
import elearning.exception.CustomException;
import elearning.service.ChapterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/chapter")
@RestController
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Operation(summary = "Add chapter to course ")
    @PostMapping("/save")
    public ResponseEntity<ChapterDto> save(@RequestBody ChapterDto request) throws CustomException {
        ChapterDto ret = chapterService.saveChapter(request);
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Edit chapter")
    @PutMapping("/update/{id}")
    public ResponseEntity<ChapterDto> update(@RequestBody ChapterDto request, @PathVariable Long id) throws CustomException {
        ChapterDto ret = chapterService.upDateChapter(request, id);
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Disable chapter  ")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws CustomException {
        chapterService.deleteChapter(id);
    }

    @Operation(summary = "Get all chapter")
    @GetMapping("/get-all")
    public ResponseEntity<List<ChapterDto>> getAll() {
        List<ChapterDto> ret = chapterService.getAllChapter();
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Get page chapter ")
    @GetMapping("/paging")
    public ResponseEntity<Page<ChapterDto>> paging(@PageableDefault(page = 0, size = 2,sort = "id",direction = Sort.Direction.DESC) Pageable pageable
            , @RequestParam(required = false) String title) {
        Page<ChapterDto> ret = chapterService.pagingChapterDto(pageable, title);
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Get chapter by Id")
    @GetMapping("/{id}")
    public ResponseEntity<ChapterDto> get(@PathVariable("id") Long id) throws CustomException {
        ChapterDto ret = chapterService.getChapterDtoById(id);
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Get chapter by courseId")
    @GetMapping("/get-chapters-by-course/{courseId}")
    public ResponseEntity<List<ChapterDto>> getChaptersByCourseId(@PathVariable("courseId") Long courseId) {
        List<ChapterDto> ret = chapterService.getChaptersByCourseId(courseId);
        return ResponseEntity.ok(ret);
    }
}
