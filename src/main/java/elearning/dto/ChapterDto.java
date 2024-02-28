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
    public ChapterDto() {
    }
    public ChapterDto(Chapter entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        if(entity.getCourse() != null){
            this.course = new CourseDto(entity.getCourse());
        }
        if (entity.getVoided() != null) {
            this.voided = entity.getVoided();
        }
    }

    public ChapterDto(Chapter entity, Boolean isGetFull) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        if(entity.getCourse() != null){
            this.course = new CourseDto(entity.getCourse());
        }
        if (entity.getVoided() != null) {
            this.voided = entity.getVoided();
        }

        if (isGetFull) {
            this.createDate = entity.getCreateDate();
            this.modifyBy = entity.getModifyBy();
            this.createBy = entity.getCreateBy();
            this.modifyDate = entity.getModifyDate();
        }
    }
}
