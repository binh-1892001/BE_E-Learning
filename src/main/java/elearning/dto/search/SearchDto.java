package elearning.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {
    private String keyword;
    private Integer pageIndex = 1;
    private Integer pageSize = 10;
    private Long id;
    private Boolean isVoided;
}
