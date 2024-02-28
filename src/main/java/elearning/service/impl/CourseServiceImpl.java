package elearning.service.impl;

import elearning.dto.CourseDto;
import elearning.dto.search.CourseSearchDto;
import elearning.model.Course;
import elearning.repository.CourseRepository;
import elearning.service.CourseService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @PersistenceContext
    public EntityManager manager;

    @Value("${course.file.path.img}")
    private String filePath;

    @Override
    public CourseDto saveCourse(CourseDto dto) throws IOException {
        if (dto == null) return null;
        Course entity = null;
        if (dto.getId() != null) {
            entity = courseRepository.findById(dto.getId()).orElse(null);
        }
        if (entity == null) {
            entity = new Course();
        }
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());

        entity = this.uploadFileImg(dto, entity);
        entity = courseRepository.save(entity);
        return new CourseDto(entity);
    }

    // upload file img
    private Course uploadFileImg(CourseDto dto, Course entity) throws IOException {
        if (dto.getImageFile() != null) {
            LocalDateTime dateTime = LocalDateTime.now();
            String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-nnnnnnnnn"));
            String filename = formattedDateTime + "_" + removeAccentsAndSpaces(dto.getImageFile().getOriginalFilename());

            entity.setImage(filename);

            // ghi file
            byte[] bytes = dto.getImageFile().getBytes();
            Path path = Paths.get(filePath + filename);
            Files.write(path, bytes);

        }
        return entity;
    }

    private String removeAccentsAndSpaces(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String result = pattern.matcher(temp).replaceAll("");
        result = result.replaceAll("đ", "d").replaceAll("Đ", "D");
        result = result.replaceAll("\\s+", "");
        return result;
    }

    @Override
    public void markDeleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        course.setVoided(true);
        courseRepository.save(course);
    }

    @Override
    public List<CourseDto> getAllCourse() {
        return courseRepository.getAll();
    }

    @Override
    public CourseDto getCourseDtoById(Long id) {
        return new CourseDto(this.getCourseById(id));
    }

    private Course getCourseById(Long id) {
        Optional<Course> optional = courseRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<CourseDto> pagingCourseDto(CourseSearchDto dto) {
        if (dto == null) {
            return null;
        }
        int pageIndex = dto.getPageIndex() != null ? dto.getPageIndex() : 0;
        int pageSize = dto.getPageSize() != null ? dto.getPageSize() : 10;
        pageIndex = pageIndex > 0 ? pageIndex - 1 : 0;
        String whereClause = buildWhereQueryPagingCourse(dto);
        String sql = "select new elearning.dto.CourseDto(entity, false) from Course as entity ";
        String sqlCount = "select count(entity.id) from Course as entity ";
        String orderBy = " order by entity.createDate ASC";

        sql += whereClause + orderBy;
        sqlCount += whereClause;

        Query q = manager.createQuery(sql, CourseDto.class);
        Query qCount = manager.createQuery(sqlCount);

        setParametersPagingCourse(dto, q, qCount);

        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);

        List<CourseDto> entities = q.getResultList();
        if (entities == null || entities.size() == 0) {
            return null;
        }
        long count = (long) qCount.getSingleResult();
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return new PageImpl<>(entities, pageable, count);
    }

    private static String buildWhereQueryPagingCourse(CourseSearchDto dto) {
        String whereClause = dto.getIsVoided() != null && dto.getIsVoided() ?
                "where (1=1) AND (entity.voided = 1 ) " : "where (1=1) AND (entity.voided is null OR entity.voided = 0 ) ";

        if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
            whereClause += "AND ( entity.title LIKE :text ) ";
        }

        return whereClause;
    }

    private static void setParametersPagingCourse(CourseSearchDto dto, Query q, Query qCount) {
        if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
            q.setParameter("text", '%' + dto.getKeyword().trim() + '%');
            qCount.setParameter("text", '%' + dto.getKeyword().trim() + '%');
        }
    }
}
