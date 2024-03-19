package elearning.controller;

import elearning.dto.CommentDto;
import elearning.exception.CustomException;
import elearning.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Add comment in lesson")
    @PostMapping("/save")
    public ResponseEntity<CommentDto> save(@RequestBody CommentDto request) throws CustomException {
        CommentDto ret = commentService.saveComment(request);
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Edit comment in lesson")
    @PutMapping("/update/{id}")
    public ResponseEntity<CommentDto> update(@RequestBody CommentDto request, @PathVariable Long id) throws CustomException {
        CommentDto ret = commentService.upDateComment(request, id);
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Disable comment")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws CustomException {
        commentService.deleteComment(id);
    }

    @Operation(summary = "Get All comment")
    @GetMapping("/get-all")
    public ResponseEntity<List<CommentDto>> getAll() {
        List<CommentDto> ret = commentService.getAllComment();
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Page comment")
    @GetMapping("/paging")
    public ResponseEntity<Page<CommentDto>> paging(@PageableDefault(page = 0, size = 2) Pageable pageable) {
        Page<CommentDto> ret = commentService.pagingCommentDto(pageable);
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Get comment by Id")
    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> get(@PathVariable("id") Long id) throws CustomException {
        CommentDto ret = commentService.getCommentDtoById(id);
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Get page parent comment ")
    @GetMapping("/paging-comment-parent")
    public ResponseEntity<Page<CommentDto>> pagingCommentParent(@PageableDefault(page = 0, size = 2,sort = "id",direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CommentDto> ret = commentService.pagingCommentParent(pageable);
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Get page child comment by parent comment")
    @GetMapping("/paging-comment-children")
    public ResponseEntity<Page<CommentDto>> pagingCommentChildrenByParentId(@PageableDefault(page = 0, size = 2) Pageable pageable
            , @RequestParam Long parentId) {
        Page<CommentDto> ret = commentService.pagingCommentChildrenByParentId(pageable, parentId);
        return ResponseEntity.ok(ret);
    }
}
