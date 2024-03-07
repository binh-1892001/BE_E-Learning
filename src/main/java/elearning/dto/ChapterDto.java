package elearning.dto;

import elearning.dto.base.BaseObjectDto;
import elearning.model.Chapter;
import elearning.model.Course;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChapterDto extends BaseObjectDto {
    private String title;
    private String description;
    private CourseDto course;
    private Long courseId;
    private String courseName;
    public ChapterDto() {
    }
    public ChapterDto(Chapter entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        if (entity.getVoided() != null) {
            this.voided = entity.getVoided();
        }
        if(entity.getCourse() != null){
//            this.course = new CourseDto(entity.getCourse());
            this.courseId = entity.getCourse().getId();
            this.courseName = entity.getCourse().getTitle();
        }
    }

    public ChapterDto(Chapter entity, Boolean isGetFull) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        if (entity.getVoided() != null) {
            this.voided = entity.getVoided();
        }
        if(entity.getCourse() != null){
//            this.course = new CourseDto(entity.getCourse());
            this.courseId = entity.getCourse().getId();
            this.courseName = entity.getCourse().getTitle();
        }

        if (isGetFull) {
            this.createDate = entity.getCreateDate();
            this.modifyBy = entity.getModifyBy();
            this.createBy = entity.getCreateBy();
            this.modifyDate = entity.getModifyDate();
        }
    }
}
