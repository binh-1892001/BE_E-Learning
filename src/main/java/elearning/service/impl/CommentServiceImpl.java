package elearning.service.impl;

import elearning.dto.ChapterDto;
import elearning.dto.CommentDto;
import elearning.dto.UsersDto;
import elearning.exception.CustomException;
import elearning.model.*;
import elearning.repository.CommentRepository;
import elearning.repository.IUserRepository;
import elearning.repository.LessonRepository;
import elearning.repository.UserRepository;
import elearning.security.user_principal.UserPrincipal;
import elearning.service.CommentService;
import elearning.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    IUserService iUserService;

    public CommentDto save(Comment entity, CommentDto dto) throws CustomException {

        Users users = iUserService.getCurrentUser();
        if (users == null || users.getId() == null) {
            throw new CustomException("User not found");
        }
        entity.setUsers(users);

        entity.setContent(dto.getContent());

        if (dto.getLesson() == null || dto.getLesson().getId() == null) {
            throw new CustomException("Lesson is not null");
        }
        Lesson lesson = lessonRepository.findById(dto.getLesson().getId()).orElse(null);
        if (lesson == null) {
            throw new CustomException("Lesson not found");
        }
        entity.setLesson(lesson);

        if (dto.getComment() != null && dto.getComment().getId() != null) {
            Comment comment = commentRepository.findById(dto.getComment().getId()).orElse(null);
            entity.setComment(comment);
        }

        entity = commentRepository.save(entity);
        return new CommentDto(entity);
    }

    @Override
    public CommentDto saveComment(CommentDto dto) throws CustomException {
        Comment entity = new Comment();
        return this.save(entity, dto);
    }

    @Override
    public CommentDto upDateComment(CommentDto dto, Long id) throws CustomException {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException("Comment not found"));
        return this.save(comment, dto);
    }

    @Override
    public void deleteComment(Long id) throws CustomException {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException("Comment not found"));
        if(comment.getVoided() == null || comment.getVoided() == false){
            comment.setVoided(true);
        }else {
            comment.setVoided(false);
        }
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> getAllComment() {
        return commentRepository.getAll();
    }

    @Override
    public CommentDto getCommentDtoById(Long id) throws CustomException {
        return new CommentDto(this.getCommentById(id));
    }

    private Comment getCommentById(Long id) throws CustomException {
        Optional<Comment> optional = commentRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new CustomException("Comment not found");
    }

    @Override
    public Page<CommentDto> pagingCommentDto(Pageable pageable) {
        Page<CommentDto> page = commentRepository.getCommentPage(pageable);
        return page;
    }

    @Override
    public Page<CommentDto> pagingCommentParent(Pageable pageable) {
        Page<CommentDto> page = commentRepository.getCommentParentPage(pageable);
        return page;
    }

    @Override
    public Page<CommentDto> pagingCommentChildrenByParentId(Pageable pageable, Long parentId) {
        Page<CommentDto> page = commentRepository.getCommentChildrenByParentId(pageable, parentId);
        return page;
    }

}
