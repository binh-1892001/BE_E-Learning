package elearning.repository;

import elearning.dto.CommentDto;
import elearning.dto.LessonDto;
import elearning.model.Comment;
import elearning.model.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("select new elearning.dto.CommentDto(e, true) from Comment e")
    List<CommentDto> getAll();

    @Query("select new elearning.dto.CommentDto(e, true) from Comment e" +
            " order by e.id asc ")
    Page<CommentDto> getCommentPage(Pageable pageable);
}
