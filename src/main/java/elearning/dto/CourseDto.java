package elearning.dto;

import elearning.dto.base.BaseObjectDto;
import elearning.model.Course;
import elearning.model.base.BaseObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class CourseDto extends BaseObjectDto {
    private String title;
    private String image;
    private String description;
    private MultipartFile imageFile;
    private String subDescription;

    public CourseDto() {
    }

    public CourseDto(Course entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.image = entity.getImage();
        this.description = entity.getDescription();
        this.subDescription = entity.getSubDescription();
    }

    public CourseDto(Course entity, Boolean isGetFull) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.image = entity.getImage();
        this.description = entity.getDescription();
        this.subDescription = entity.getSubDescription();
        if (isGetFull) {
            this.createDate = entity.getCreateDate();
            this.modifyBy = entity.getModifyBy();
            this.createBy = entity.getCreateBy();
            this.modifyDate = entity.getModifyDate();
        }
    }
}
