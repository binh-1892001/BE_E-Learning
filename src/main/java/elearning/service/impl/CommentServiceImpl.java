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

    @Override
    public CommentDto saveComment(CommentDto dto) throws CustomException {
        if (dto == null) return null;
        Comment entity = null;
        if (dto.getId() != null) {
            entity = commentRepository.findById(dto.getId()).orElse(null);
        }
        if (entity == null) {
            entity = new Comment();
        }

//        UserPrincipal userPrincipal = (UserPrincipal) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
//        if(userPrincipal == null && userPrincipal.getId() == null){
//            throw new CustomException("User is not null");
//        }
//        Users users = userRepository.findById(userPrincipal.getId()).orElse(null);
//        if(users == null || users.getId() != null){
//            throw new CustomException("User not found");
//        }
//        entity.setUsers(users);

        entity.setContent(dto.getContent());

        if(dto.getLesson() == null || dto.getLesson().getId() == null){
            throw new CustomException("Lesson is not null");
        }
        Lesson lesson = lessonRepository.findById(dto.getLesson().getId()).orElse(null);
        if (lesson == null){
            throw new CustomException("Lesson not found");
        }
        entity.setLesson(lesson);

        if(dto.getComment() != null && dto.getComment().getId() != null){
            Comment comment = commentRepository.findById(dto.getComment().getId()).orElse(null);
            entity.setComment(comment);
        }

        entity = commentRepository.save(entity);
        return new CommentDto(entity);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
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
}
