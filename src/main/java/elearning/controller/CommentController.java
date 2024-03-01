package elearning.controller;

import elearning.dto.CommentDto;
import elearning.exception.CustomException;
import elearning.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/comment")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/save")
    public ResponseEntity<CommentDto> saveOrUpdate(@RequestBody CommentDto request) throws CustomException {
        CommentDto ret = commentService.saveComment(request);
        return ResponseEntity.ok(ret);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CommentDto>> getAll() {
        List<CommentDto> ret = commentService.getAllComment();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<CommentDto>> paging(@PageableDefault(page = 0, size = 2) Pageable pageable) {
        Page<CommentDto> ret = commentService.pagingCommentDto(pageable);
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> get(@PathVariable("id") Long id) throws CustomException {
        CommentDto ret = commentService.getCommentDtoById(id);
        return ResponseEntity.ok(ret);
    }

}