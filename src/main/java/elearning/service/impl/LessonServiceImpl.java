package elearning.service.impl;

import elearning.dto.LessonDto;
import elearning.exception.CustomException;
import elearning.model.Chapter;
import elearning.model.Lesson;
import elearning.repository.ChapterRepository;
import elearning.repository.LessonRepository;
import elearning.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    ChapterRepository chapterRepository;

    @Override
    public LessonDto saveLesson(LessonDto dto) throws CustomException {
        if (dto == null) return null;
        Lesson entity = null;
        if (dto.getId() != null) {
            entity = lessonRepository.findById(dto.getId()).orElse(null);
        }
        if (entity == null) {
            entity = new Lesson();
        }
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setVideo(dto.getVideo());
        entity.setResources(dto.getResources());

        if(dto.getChapterDto() == null || dto.getChapterDto().getId() == null){
            throw new CustomException("Chapter is not null");
        }
        Chapter chapter = chapterRepository.findById(dto.getChapterDto().getId()).orElse(null);
        if (chapter == null){
            throw new CustomException("Chapter is not null");
        }
        entity.setChapter(chapter);
        entity = lessonRepository.save(entity);

        return new LessonDto(entity);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public List<LessonDto> getAllLesson() {
        return lessonRepository.getAll();
    }

    @Override
    public LessonDto getLessonDtoById(Long id) throws CustomException {
        return new LessonDto(this.getLessonById(id));
    }
    private Lesson getLessonById(Long id) throws CustomException {
        Optional<Lesson> optional = lessonRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new CustomException("Lesson not found");
    }

    @Override
    public Page<LessonDto> pagingLessonDto(Pageable pageable, String title) {
        Page<LessonDto> page = lessonRepository.getLessonPage(pageable, title);
        return page;
    }
}
