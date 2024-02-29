package elearning.service.impl;

import elearning.dto.ChapterDto;
import elearning.dto.CourseDto;
import elearning.exception.CustomException;
import elearning.model.Chapter;
import elearning.model.Course;
import elearning.repository.ChapterRepository;
import elearning.repository.CourseRepository;
import elearning.service.ChapterService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    CourseRepository courseRepository;

    @PersistenceContext
    public EntityManager manager;


    @Override
    public ChapterDto saveChapter(ChapterDto dto) throws CustomException {
        if (dto == null) return null;
        Chapter entity = null;
        if (dto.getId() != null) {
            entity = chapterRepository.findById(dto.getId()).orElse(null);
        }
        if (entity == null) {
            entity = new Chapter();
        }
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());

        if(dto.getCourse() == null || dto.getCourse().getId() == null){
            throw new CustomException("Course is not null");
        }
        Course course = courseRepository.findById(dto.getCourse().getId()).orElse(null);
        if (course == null){
            throw new CustomException("Course is not null");
        }
        entity.setCourse(course);
        entity = chapterRepository.save(entity);
        return new ChapterDto(entity);
    }

    @Override
    public void deleteChapter(Long id) {
        chapterRepository.deleteById(id);

    }

    @Override
    public List<ChapterDto> getAllChapter() {
        return chapterRepository.getAll();
    }

    @Override
    public ChapterDto getChapterDtoById(Long id) throws CustomException {
        return new ChapterDto(this.getChapterById(id));
    }


    private Chapter getChapterById(Long id) throws CustomException {
        Optional<Chapter> optional = chapterRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new CustomException("Chapter not found");
    }

    @Override
    public Page<ChapterDto> pagingChapterDto(Pageable pageable, String title) {
        Page<ChapterDto> page = chapterRepository.getChapterPage(pageable, title);
        return page;
    }

}
