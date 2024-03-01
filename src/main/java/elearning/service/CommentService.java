package elearning.service;

import elearning.dto.CommentDto;
import elearning.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    CommentDto saveComment(CommentDto dto) throws CustomException;

    void deleteComment(Long id);

    List<CommentDto> getAllComment();

    CommentDto getCommentDtoById(Long id) throws CustomException;

    Page<CommentDto> pagingCommentDto(Pageable pageable);

}
