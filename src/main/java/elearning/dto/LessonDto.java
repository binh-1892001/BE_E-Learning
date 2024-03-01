package elearning.dto;

import elearning.dto.base.BaseObjectDto;
import elearning.model.Lesson;
import lombok.*;


@Getter
@Setter
public class LessonDto  extends BaseObjectDto {
    private String title;
    private String video;
    private String resources;
    private String description;
    private ChapterDto chapterDto;

    public LessonDto() {
    }

    public LessonDto(Lesson entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.video = entity.getVideo();
        this.resources = entity.getResources();
        this.description = entity.getDescription();
        if(entity.getChapter() != null){
            this.chapterDto = new ChapterDto(entity.getChapter());
        }
    }

    public LessonDto(Lesson entity, Boolean isGetFull) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.video = entity.getVideo();
        this.resources = entity.getResources();
        this.description = entity.getDescription();
        if(entity.getChapter() != null){
            this.chapterDto = new ChapterDto(entity.getChapter());
        }
        if (isGetFull) {
            this.createDate = entity.getCreateDate();
            this.modifyBy = entity.getModifyBy();
            this.createBy = entity.getCreateBy();
            this.modifyDate = entity.getModifyDate();
        }
    }

}
