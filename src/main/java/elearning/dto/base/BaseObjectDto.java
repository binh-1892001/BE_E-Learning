package elearning.dto.base;

import elearning.model.base.BaseObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseObjectDto extends AuditableEntityDto{
    protected Long id;

    public BaseObjectDto() {
    }
    public BaseObjectDto(BaseObject entity) {
        super(entity);
        if (entity != null) {
            this.id = entity.getId();
        }

    }
}
