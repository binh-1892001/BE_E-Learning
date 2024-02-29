package elearning.service;

import elearning.dto.ChapterDto;
import elearning.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChapterService {

    ChapterDto saveChapter(ChapterDto dto) throws CustomException;

    void deleteChapter(Long id);

    List<ChapterDto> getAllChapter();

    ChapterDto getChapterDtoById(Long id) throws CustomException;

    Page<ChapterDto> pagingChapterDto(Pageable pageable, String title);

}
