package elearning.controller;

import elearning.dto.ChapterDto;
import elearning.exception.CustomException;
import elearning.service.ChapterService;
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

    @PostMapping("/save")
    public ResponseEntity<ChapterDto> saveOrUpdate(@RequestBody ChapterDto request) throws CustomException {
        ChapterDto ret = chapterService.saveChapter(request);
        return ResponseEntity.ok(ret);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        chapterService.deleteChapter(id);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ChapterDto>> getAll() {
        List<ChapterDto> ret = chapterService.getAllChapter();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<ChapterDto>> paging(@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , @RequestParam(required = false) String title) {
        Page<ChapterDto> ret = chapterService.pagingChapterDto(pageable, title);
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterDto> get(@PathVariable("id") Long id) {
        ChapterDto ret = chapterService.getChapterDtoById(id);
        return ResponseEntity.ok(ret);
    }

}
